package top.godder.homework.service;

import top.godder.homework.domain.Grade;
import top.godder.homework.domain.Student;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/8
 */
public interface RegisterService {
    List<Grade> getGradeList();

    boolean registerStudent(Student student);
}
