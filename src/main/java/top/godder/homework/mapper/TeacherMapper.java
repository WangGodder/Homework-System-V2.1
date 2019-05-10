package top.godder.homework.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.godder.homework.utils.BaseMapper;
import top.godder.homework.domain.Teacher;

import java.util.List;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
    long countTeacher();

    int deleteTeacher(String id);

    List<Teacher> selectTeacher(@Param("id") String id);

    Teacher loginTeacher(@Param("id") String id, @Param("password") String password);

    int updateTeacher(Teacher teacher);

    int updatePassword(@Param("teacher") Teacher teacher, @Param("newPassword") String newPassword);

    int insertTeacher(Teacher teacher);
}