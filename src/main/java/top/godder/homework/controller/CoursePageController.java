package top.godder.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.godder.homework.domain.*;
import top.godder.homework.service.DiscussionService;
import top.godder.homework.service.LogService;
import top.godder.homework.service.TeachCourseService;
import top.godder.homework.vo.JsonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CoursePageController {
    @Autowired
    private TeachCourseService teachCourseService;
	@Autowired
	private LogService logService;
	@Autowired
	private DiscussionService discussionService;
	
	@RequestMapping(value="/studentCourse")
	public String studentCourse(ModelMap model, HttpSession session) {
		Student student = (Student)session.getAttribute("user");
		List<TeachCourse> courses = teachCourseService.getTeachCourseByStudent(student.getId());
		List<String[]> infos = new ArrayList<>();
		for (TeachCourse course: courses) {
            infos.add(teachCourseService.getTeachCourseInfo(course, student));
		}
		model.addAttribute("studentCourses", infos);
		return "subpage/studentCourse";
	}
	
	@RequestMapping(value="/addCourse")
	public String addCourse(ModelMap model, HttpSession session) {
		Student student = (Student)session.getAttribute("user");
		List<TeachCourse> courses = teachCourseService.getEnterableTeachCourse(student.getGradeId());

		List<String[]> infos = teachCourseService.enterableCourseInfos(student);
		model.addAttribute("nonAttendCourses", infos);
		return "subpage/addCourse";
	}
	
	@RequestMapping(value="/attendCourse")
    @ResponseBody
	public JsonResult attendCourse(@RequestParam("teachCourseId") Integer teachCourseId, HttpSession session) {
		Student student = (Student)session.getAttribute("user");
		if (!this.teachCourseService.attendTeachCourse(student, teachCourseId)) {
			return JsonResult.fail("can't attend course, because permission not enough");
		}
        return JsonResult.success();
	}
	
	@RequestMapping(value="/student_teachCourse/{courseId}")
	public String toTeachCoursePage(ModelMap model, @PathVariable Integer courseId, HttpSession session) {
		try {
			@SuppressWarnings("unused")
			Student student = (Student)session.getAttribute("user");
		}
		catch (Exception ex) {
			return "error/error";
		}
		TeachCourse teachCourse = this.teachCourseService.getTeachCourse(courseId);
		Course course = this.teachCourseService.getCourse(teachCourse);
		List<Message> messages = this.teachCourseService.getMessageListOfTeachCourse(courseId);
		List<Homework> homeworks = this.teachCourseService.getHomrworkListOfTeachCourse(courseId);
		model.addAttribute("teachCourse", teachCourse);
		model.addAttribute("course", course);
		model.addAttribute("messages", messages);
		model.addAttribute("homeworks", homeworks);
		return "coursePage";
	}
	
//	private List<String[]> enterableCourseInfos(List<TeachCourse> courses, String studentId) {
//		List<String[]> infos = new ArrayList<>();
//		for (TeachCourse course: courses) {
//			if (!this.attendCourseDAOImpl.isAttend(studentId, course.getId())) {
//				Course c = this.courseDAOImpl.getCourse(course.getCourseId());
//				String teacherName = this.teacherDAOImpl.getName(course.getTeacherId());
//				String gradeName = this.gradeDAOImpl.getName(course.getGradeId());
//				String[] info = {c.getName(), c.getId().toString(), course.printSemestor(), c.printType(), teacherName, gradeName, course.getIsPublic().toString(), course.getId().toString()};
//				infos.add(info);
//			}
//		}
//		return infos;
//	}
	
	@RequestMapping(value="/teacherCourse")
	public String teacherCourse(ModelMap model, HttpSession session) {
		Teacher teacher = (Teacher)session.getAttribute("user");
		List<TeachCourse> courses = teachCourseService.getTeachCourseByTeacher(teacher.getId());
		List<String[]> infos = new ArrayList<>();
		for (TeachCourse course: courses) {
			Course c = teachCourseService.getCourse(course);
			Grade grade = teachCourseService.getGrade(course);
			String[] info = {c.getName(), c.getId().toString(), course.printSemestor(), grade.getName(), course.getIsPublic().toString(), course.getId().toString()};
			infos.add(info);
		}
		model.addAttribute("teacherCourses", infos);
		return "subpage/teacherCourse";
	}
	
	@RequestMapping(value="/teachCourse")
	public String goTeachCourse(ModelMap model, HttpSession session) {
		try {
			@SuppressWarnings("unused")
			Teacher teacher = (Teacher)session.getAttribute("user");
		} catch (Exception e) {
			return "error/error";
		}
		List<Course> courses = teachCourseService.getAllCourse();
		List<Grade> grades = teachCourseService.getAllGrade();
		model.addAttribute("courses", courses);
		model.addAttribute("grades", grades);
		return "subpage/teachCourse";
	}
	
	@RequestMapping(value="/teachNewCourse")
	@ResponseBody
	public JsonResult teachCourse(HttpServletRequest request, HttpSession session) throws IOException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Teacher teacher = (Teacher)session.getAttribute("user");
		Integer courseId = Integer.parseInt(request.getParameter("courseId"));
		Integer gradeId = Integer.parseInt(request.getParameter("gradeId"));
		Date date = null;
		try {
			date =  simpleDateFormat.parse(request.getParameter("date"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Boolean ispublic = Boolean.parseBoolean(request.getParameter("ispublic"));
		TeachCourse teachCourse = new TeachCourse();
		teachCourse.setCourseId(courseId);
		teachCourse.setGradeId(gradeId);
		teachCourse.setIsPublic(ispublic);
		teachCourse.setTeacherId(teacher.getId());
		teachCourse.setSemestor(date);
		if (this.teachCourseService.createTeachCourse(teachCourse)) {
			Log log = new Log();
			log.setIp(request.getRemoteAddr());
			log.setContent("teach course," + courseId +", on grade ,"+ gradeId + ", by ," + teacher.getId());
			log.setOperatorid(teacher.getId());
			log.setType(Log.CREATE_COURSE);
			logService.log(log);
			return JsonResult.success();
		}
		return JsonResult.fail("you have teach this course");
	}
	
	@RequestMapping(value="/teacher_teachCourse/{courseId}")
	public String toTeacherCoursePage(ModelMap model, @PathVariable Integer courseId, HttpSession session) {
		try {
			@SuppressWarnings("unused")
			Teacher teacher = (Teacher)session.getAttribute("user");
		}
		catch (Exception ex) {
			return "error/error";
		}
		TeachCourse teachCourse = teachCourseService.getTeachCourse(courseId);
		Course course = this.teachCourseService.getCourse(teachCourse);
		Grade grade = this.teachCourseService.getGrade(teachCourse);
		List<Message> messages = this.teachCourseService.getMessageListOfTeachCourse(courseId);
		List<Homework> homeworks = this.teachCourseService.getHomrworkListOfTeachCourse(courseId);
		List<AttendCourse> attendCourses = this.teachCourseService.getAttendCourseOfTeachCourse(courseId);
		List<Discussion> discussions = this.teachCourseService.getDiscussionOfTeachCourse(courseId);
		List<String[]> lists = new ArrayList<>();
		for (Discussion discussion: discussions) {
			String[] info = {this.discussionService.getStudent(discussion).getName(), discussion.getDatetime().toString(), discussion.getContent()};
			lists.add(info);
		}
		model.addAttribute("teachCourse", teachCourse);
		model.addAttribute("course", course);
		model.addAttribute("grade", grade);
		model.addAttribute("messages", messages);
		model.addAttribute("studentNum", attendCourses.size());
		model.addAttribute("homeworks", homeworks);
		model.addAttribute("discussions", lists);
		return "teacherCoursePage";
	}
	
}
