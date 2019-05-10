package top.godder.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import top.godder.homework.domain.*;
import top.godder.homework.service.LoginService;
import top.godder.homework.service.MainPageService;
import top.godder.homework.service.RegisterService;
import top.godder.homework.vo.JsonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@SessionAttributes("user")
public class LoginController {
	@Autowired
	private RegisterService registerService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private MainPageService mainPageService;

	@RequestMapping(value="/")
	public String loginPage() {
		return "login";
	}
	
	@RequestMapping(value="/administrator")
	public String loginAdm() {
		return "adm_login";
	}
	
	@RequestMapping(value="/register")
	public String register(ModelMap model) {
		List<Grade> grades = this.registerService.getGradeList();
		model.addAttribute("grades", grades);
		return "register";
	}
	
	@RequestMapping(value="/login_student", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult studentLogin(HttpServletRequest request, HttpSession session) throws IOException {
		String id, password;
		try {
			id = request.getParameter("id");
			password = request.getParameter("password");
		} catch (Exception ex) {
			return JsonResult.fail("id and password have not pass");
		}
		Student student = loginService.loginStudent(id, password);
		if (student == null) {
			return JsonResult.fail("id or password is wrong");
		}
//		try {
//			student = studentDAOImpl.getStudent(id, password);
//		} catch (EmptyResultDataAccessException ex) {
//			String errorMessage = "�û������������(error)";
////			out.write(errorMessage);
//			model.addObject("error", errorMessage);
////			model.setViewName("login");
//			out.write("wrong");
//			return null;
//		}
		session.setAttribute("user", student);
		return JsonResult.success();
	}
	
	@RequestMapping(value="/login_teacher", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult teacherLogin(HttpServletRequest request, HttpSession session) throws IOException {
		String id, password;
		try {
			id = request.getParameter("id");
			password = request.getParameter("password");
		} catch (Exception ex) {
			return JsonResult.fail("id and password have not pass");
		}
		Teacher teacher = loginService.loginTeacher(id, password);
		if (teacher == null) {
			return JsonResult.fail("id or password is wrong");
		}
//		try {
//			teacher = teacherDAOImpl.getTeacher(id, password);
//		} catch (EmptyResultDataAccessException ex) {
//			String errorMessage = "�û������������(error)";
////			out.write(errorMessage);
//			model.addObject("error", errorMessage);
////			model.setViewName("login");
//			out.write("wrong");
//			return null;
//		}
		
		session.setAttribute("user", teacher);
		return JsonResult.success();
	}
	
	@RequestMapping(value="/login_administrator")
	public ModelAndView administratorLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		ModelAndView model = new ModelAndView();
		String id, password;
		try {
			id = request.getParameter("id");
			password = request.getParameter("password");
		} catch (Exception ex) {
			model.addObject("error", ex.getMessage());
			ex.printStackTrace();
			model.setViewName("error/login");
			return model;
		}
		Administrator administrator = loginService.loginAdmin(id, password);
		if (administrator == null) {
			String errorMessage = "id or password is wrong";
			model.addObject("error", errorMessage);
			out.write("wrong");
			return null;
		}

//		try {
//			administrator = administratorDAOImpl.getAdministrator(id, password);
//		} catch (EmptyResultDataAccessException ex) {
//			String errorMessage = "�û������������(error)";
////			out.write(errorMessage);
//			model.addObject("error", errorMessage);
////			model.setViewName("login");
//			out.write("wrong");
//			return null;
//		}
		
		session.setAttribute("user", administrator);
		model.addObject("user", administrator);
		out.write("success");
		return null;
	}
	
	@RequestMapping(value="/main_student")
	public String mainStudent(ModelMap model, HttpSession session) {
		try {
			Student student = (Student)session.getAttribute("user");
			Grade grade = mainPageService.getGrade(student.getGradeId());
			String gradeName = grade.getName();
			model.addAttribute("gradeName", gradeName);
		} catch (Exception exception) {
			model.addAttribute("error", exception.getMessage());
			return "error/login";
		}
		List<Notice> notices = mainPageService.getNoticeList();
		model.addAttribute("notices", notices);

		return "main_student";
	}
	
	@RequestMapping(value="/main_teacher")
	public ModelAndView mainTeacher(HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName("main_teacher");
		try {
			Teacher teacher = (Teacher)session.getAttribute("user");
			if (teacher == null) {
				throw new NullPointerException();
			}
		} catch (Exception exception) {
			model.addObject("error", exception.getMessage());
			model.setViewName("error/login");
		}
		List<Notice> notices = mainPageService.getNoticeList();
		model.addObject("notices", notices);
		return model;
	}
	
	@RequestMapping(value="/main_administrator")
	public ModelAndView mainAdministrator(HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName("main_administrator");
		try {
			Administrator teacher = (Administrator)session.getAttribute("user");
			if (teacher == null) {
				throw new NullPointerException();
			}
		} catch (Exception exception) {
			model.addObject("error", exception.getMessage());
			model.setViewName("error/login");
		}
		List<Notice> notices = mainPageService.getNoticeList();
		model.addObject("notices", notices);
		return model;
	}

	@RequestMapping(value="/logout")
	public String logout(ModelMap model, HttpSession session) {
//		ModelAndView model = new ModelAndView();
		model.clear();
		session.removeAttribute("user");
		session.invalidate();
//		model.setViewName("login");
		return "login";
	}
	
	@RequestMapping(value="/registerStudent", method=RequestMethod.POST)
	@ResponseBody
	public JsonResult registerStudent(HttpServletRequest request, HttpSession session) throws IOException {
		String name = request.getParameter("name");
		String studentId = request.getParameter("studentId");
		String password = request.getParameter("password");
		Integer gradeId = Integer.parseInt(request.getParameter("gradeId"));
		String tel = request.getParameter("tel");
		Student student = new Student();
		student.setName(name);
		student.setId(studentId);
		student.setGradeId(gradeId);
		student.setPassword(password);
		student.setTel(tel);
		System.out.println(studentId);
		if (!registerService.registerStudent(student)) {
			return JsonResult.fail("id has exist");
		}
		session.setAttribute("user", student);
		return JsonResult.success();
	}
}
