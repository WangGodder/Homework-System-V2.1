package top.godder.homework.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.godder.homework.utils.BaseMapper;
import top.godder.homework.domain.Course;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    long countCourse(@Param("name") String name, @Param("type") Integer type);

    int deleteCourse(Integer id);

    List<Course> selectCourse(@Param("id") Integer id, @Param("name") String name, @Param("type") Integer type);

    int insertCourse(Course course);

    int updateCourse(@Param("id") Integer id,
                     @Param("name") String name,
                     @Param("info") String info,
                     @Param("type") Integer type);
}