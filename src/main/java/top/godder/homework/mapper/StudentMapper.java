package top.godder.homework.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.godder.homework.utils.BaseMapper;
import top.godder.homework.domain.Student;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    long countStudent(@Param("gradeId") Integer gradeId);

    int deleteStudent(String id);

    Student loginStudent(@Param("id") String id, @Param("password") String password);

    Student getStudent(String id);

    List<Student> selectStudent(@Param("id") String id, @Param("gradeId") Integer gradeId);

    int updateStudent(Student student);

    int updatePs(@Param("student") Student student, @Param("newPassword") String newPassword);

    int insertStudent(Student student);
}