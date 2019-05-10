package top.godder.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.godder.homework.dao.AdministratorDao;
import top.godder.homework.dao.StudentDao;
import top.godder.homework.dao.TeacherDao;
import top.godder.homework.domain.Administrator;
import top.godder.homework.domain.Student;
import top.godder.homework.domain.Teacher;
import top.godder.homework.service.LoginService;

/**
 * @author: godder
 * @date: 2019/5/8
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private AdministratorDao administratorDao;

    @Override
    public Student loginStudent(String id, String password) {
        return studentDao.loginStudent(id, password);
    }

    @Override
    public Teacher loginTeacher(String id, String password) {
        return teacherDao.loginTeacher(id, password);
    }

    @Override
    public Administrator loginAdmin(String id, String password) {
        return administratorDao.getAdministrator(id, password);
    }
}
