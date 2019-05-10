package top.godder.homework.service;

import top.godder.homework.domain.Course;
import top.godder.homework.domain.Grade;
import top.godder.homework.domain.Notice;
import top.godder.homework.domain.Teacher;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/10
 */
public interface AdminService {
    List<Course> listAllCourse();

    boolean createCourse(Course course);

    boolean changeCourse(Course course);

    boolean deleteCourse(Integer courseId);

    List<Teacher> listAllTeacher();

    boolean changeTeacherInfo(String teacherId, String info);

    boolean createTeacher(Teacher teacher);

    List<Grade> listAllGrade();

    boolean createGrade(Grade grade);

    boolean changeGrade(Grade grade);

    boolean deleteGrade(Integer gradeId);

    Integer getStudentNumByGrade(Integer gradeId);

    List<Notice> listNotice();

    boolean deleteNotice(Integer noticeId);

    boolean createNotice(Notice notice);
}
