package top.godder.homework.dao;

import top.godder.homework.domain.Teacher;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/8
 */
public interface TeacherDao {
    boolean createTeacher(Teacher teacher);
    Teacher loginTeacher(String id, String password);
    Teacher getTeacher(String id);
    List<Teacher> getAllTeacher();
    boolean alterPassword(Teacher teacher, String newPd);

    boolean alterTeacher(Teacher teacher);
}
