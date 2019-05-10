package top.godder.homework.dao;

import java.util.List;
import top.godder.homework.domain.AttendCourse;

/**
 * @author Godder
 */
public interface AttendCourseDao{
     boolean attendCourse(String studentId, Integer teachCourseId);
     boolean alterUsualScore(String studentId, Integer teachCourseId, Integer score);
     boolean alterHomeworkScore(String studentId, Integer teachCourseId, Integer score);
     boolean alterFinalScore(Integer teachCourseId, Double usualProp);
     List<AttendCourse> getByStudent(String studentId);
     List<AttendCourse> getByTeachCourse(Integer teachCourseId);
     List<AttendCourse> getAll();
     List<AttendCourse> getStudentFinalScoreBigger(Integer teachCourseId, Integer score);
     List<AttendCourse> getStudentFinalScoreSmaller(Integer teachCourseId, Integer score);
     boolean isAttend(String studentId, Integer teachCourseId);

     Integer getSumHomework(String studentId, Integer teachCourseId);
     Integer scoreHomeworkStudent(String studentId, Integer teachCourseId);
     Integer getFinalScore(Integer teachCourseId, String studentId);
     boolean deleteOne(String studentId, Integer teachCourseId);
     boolean deleteByStudent(String studentId);
     boolean deleteByTeachCourse(Integer teachCourseId);
     boolean deleteCourse(Integer courseId);
}
