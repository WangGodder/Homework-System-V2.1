package top.godder.homework.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.godder.homework.utils.BaseMapper;
import top.godder.homework.domain.AssignHomework;

import java.util.List;

@Mapper
public interface AssignHomeworkMapper extends BaseMapper<AssignHomework> {
    long countAssignHomework(@Param("studentId") String studentId,
                            @Param("teachCourseId") Integer teachCourseId,
                            @Param("homeworkId") Integer homeworkId);

    int deleteAssignHomework(@Param("studentId") String studentId,
                            @Param("teachCourseId") Integer teachCourseId,
                            @Param("homeworkId") Integer homeworkId);

    List<AssignHomework> selectAssignHomework(@Param("studentId") String studentId,
                                              @Param("teachCourseId") Integer teachCourseId,
                                              @Param("homeworkId") Integer homeworkId);

    int replaceAssignHomework(@Param("studentId") String studentId,
                              @Param("teachCourseId") Integer teachCourseId,
                              @Param("homeworkId") Integer homeworkId);

    int updateAssignHomework(@Param("studentId") String studentId,
                             @Param("teachCourseId") Integer teachCourseId,
                             @Param("homeworkId") Integer homeworkId,
                             @Param("score") Integer score,
                             @Param("remark") String remark);
}