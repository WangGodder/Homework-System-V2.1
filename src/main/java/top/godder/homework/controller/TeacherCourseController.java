package top.godder.homework.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.godder.homework.domain.*;
import top.godder.homework.service.*;
import top.godder.homework.utils.DOCX2HTML;
import top.godder.homework.utils.Duplicate;
import top.godder.homework.utils.ZipUtil;
import top.godder.homework.vo.JsonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

@Controller
@RequestMapping("/teacher_teachCourse")
public class TeacherCourseController {
	@Autowired
	private AttendCourseService attendCourseService;
	@Autowired
	private LogService logService;
	@Autowired
	private TeachCourseService teachCourseService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private DiscussionService discussionService;
	@Autowired
	private SensitiveWordService sensitiveWordService;
	@Value(value = "${path.resource}")
	private String resourcePath;
	@Value(value = "${path.homework}")
	private String homeworkPath;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@RequestMapping(value="/homeworks")
	public String homeworks(ModelMap model, @RequestParam("teachCourseId") Integer teachCourseId) {
		List<Homework> homeworks = this.attendCourseService.listHomework(teachCourseId);
		model.addAttribute("homeworks", homeworks);
		return "teacherCoursePage/homeworkPage";
	}
	
	
	@RequestMapping(value="editorHomework", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult editorHomework(@RequestParam Integer homeworkId, @RequestParam String name, @RequestParam String info, @RequestParam String deadline) {
		Homework homework = new Homework();
		homework.setId(homeworkId);
		homework.setName(name);
		homework.setInfo(info);
		try {
			homework.setDeadline(sdf.parse(deadline));
		} catch (ParseException e1) {
			e1.printStackTrace();
			return JsonResult.fail("deadline with wrong format");
		}
		try {
			attendCourseService.changeHomework(homework);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail("It happen a error when changing homework");
		}
		return JsonResult.success();
	}
	
	@RequestMapping(value="/deleteHomework", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult deleteHomework(@RequestParam Integer homeworkId, HttpSession session, HttpServletRequest request) {
		Teacher teacher = null;
		try {
			teacher = (Teacher)session.getAttribute("user");
		} catch (Exception e) {
			return JsonResult.fail("permission deny");
		}
		if (!attendCourseService.deleteHomework(homeworkId)) {
			return JsonResult.fail("a error has happened when delete homework");
		}
		Log log = new Log();
		log.setIp(request.getRemoteAddr());
		log.setOperatorid(teacher.getId());
		log.setType(Log.DELETE_HOMEWORK);
		log.setContent("delete homework," + homeworkId);
		logService.log(log);
		return JsonResult.success();
	}
	
	@RequestMapping(value="/addHomework", method=RequestMethod.POST)
	public JsonResult addHomework(@RequestParam String name, @RequestParam String info, @RequestParam String deadline, @RequestParam Integer teachCourseId, @RequestParam String format, HttpSession session) {
		@SuppressWarnings("unused")
		Teacher teacher = null;
		try {
			teacher = (Teacher)session.getAttribute("user");
		} catch (Exception e) {
			return JsonResult.fail("permission deny");
		}
		Homework homework = new Homework();
		homework.setName(name);
		homework.setInfo(info);
		if (format.isEmpty()) {
			homework.setFormat(null);
		} else {
			homework.setFormat(format);
		}
		try {
			homework.setDeadline(sdf.parse(deadline));
		} catch(Exception exception) {
			return JsonResult.fail("deadline format is wrong");
		}
		homework.setTeachcourseid(teachCourseId);
		if (attendCourseService.createHomework(homework)) {
			return JsonResult.success();
		}
		return JsonResult.fail("a error has happened when create homework");
	}
	
	@RequestMapping(value="/homeworkScore/{teachCourseId}", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult homeworkScore(HttpServletRequest request, HttpSession session, @PathVariable Integer teachCourseId) {
		Map<String, String[]> map = request.getParameterMap();
		for (Entry<String, String[]> entry: map.entrySet()) {
			Homework homework = attendCourseService.getHomework(Integer.parseInt(entry.getKey()));
			homework.setProportion(Integer.parseInt(entry.getValue()[0]));
			attendCourseService.changeHomework(homework);
		}
		List<AttendCourse> attendCourses = attendCourseService.listAttendCourse(teachCourseId);
		for (AttendCourse attendCourse: attendCourses) {
			attendCourseService.changeHomeworkScore(attendCourse.getStudentId(), teachCourseId);
		}
		return JsonResult.success();
	}
	
	@RequestMapping(value="/remarkHomework", method=RequestMethod.POST)
	@ResponseBody
	public String remarkHomework(HttpServletRequest request, HttpSession session) {
		@SuppressWarnings("unused")
		Teacher teacher = (Teacher)session.getAttribute("user");
		String studentId = request.getParameter("studentId");
		Integer homeworkId = Integer.parseInt(request.getParameter("homeworkId"));
		Integer score = Integer.parseInt(request.getParameter("score"));
		String remark = request.getParameter("remark");
		attendCourseService.remarkHomework(studentId, homeworkId, score, remark);
		return "remark student :" + attendCourseService.getStudent(studentId).getName() + ".homework:" + attendCourseService.getHomework(homeworkId).getName() + " success";
	}
	
	@RequestMapping(value="/goHomework/{homeworkId}")
	public String viewHomework(ModelMap model, @PathVariable Integer homeworkId, @RequestParam("teachCourseId") Integer teachCourseId) {
		Homework homework = attendCourseService.getHomework(homeworkId);
		List<AttendCourse> students = attendCourseService.listAttendCourse(teachCourseId);
		List<AssignHomework> assignHomeworks = attendCourseService.listAssignHomework(homeworkId);
		List<String[]> infos = new ArrayList<>();
		for (AssignHomework assignHomework: assignHomeworks) {
			Student student = attendCourseService.getStudent(assignHomework.getStudentid());
			String[] info = {student.getId().toString(), student.getName(), assignHomework.getSubmittime().toString(),
					assignHomework.getScore()>-1?assignHomework.getScore().toString():"", assignHomework.getStudentid(), assignHomework.getRemark()};
			infos.add(info);
		}
		model.addAttribute("infos", infos);
		model.addAttribute("totalNum", students.size());
		model.addAttribute("num", assignHomeworks.size());
		model.addAttribute("homework", homework);
		return "teacherCoursePage/viewHomework";
	}
		
	@RequestMapping(value="/downloadHomework", method=RequestMethod.POST)
	public ResponseEntity<byte[]> downloadHomework(@RequestParam("studentId") String studentId, @RequestParam("homeworkId") Integer homeworkId, HttpServletRequest request) throws IOException {
		String path = homeworkPath + File.separator + "homework" + File.separator + homeworkId + File.separator;
		File[] files = new File(path).listFiles();
		String fileName = "";
		for (File file : files ) {
			if (file.getName().split("_")[0].equals(studentId)) {
				fileName = file.getName();
				break;
			}
		}
		File filepath = new File(path, fileName);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepath), headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/downloadAllHomework", method=RequestMethod.POST) 
	public ResponseEntity<byte[]> downloadAllHomework(@RequestParam("homeworkId") Integer homeworkId, HttpServletRequest request) throws IOException {
		String folderUrl = homeworkPath  + "homework" + File.separator + homeworkId;
		String zipUrl = homeworkPath  + "cache" + File.separator;
		String homeworkName = attendCourseService.getHomework(homeworkId).getName();
		File zipFile = ZipUtil.ZipFloder(folderUrl, homeworkId.toString(), zipUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", homeworkName + ".zip");
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(zipFile), headers, HttpStatus.CREATED);
		return responseEntity;
	}
	
	@RequestMapping(value="/viewHomework")
	public void viewHomework(HttpServletRequest request, HttpSession session, HttpServletResponse response, ModelMap model) throws IOException {
		String studentId = request.getParameter("studentId");
		String homeworkId = request.getParameter("homeworkId");
		String path = homeworkPath + "homework" + File.separator + homeworkId + File.separator;
		File[] files = new File(path).listFiles();
		String fileName = "";
		for (File file : files ) {
			if (file.getName().split("_")[0].equals(studentId.toString())) {
				fileName = file.getName();
				break;
			}
		}
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (fileName.isEmpty()) {
			out.write("empty");
			return;
		}
		if (!fileName.split("\\.")[1].equals("docx")) {
			out.write("nondocx");
			return;
		}
		String title = "<title>" + attendCourseService.getStudent(studentId).getName()+ "</title>";
		String form = "<form action=\"remarkHomework\" method=\"post\" style=\"text-align:center;\">"
				+ "<input type=\"hidden\" name=\"studentId\" value=\""+ studentId + "\">"
				+ "<input type=\"hidden\" name=\"homeworkId\" value=\""+ homeworkId + "\">"
				+ "<p>分数:<input type=\"number\" name=\"score\"></p>"
				+ "<p>评语:<input type=\"text\" name=\"remark\"></p>"
				+ "<p><input type=\"submit\" value=\"评价\"></p>"
				+ "</form>";
		out.write("<meta charset=\"utf-8\">");
		out.write(title);
		DOCX2HTML.docx2html(path + fileName, out, request.getServletContext().getRealPath("/") + "static" + File.separator + "cache", null);
		out.write(form);
//		out.write("success");
//		model.addAttribute("content", DOC2HTMLString.docx2html(path + fileName, request.getServletContext().getRealPath("/") + "static" + File.separator + "cache", null));
		return;
	}
	
	@RequestMapping(value="/checkHomework")
	public String checkHomework(HttpServletRequest request, ModelMap model) {
		String studentId = request.getParameter("studentId");
		Integer homeworkId = Integer.parseInt(request.getParameter("homeworkId"));
		String path = homeworkPath + File.separator + "homework" + File.separator + homeworkId + File.separator;
		File[] files = new File(path).listFiles();
		String[] sentences = null;
		for (File file : files ) {
			if (file.getName().split("_")[0].equals(studentId.toString())) {
				sentences = Duplicate.readDocx(file);
				break;
			}
		}
		Map<String, Double> check = new HashMap<>();
		for (File file : files) {
			if (file.getName().split("_")[0].equals(studentId.toString())) {
				continue;
			}
			String[] otherSentences = Duplicate.readDocx(file);
			check.put(file.getName().split("_")[1], Duplicate.checkSentences(sentences, otherSentences, 0.6));
		}
		model.addAttribute("check", check);
		model.addAttribute("length", sentences.length);
		model.addAttribute("studentId", studentId);
		return "teacherCoursePage/checkHomework";
	}
	
	@RequestMapping(value="/saveScore/{homeworkId}", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult saveHomeworkScore(HttpServletRequest request, HttpSession session, @PathVariable Integer homeworkId) throws IOException {
		Teacher teacher = (Teacher)session.getAttribute("user");
		Log log = new Log();
		log.setIp(request.getRemoteAddr());
		try {
			log.setOperatorid(teacher.getId());
			log.setContent("save score of homeworkID " + homeworkId );
		} catch (Exception e) {
			return JsonResult.fail("wrong");
		}
		log.setType(Log.SAVE_HOMEWORK_SCORE);
		Map<String, String[]> map = request.getParameterMap();
		for (Entry<String, String[]> entry: map.entrySet()) {
			if (entry.getValue() == null) {
				continue;
			}
			attendCourseService.scoreHomework(entry.getKey(), homeworkId, Integer.parseInt(entry.getValue()[0]));
		}
		logService.log(log);
		return JsonResult.success();
	}
	
	@RequestMapping(value="/resources")
	public String goResource(ModelMap model, @RequestParam("teachCourseId") Integer teachCourseId, HttpServletRequest request) {
		String path = resourcePath + File.separator + "teachCourse" + File.separator + teachCourseId + File.separator;
		File filepath = new File(path);
		if (!filepath.exists()) {
			filepath.mkdirs();
		}
		File[] files = filepath.listFiles();
		List<Object[]> resources = new ArrayList<>();
		for (File file : files) {
			String name = file.getName();
			String date = new Date(file.lastModified()).toLocaleString();
			long length = file.length();
			resources.add(new Object[] {name, date, length});
		}
		model.addAttribute("resourceNames", resources);
		model.addAttribute("teachCourseId", teachCourseId);
		return "teacherCoursePage/resource";
	}
	
	@RequestMapping(value="/uploadResource/{teachCourseId}", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult submitSource(@RequestParam("file") MultipartFile file, @PathVariable Integer teachCourseId) throws IOException {
		if (file.isEmpty()) {
			return JsonResult.fail("empty");
		}
		String path = resourcePath + File.separator + "teachCourse" + File.separator + teachCourseId + File.separator;
		String fileName = file.getOriginalFilename();
		File filepath = new File(path, fileName);
		if (!filepath.getParentFile().exists()) {
			filepath.getParentFile().mkdirs();
		}
		file.transferTo(filepath);
		return JsonResult.success();
	}
	
	@RequestMapping(value="/resource_download/{teachCourseId}")
	public ResponseEntity<byte[]> downloadResource(@PathVariable Integer teachCourseId, @RequestParam("fileName") String fileName, HttpServletRequest request) throws IOException {
		String path = resourcePath + File.separator + "teachCourse" + File.separator + teachCourseId + File.separator;
		File filepath = new File(path, fileName);
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepath), headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/studentList")
	public String studentList(ModelMap model, @RequestParam("teachCourseId") Integer teachCourseId, HttpServletRequest request) {
		List<AttendCourse> attendCourses;
		if (!request.getParameter("bigger").isEmpty()) {
			Integer score = Integer.parseInt(request.getParameter("bigger"));
			attendCourses = this.attendCourseService.getStudentFinalScoreBigger(teachCourseId, score);
		}
		if (!request.getParameter("smaller").isEmpty()) {
			Integer score = Integer.parseInt(request.getParameter("smaller"));
			attendCourses = this.attendCourseService.getStudentFinalScoreSmaller(teachCourseId, score);
		}
		attendCourses = this.attendCourseService.listAttendCourse(teachCourseId);
		List<String[]> infos = new ArrayList<>();
		for (AttendCourse attendCourse: attendCourses) {
			String[] info = {attendCourse.getStudentId(), attendCourseService.getStudent(attendCourse.getStudentId()).getName(), attendCourseService.getGrade(attendCourse.getStudentId()).getName(),
					attendCourse.getUsualScore()>-1?attendCourse.getUsualScore().toString():"", attendCourse.getHomeworkScore()>-1?attendCourse.getHomeworkScore().toString():"", 
							attendCourse.getFinalScore()>-1?attendCourse.getFinalScore().toString():""};
			infos.add(info);
		}
		model.addAttribute("studentList", infos);
		return "teacherCoursePage/studentList";
	}
		
	@RequestMapping(value="/deleteStudent", method=RequestMethod.POST)
	@ResponseBody
	public String deleteStudentFromCourse(@RequestParam("studentId") String studentId, @RequestParam("teachCourseId") Integer teachCourseId, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws IOException {
		Teacher teacher = (Teacher)session.getAttribute("user");
		Log log = new Log();
		log.setIp(request.getRemoteAddr());
		log.setType(Log.LOGOUT_STUDENT);
		log.setOperatorid(teacher.getId());
		log.setContent("delete studentID " + studentId + " in teachCourseID " + teachCourseId );
		attendCourseService.deleteStudent(studentId, teachCourseId);
		logService.log(log);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Student student = attendCourseService.getStudent(studentId);
		return student.getName();
	}
	
	@RequestMapping(value="/saveUsualScore/{teachCourseId}", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult saveUsualScore(@PathVariable Integer teachCourseId, HttpServletRequest request, HttpSession session) {
		Teacher teacher = (Teacher)session.getAttribute("user");
		Log log = new Log();
		log.setIp(request.getRemoteAddr());

		log.setOperatorid(teacher.getId());
		log.setContent("save score of teachCourseID " + teachCourseId);
		log.setType(Log.SAVE_HOMEWORK_SCORE);

		Map<String, String[]> map = request.getParameterMap();
		for (Entry<String, String[]> entry: map.entrySet()) {
			String studentId = entry.getKey();
			Integer score = Integer.parseInt(entry.getValue()[0]);
			if (!attendCourseService.changeUsualScore(studentId, teachCourseId, score)) {
				return JsonResult.fail("wrong");
			}
		}
		return JsonResult.success();
	}
	
	@RequestMapping(value="/createFinalScore", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult createFinalScore(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		Teacher teacher = (Teacher)session.getAttribute("user");
		Integer teachCourseId = Integer.parseInt(request.getParameter("teachCourseId"));
		Double usualProp = Double.parseDouble(request.getParameter("usualProp"));
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Log log = new Log();
		log.setIp(request.getRemoteAddr());
		try {
			log.setOperatorid(teacher.getId());
			log.setContent("save score of teachCourseID " + teachCourseId);
			log.setType(Log.SAVE_HOMEWORK_SCORE);
		} catch (Exception e) {
			return JsonResult.fail("wrong");
		}
		logService.log(log);
		attendCourseService.changeFinalScore(teachCourseId, usualProp);
		return JsonResult.success();
	}
	
	@RequestMapping(value="/courseMessage")
	public String courseMessage(@RequestParam("teachCourseId") Integer teachCourseId, ModelMap model) {
		List<Message> messages = teachCourseService.getMessageListOfTeachCourse(teachCourseId);
		model.addAttribute("messages", messages);
		return "teacherCoursePage/message";
	}
	
	@RequestMapping(value="/createMessage", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult createMessage(HttpServletRequest request, HttpSession session) {
		@SuppressWarnings("unused")
		Teacher teacher = (Teacher)session.getAttribute("user");
		Integer teachCourseId = Integer.parseInt(request.getParameter("teachCourseId"));
		String Name = request.getParameter("name");
		String info = request.getParameter("info");
		Message message = new Message();
		message.setName(Name);
		message.setInfo(info);
		message.setTeachcourseid(teachCourseId);
		if (messageService.createMessage(message)) {
			return JsonResult.success();
		}
		return JsonResult.fail("wrong");
	}
	
	@RequestMapping(value="/deleteMessage", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult deleteMessage(HttpServletRequest request, HttpSession session){
		Teacher teacher = (Teacher)session.getAttribute("user");
		Integer id = Integer.parseInt(request.getParameter("messageId"));
		Log log = new Log();
		log.setIp(request.getRemoteAddr());
		log.setOperatorid(teacher.getId());
		log.setContent("delete messageID " + id );
		log.setType(Log.DELETE_MESSAGE);
		logService.log(log);
		if (messageService.deleteMessage(id)) {
			return JsonResult.success();
		}
		return JsonResult.fail("wrong");
	}
	
	@RequestMapping(value="/editorMessage", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult editorMessage(HttpServletRequest request, HttpSession session) {
		@SuppressWarnings("unused")
		Teacher teacher = (Teacher)session.getAttribute("user");
		Integer id = Integer.parseInt(request.getParameter("messageId"));
		String name = request.getParameter("name");
		String info = request.getParameter("info");
//		Log log = new Log();
//		log.setIP(request.getRemoteAddr());
//		log.setContent("editor message id," + id + "by teacher," + teacher.getId());
		Message message = new Message();
		message.setId(id);
		message.setName(name);
		message.setInfo(info);
		if (messageService.changeMessage(message)) {
			return JsonResult.success();
		}
		return JsonResult.fail("wrong");
	}

	@RequestMapping(value="/discussions")
	public String toDiscussion(@RequestParam("teachCourseId") Integer teachCourseId, ModelMap model) {
		List<Discussion> discussions = this.teachCourseService.getDiscussionOfTeachCourse(teachCourseId);
		List<String[]> lists = new ArrayList<>();
		for (Discussion discussion: discussions) {
			String[] info = {this.discussionService.getStudent(discussion).getName(), discussion.getDatetime().toString(), discussion.getContent()};
			lists.add(info);
		}
		model.addAttribute("discussions", lists);
		return "teacherCoursePage/discussionPage";
	}
}
