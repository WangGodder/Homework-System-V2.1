package top.godder.homework.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.godder.homework.utils.BaseMapper;
import top.godder.homework.domain.Homework;

import java.util.List;

/**
 * @author Godder
 */
@Mapper
public interface HomeworkMapper extends BaseMapper<Homework> {
    long countHomework(@Param("id") Integer id,
                       @Param("teachCourseId") Integer teachCourseId);

    int deleteHomework(Integer id);

    int deleteCourse(Integer courseId);

    List<Homework> selectHomework(@Param("id") Integer id,
                                  @Param("teachCourseId") Integer teachCourseId);

    int updateHomework(Homework homework);

    int insertHomework(Homework homework);
}