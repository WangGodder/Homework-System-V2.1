package top.godder.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.godder.homework.dao.GradeDao;
import top.godder.homework.dao.StudentDao;
import top.godder.homework.domain.Grade;
import top.godder.homework.domain.Student;
import top.godder.homework.service.RegisterService;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/8
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private GradeDao gradeDao;

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Grade> getGradeList() {
        return gradeDao.getAllGrade();
    }

    @Override
    public boolean registerStudent(Student student) {
        return studentDao.createStudent(student);
    }
}
