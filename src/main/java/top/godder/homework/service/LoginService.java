package top.godder.homework.service;

import top.godder.homework.domain.Administrator;
import top.godder.homework.domain.Student;
import top.godder.homework.domain.Teacher;

/**
 * @author: godder
 * @date: 2019/5/8
 */
public interface LoginService {
    Student loginStudent(String id, String password);

    Teacher loginTeacher(String id, String password);

    Administrator loginAdmin(String id, String password);
}
