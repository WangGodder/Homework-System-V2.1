package top.godder.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.godder.homework.domain.*;
import top.godder.homework.service.AdminService;
import top.godder.homework.service.LogService;
import top.godder.homework.service.SensitiveWordService;
import top.godder.homework.vo.JsonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdministratorController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private LogService logService;
	@Autowired
	private SensitiveWordService sensitiveWordService;
	
	@RequestMapping(value="/createCourse")
	public String goCreateCourse(ModelMap model, HttpSession session) {
		try {
			@SuppressWarnings("unused")
			Administrator administrator = (Administrator)session.getAttribute("user");
		} catch (Exception e) {
			return "error/error";
		}
		List<Course> courses = adminService.listAllCourse();
		model.addAttribute("courses", courses);
		return "admPage/createCourse";
	}
	
	@RequestMapping(value="/createNewCourse", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult createCourse(HttpServletRequest request, HttpSession session) throws IOException {
		Administrator administrator = new Administrator();
		try {
			administrator = (Administrator)session.getAttribute("user");
		} catch (Exception e) {
			return JsonResult.fail("permission deny");
		}
		String courseName = request.getParameter("courseName");
		String courseInfo = request.getParameter("courseInfo");
		Integer type = Integer.parseInt(request.getParameter("courseType"));
		Course course = new Course();
		course.setName(courseName);
		course.setInfo(courseInfo);
		course.setType(type);
		if (!adminService.createCourse(course)) {
			return JsonResult.fail("wrong");
		}
		Log log = new Log();
		log.setIp(request.getRemoteAddr());
		log.setType(Log.CREATE_COURSE);
		log.setOperatorid(administrator.getId());
		log.setContent("create course " + courseName);
		logService.log(log);
		return JsonResult.success();
	}
	
	@RequestMapping(value="/viewCourse")
	public String viewCourse(ModelMap model) {
		List<Course> courses = adminService.listAllCourse();
		model.addAttribute("courses", courses);
		return "admPage/viewCourse";
	}
	
	@RequestMapping(value="/editorCourse", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult editorCourse(HttpServletRequest request, HttpSession session) {
		Integer courseId = Integer.parseInt(request.getParameter("courseId"));
		String name = request.getParameter("name");
		String info = request.getParameter("info");
		Integer type = Integer.parseInt(request.getParameter("type"));
		Administrator administrator = (Administrator)session.getAttribute("user");

		Course course = new Course();
		course.setId(courseId);
		course.setInfo(info);
		course.setName(name);
		course.setType(type);
		if (adminService.changeCourse(course)) {
			Log log = new Log();
			log.setIp(request.getRemoteAddr());
			log.setOperatorid(administrator.getId());
			log.setType(Log.EDITOR_COURSE);
			log.setContent("editor courseId " + courseId);
			logService.log(log);
			return JsonResult.success();
		}
		return JsonResult.fail("wrong");
	}
	
	@RequestMapping(value="/deleteCourse", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult deleteCourse(HttpServletRequest request, HttpSession session) {
		Integer courseId = Integer.parseInt(request.getParameter("courseId"));
		Administrator administrator = (Administrator)session.getAttribute("user");
		if (adminService.deleteCourse(courseId)) {
			Log log = new Log();
			log.setIp(request.getRemoteAddr());
			log.setOperatorid(administrator.getId());
			log.setType(Log.DELETE_COURSE);
			log.setContent("delete courseId " + courseId);
			logService.log(log);
			return JsonResult.success();
		}

//		homeworkDAOImpl.deleteCourse(courseId);
//		attendCourseDAOImpl.deleteCourse(courseId);
//		teachCourseDAOImpl.deleteCourse(courseId);
//		courseDAOImpl.deleteCourse(courseId);
		return JsonResult.fail("wrong");
	}
	
	@RequestMapping(value="/viewLog")
	public String viewLog(ModelMap model) {
		List<Log> logs = logService.listAllLog();
		model.addAttribute("logs", logs);
		return "admPage/viewLog";
	}
	
	@RequestMapping(value="/viewSensitiveWord")
	public String viewSensitiveWord(ModelMap model) {
		List<String> words = sensitiveWordService.listSensitiveWords();
		model.addAttribute("words", words);
		return "admPage/viewSensitiveWord";
	}
	
	@RequestMapping(value="/addSensitiveWord", method=RequestMethod.POST)
	public void addSensitiveWord(@RequestParam("word") String word, HttpSession session, HttpServletResponse response) throws IOException {
		Administrator administrator = (Administrator)session.getAttribute("user");
		try {
			@SuppressWarnings("unused")
			String id = administrator.getId();
		} catch (Exception e) {
			return;
		}
		sensitiveWordService.addSensitiveWord(word);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.write("success");
	}
	
	@RequestMapping(value="/viewTeacher")
	public String viewTeacher(ModelMap model) {
		List<Teacher> teachers = adminService.listAllTeacher();
		model.addAttribute("teachers", teachers);
		return "admPage/viewTeacher";
	}
	
	@RequestMapping(value="/editorTeacherInfo")
	@ResponseBody
	public JsonResult editorTeacherInfo(HttpServletRequest request, HttpSession session) {
		Administrator administrator = (Administrator)session.getAttribute("user");
		String teacherId = request.getParameter("teacherId");
		String info = request.getParameter("info");
		if (adminService.changeTeacherInfo(teacherId, info)) {
			Log log = new Log();
			log.setType(Log.EDITOR_TEACHER);
			log.setIp(request.getRemoteAddr());
			log.setContent("edtior teacherId " + teacherId);
			log.setOperatorid(administrator.getId());
			logService.log(log);
			return JsonResult.success();
		}
		return JsonResult.fail("wrong");
	}
	
	@RequestMapping(value="/addTeacher", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult addTeacher(HttpServletRequest request, HttpSession session) {
		Log log = new Log();
		Administrator administrator = (Administrator)session.getAttribute("user");
		String teacherId = request.getParameter("teacherId");
		String name = request.getParameter("name");
		String info = request.getParameter("info");
		Teacher teacher = new Teacher();
		teacher.setId(teacherId);
		teacher.setPassword(teacherId);
		teacher.setName(name);
		teacher.setInfo(info);

		if (adminService.createTeacher(teacher)) {
			log.setIp(request.getRemoteAddr());
			log.setOperatorid(administrator.getId());
			log.setType(Log.CREATE_TEACHER);
			log.setContent("create teacherId " + teacherId);
			logService.log(log);
			return JsonResult.success();
		}
		return JsonResult.fail("wrong");
	}
			
	@RequestMapping(value="/viewGrade")
	public String viewGrade(ModelMap model) {
		List<Grade> grades = adminService.listAllGrade();
		List<Integer> nums = new ArrayList<>();
		model.addAttribute("grades", grades);
		for (Grade grade: grades) {
			nums.add(adminService.getStudentNumByGrade(grade.getId()));
		}
		model.addAttribute("nums", nums);
		return "admPage/viewGrade";
	}
	
	@RequestMapping(value="/editorGrade", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult edtiorGrade(HttpServletRequest request, HttpSession session) {
		Log log = new Log();
		Administrator administrator = (Administrator)session.getAttribute("user");
		Integer gradeId = Integer.parseInt(request.getParameter("gradeId"));
		String name = request.getParameter("name");
		String info = request.getParameter("info");

		Grade grade = new Grade();
		grade.setId(gradeId);
		grade.setInfo(info);
		grade.setName(name);
		if (adminService.changeGrade(grade)) {
			log.setType(Log.EDITOR_GRADE);
			log.setIp(request.getRemoteAddr());
			log.setContent("editor gradeId " + gradeId);
			log.setOperatorid(administrator.getId());
			logService.log(log);
			return JsonResult.success();
		}
		return JsonResult.fail("wrong");
	}
	
	@RequestMapping(value="/addGrade", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult addGrade(HttpServletRequest request, HttpSession session) {
		Log log = new Log();
		Administrator administrator = (Administrator)session.getAttribute("user");
		String name = request.getParameter("name");
		String info =  request.getParameter("info");
		Grade grade = new Grade();
		grade.setName(name);
		grade.setInfo(info);

		if (adminService.createGrade(grade)) {
			log.setType(Log.CREATE_GRADE);
			log.setIp(request.getRemoteAddr());
			log.setOperatorid(administrator.getId());
			log.setContent("create gradeName " + name);
			logService.log(log);
			return JsonResult.success();
		}
		return JsonResult.fail("wrong");
	}
	
	@RequestMapping(value="viewNotice")
	public String viewNotice(ModelMap model) {
		List<Notice> notices = adminService.listNotice();
		model.addAttribute("notices", notices);
		return "admPage/viewNotice";
	}
	
	@RequestMapping(value="deleteNotice")
	@ResponseBody
	public JsonResult deleteNotice(HttpServletRequest request, HttpSession session) {
		Log log = new Log();
		Administrator administrator = (Administrator)session.getAttribute("user");
		Integer noticeId = Integer.parseInt(request.getParameter("noticeId"));

		if (adminService.deleteNotice(noticeId)) {
			log.setContent("delete noticeId " + noticeId);
			log.setIp(request.getRemoteAddr());
			log.setOperatorid(administrator.getId());
			log.setType(Log.DELETE_NOTICE);
			logService.log(log);
			return JsonResult.success();
		}
		return JsonResult.fail("wrong");
	}
	
	@RequestMapping(value="addNotice")
	@ResponseBody
	public JsonResult addNotice(HttpServletRequest request, HttpSession session) {
		Log log = new Log();
		Administrator administrator = (Administrator)session.getAttribute("user");
		String name = request.getParameter("name");
		String info = request.getParameter("info");
		Notice notice = new Notice();
		notice.setInfo(info);
		notice.setName(name);

		if (adminService.createNotice(notice)) {
			log.setIp(request.getRemoteAddr());
			log.setOperatorid(administrator.getId());
			log.setType(Log.CREATE_NOTICE);
			log.setContent("create noticeName " + name);
			logService.log(log);
			return JsonResult.success();
		}
		return JsonResult.fail("wrong");
	}
}
