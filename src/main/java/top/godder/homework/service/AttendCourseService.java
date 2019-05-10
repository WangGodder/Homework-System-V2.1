package top.godder.homework.service;

import top.godder.homework.domain.*;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/9
 */
public interface AttendCourseService {
    List<AttendCourse> listAttendCourse(Integer teachCourseId);

    AssignHomework getAssignHomework(Student student, Integer teachCourseId, Integer homworkId);

    boolean createHomework(Homework homework);

    Homework getHomework(Integer homeworkId);

    List<Homework> listHomework(Integer teachCourseId);

    boolean changeHomework(Homework homework);

    boolean deleteHomework(Integer homeworkId);

    boolean submitHomework(Student student, Integer teachCourseId, Integer homeworkId);

    boolean changeHomeworkScore(String studentId, Integer teachCourseId);

    boolean changeUsualScore(String studentId, Integer teachCourseId, Integer score);

    boolean changeFinalScore(Integer teachCourseId, Double usualProp);

    List<AssignHomework> listAssignHomework(Integer homeworkId);

    boolean remarkHomework(String studentId, Integer homeworkId, Integer score, String remark);

    Student getStudent(String StudentId);

    Grade getGrade(String StudentId);

    boolean scoreHomework(String studentId, Integer homeworkId, Integer score);

    List<AttendCourse> getStudentFinalScoreBigger(Integer teachCourse, Integer score);

    List<AttendCourse> getStudentFinalScoreSmaller(Integer teachCourseId, Integer score);

    boolean deleteStudent(String studentId, Integer teachCourseId);

}
