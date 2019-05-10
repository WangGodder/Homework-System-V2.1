package top.godder.homework.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.godder.homework.utils.BaseMapper;
import top.godder.homework.domain.AttendCourse;

import java.util.List;

@Mapper
public interface AttendCourseMapper extends BaseMapper<AttendCourse> {
    long countAttendCourse(@Param("studentId") String studentId, @Param("teachCourseId") Integer teachCourseId);

    int deleteAttendCourse(@Param("studentId") String studentId,
                           @Param("teachCourseId") Integer teachCourseId);

    int deleteCourse(Integer courseId);

    List<AttendCourse> selectAttendCourse(@Param("studentId") String studentId,
                                          @Param("teachCourseId") Integer teachCourseId,
                                          @Param("finalScoreUpperLimit") Integer finalScoreUpperLimit,
                                          @Param("finalScoreLowerLimit") Integer finalScoreLowerLimit);

    int insertAttendCourse(@Param("studentId") String studentId, @Param("teachCourseId") Integer teachCourseId);

    int updateAttendCourse(@Param("studentId") String studentId,
                           @Param("teachCourseId") Integer teachCourseId,
                           @Param("usualScore") Integer usualScore,
                           @Param("homeworkScore") Integer homeworkScore,
                           @Param("usualScoreProp") Double usualScoreProp);

    int getHomeworkScore(@Param("studentId") String studentId, @Param("teachCourseId") Integer teachCourseId);
}