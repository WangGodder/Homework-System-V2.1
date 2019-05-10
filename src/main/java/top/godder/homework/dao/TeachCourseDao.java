package top.godder.homework.dao;

import top.godder.homework.domain.TeachCourse;

import java.util.Date;
import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/8
 */
public interface TeachCourseDao {
    boolean createTeachCourse(TeachCourse course);
    TeachCourse getTeachCourse(Integer courseId, String teacherId, Integer gradeId);
    TeachCourse getTeachCourse(Integer id);
    boolean alterTeachCourse(TeachCourse teachCourse);
    List<TeachCourse> getTeachCourseByCourse(Integer courseId);
    List<TeachCourse> getTeachCourseByTeacherId(String teacherId);
    List<TeachCourse> getTeachCourseByGrade(Integer gradeId);
    List<TeachCourse> getTeachCourseByStudent(String studentId);
    List<TeachCourse> getAllTeachCourse();
    List<TeachCourse> getEnterableTeachCourse(Integer gradeId);
    boolean deleteTeachCourse(Integer id);
    boolean deleteCourse(Integer courseId);
}
