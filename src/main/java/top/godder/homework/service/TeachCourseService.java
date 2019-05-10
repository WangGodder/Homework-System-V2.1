package top.godder.homework.service;

import top.godder.homework.domain.*;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/8
 */
public interface TeachCourseService {
    List<TeachCourse> getTeachCourseByStudent(String studentId);

    List<TeachCourse> getTeachCourseByTeacher(String teacherId);

    List<TeachCourse> getEnterableTeachCourse(Integer gradeId);

    List<Course> getAllCourse();

    List<Grade> getAllGrade();

    String[] getTeachCourseInfo(TeachCourse course, Student student);

    boolean attendTeachCourse(Student student, Integer teachCourseId);

    boolean createTeachCourse(TeachCourse course);

    List<String[]> enterableCourseInfos(Student student);

    TeachCourse getTeachCourse(Integer teachCourseId);

    Course getCourse(TeachCourse teachCourse);

    Grade getGrade(TeachCourse teachCourse);

    List<Message> getMessageListOfTeachCourse(Integer teachCourseId);

    List<Homework> getHomrworkListOfTeachCourse(Integer teachCourseId);

    List<AttendCourse> getAttendCourseOfTeachCourse(Integer teachCourseId);

    List<Discussion> getDiscussionOfTeachCourse(Integer teachCourseId);
}
