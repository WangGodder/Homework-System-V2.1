package top.godder.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.godder.homework.dao.*;
import top.godder.homework.domain.*;
import top.godder.homework.service.AdminService;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/10
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private AttendCourseDao attendCourseDao;
    @Autowired
    private AssignHomeworkDao assignHomeworkDao;
    @Autowired
    private HomeworkDao homeworkDao;
    @Autowired
    private TeachCourseDao teachCourseDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private GradeDao gradeDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private NoticeDao noticeDao;

    @Override
    public List<Course> listAllCourse() {
        return courseDao.getAllCourse();
    }

    @Override
    public boolean createCourse(Course course) {
        return courseDao.createCourse(course);
    }

    @Override
    public boolean changeCourse(Course course) {
        return courseDao.changeCourse(course);
    }

    @Override
    public boolean deleteCourse(Integer courseId) {
        return homeworkDao.deleteCourse(courseId) & attendCourseDao.deleteCourse(courseId) & teachCourseDao.deleteCourse(courseId) & courseDao.deleteCourse(courseId);
    }

    @Override
    public List<Teacher> listAllTeacher() {
        return teacherDao.getAllTeacher();
    }

    @Override
    public boolean changeTeacherInfo(String teacherId, String info) {
        Teacher teacher = new Teacher();
        teacher.setId(teacherId);
        teacher.setInfo(info);
        return teacherDao.alterTeacher(teacher);
    }

    @Override
    public boolean createTeacher(Teacher teacher) {
        return teacherDao.createTeacher(teacher);
    }

    @Override
    public List<Grade> listAllGrade() {
        return gradeDao.getAllGrade();
    }

    @Override
    public boolean createGrade(Grade grade) {
        return gradeDao.createGrade(grade);
    }

    @Override
    public boolean changeGrade(Grade grade) {
        return gradeDao.alterInfo(grade.getId(), grade.getInfo()) & gradeDao.alterName(grade.getId(), grade.getInfo());
    }

    @Override
    public boolean deleteGrade(Integer gradeId) {
        return gradeDao.deleteGrade(gradeId);
    }

    @Override
    public Integer getStudentNumByGrade(Integer gradeId) {
        return (int)studentDao.getStudentNumByGrade(gradeId);
    }

    @Override
    public List<Notice> listNotice() {
        return noticeDao.getAllNotice();
    }

    @Override
    public boolean deleteNotice(Integer noticeId) {
        return noticeDao.deleteNotice(noticeId);
    }

    @Override
    public boolean createNotice(Notice notice) {
        return noticeDao.createNotice(notice);
    }
}
