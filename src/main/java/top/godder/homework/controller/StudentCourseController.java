package top.godder.homework.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.godder.homework.domain.AssignHomework;
import top.godder.homework.domain.Discussion;
import top.godder.homework.domain.Homework;
import top.godder.homework.domain.Student;
import top.godder.homework.service.AttendCourseService;
import top.godder.homework.service.DiscussionService;
import top.godder.homework.service.SensitiveWordService;
import top.godder.homework.service.TeachCourseService;

import top.godder.homework.vo.JsonResult;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/student_teachCourse")
public class StudentCourseController {
	@Autowired
	private TeachCourseService teachCourseService;
	@Autowired
	private AttendCourseService attendCourseService;
	@Autowired
	private DiscussionService discussionService;
	@Autowired
	private SensitiveWordService sensitveWordService;
	@Value(value = "${path.resource}")
	private String resourcePath;
	@Value(value = "${path.homework}")
	private String homeworkPath;
	
	@RequestMapping(value="/homeworks", method=RequestMethod.GET)
	public String homeworks(ModelMap model, @RequestParam("teachCourseId") Integer teachCourseId, HttpSession session) {
		Student student = (Student)session.getAttribute("user");
		List<Homework> homeworks = this.teachCourseService.getHomrworkListOfTeachCourse(teachCourseId);
		List<String[]> homeworkInfo = new ArrayList<>();
		for (Homework homework: homeworks) {
			boolean isDeadline =System.currentTimeMillis() > homework.getDeadline().getTime();
			AssignHomework assignHomework;
			String[] info;

			assignHomework = this.attendCourseService.getAssignHomework(student, teachCourseId, homework.getId());
			if (assignHomework == null) {
				info = new String[] {homework.getName(), homework.getInfo(), homework.getDeadline().toString(),"",
						"", homework.getId().toString(), isDeadline?"true":"false", ""};
				homeworkInfo.add(info);
				continue;
			}
			info = new String[]{homework.getName(), homework.getInfo(), homework.getDeadline().toString(), assignHomework.getSubmittime()==null?"":assignHomework.getSubmittime().toString(),
					assignHomework.getScore()>-1?assignHomework.getScore().toString():"", homework.getId().toString(), isDeadline?"true":"false", assignHomework.getRemark()};
			homeworkInfo.add(info);
		}
		model.addAttribute("homeworkInfos", homeworkInfo);
		return "studentCoursePage/homeworkPage";
	}
	
	@RequestMapping(value="/goHomework/{homeworkId}")
	public String goHomework(ModelMap model, @PathVariable Integer homeworkId, @RequestParam("teachCourseId") Integer teachCourseId, HttpSession session) {
		@SuppressWarnings("unused")
		Student student;
		try {
			student = (Student)session.getAttribute("user");
		} catch (Exception exception) {
			return "error/error";
		}
		Homework homework = attendCourseService.getHomework(homeworkId);
		model.addAttribute("homework", homework);
		return "studentCoursePage/assignHomework";
	}
	
	@RequestMapping(value="/assignHomework/{hwId}", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult assignHomework(@RequestParam("file") MultipartFile file, @RequestParam("teachCourseId") Integer teachCourseId, @PathVariable String hwId, HttpSession session, HttpServletRequest request) throws IOException {
		if (file.isEmpty()) {
			return JsonResult.fail("homework file is empty");
		}
		Integer homeworkId = Integer.parseInt(hwId);
		Homework homework = this.attendCourseService.getHomework(homeworkId);
		Student student = (Student)session.getAttribute("user");
		String path = homeworkPath + "homework" + File.separator + homeworkId + File.separator;
		String fileFormat = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
		if (homework.getFormat() != null && !fileFormat.equals(homework.getFormat())) {
            return JsonResult.fail("format is wrong");
		}
		String fileName = student.getId() + "_" + student.getName() + "_" + homework.getName() + "." + fileFormat;
		File filepath = new File(path, fileName);
		if(!filepath.getParentFile().exists()) {
			filepath.getParentFile().mkdirs();
		}
		// delete homework submit before
		File folder = new File(path);
		for (File f: folder.listFiles()) {
			if (f.getName().split("_")[0].equals(student.getId())) {
				f.delete();
			}
		}
		file.transferTo(filepath);
		if (attendCourseService.submitHomework(student, teachCourseId, homeworkId)) {
            return JsonResult.success();
        }
		return JsonResult.fail("homework submit fail");
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
	
	@RequestMapping(value="/resources", method=RequestMethod.GET)
	public String toResource(@RequestParam("teachCourseId") Integer teachCourseId, HttpServletRequest request, ModelMap model) {
		String path = resourcePath + "teachCourse" + File.separator + teachCourseId + File.separator;
//		String path = request.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "teachCourse" + File.separator + teachCourseId + File.separator;
		File filepath = new File(path);
		if (!filepath.exists()) {
			filepath.mkdirs();
		}
		File[] files = filepath.listFiles();
		List<Object[]> resources = new ArrayList<>();
		
		for (File file : files) {
			String name = file.getName();
			String date = new Date(file.lastModified()).toString();
			long length = file.length();
			resources.add(new Object[] {name, date, length});
		}
		model.addAttribute("resourceNames", resources);
		model.addAttribute("teachCourseId", teachCourseId);
		return "studentCoursePage/resource";
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
		return "studentCoursePage/discussionPage";
	}
	
	@RequestMapping(value="/submitDiscussion")
	@ResponseBody
	public JsonResult submitDiscussion(@RequestParam("teachCourseId") Integer teachCourseId, @RequestParam("discussion") String discussion, HttpSession session) {
		if (sensitveWordService.containSensitiveWord(discussion)) {
			return JsonResult.fail("discussion contain sensitive word");
		}
		Discussion dis = new Discussion();
		dis.setContent(discussion);
		dis.setPublisherid(((Student)session.getAttribute("user")).getId());
		dis.setTeachcourseid(teachCourseId);
		this.discussionService.createDiscussion(dis);
		return JsonResult.success();
	}
}
