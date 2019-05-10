package top.godder.homework.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.godder.homework.utils.BaseMapper;
import top.godder.homework.domain.TeachCourse;

import java.util.Date;
import java.util.List;

@Mapper
public interface TeachCourseMapper extends BaseMapper<TeachCourse> {
    long countTeachCourse(@Param("courseId") Integer courseId,
                          @Param("teacherId") String teacherId,
                          @Param("gradeId") Integer gradeId,
                          @Param("semestor") Date semestor,
                          @Param("isPublic") boolean isPublic);

    int deleteTeachCourse(@Param("id") Integer id, @Param("courseId") Integer courseId);

    List<TeachCourse> selectTeachCourse(@Param("id") Integer id,
                                        @Param("courseId") Integer courseId,
                                        @Param("teacherId") String teacherId,
                                        @Param("gradeId") Integer gradeId,
                                        @Param("semestor") Date semestor);

    List<TeachCourse> selectTeachCourseByStudent(String studentId);

    int updateTeachCourse(TeachCourse teachCourse);

    int insertTeachCourse(TeachCourse teachCourse);
}