package top.godder.homework.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.godder.homework.dao.StudentDao;
import top.godder.homework.domain.Homework;
import top.godder.homework.domain.Student;
import top.godder.homework.mapper.StudentMapper;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/8
 */
@Service
public class StudentDaoImpl implements StudentDao {
    @Autowired
    private StudentMapper mapper;

    @Override
    public boolean createStudent(Student student) {
        return mapper.insertStudent(student) == 1;
    }

    @Override
    public Student loginStudent(String id, String password) {
        return mapper.loginStudent(id, password);
    }

    @Override
    public long getStudentNumByGrade(Integer gradeId) {
        return mapper.countStudent(gradeId);
    }

    @Override
    public Student getStudent(String id) {
        return mapper.getStudent(id);
    }

    @Override
    public List<Student> getStudentByGrade(Integer gradeId) {
        return mapper.selectStudent(null, gradeId);
    }

    @Override
    public boolean alterStudent(Student student) {
        return mapper.updateStudent(student) == 1;
    }

    @Override
    public boolean alterPassword(Student student, String newPd) {
        return mapper.updatePs(student, newPd) == 1;
    }

    @Override
    public boolean deleteStudent(String id) {
        return mapper.deleteStudent(id) == 1;
    }
}
