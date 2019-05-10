package top.godder.homework.dao;

import top.godder.homework.domain.Student;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/8
 */
public interface StudentDao {
    boolean createStudent(Student student);
    Student loginStudent(String id, String password);
    long getStudentNumByGrade(Integer gradeId);
    Student getStudent(String id);
    List<Student> getStudentByGrade(Integer gradeId);
    boolean alterStudent(Student student);
    boolean alterPassword(Student student, String newPd);
    boolean deleteStudent(String id);
}
