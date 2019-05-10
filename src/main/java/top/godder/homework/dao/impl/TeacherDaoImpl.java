package top.godder.homework.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.godder.homework.dao.TeacherDao;
import top.godder.homework.domain.Teacher;
import top.godder.homework.mapper.TeacherMapper;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/8
 */
@Service
public class TeacherDaoImpl implements TeacherDao {
    @Autowired
    private TeacherMapper mapper;

    @Override
    public boolean createTeacher(Teacher teacher) {
        return mapper.insertTeacher(teacher) == 1;
    }

    @Override
    public Teacher loginTeacher(String id, String password) {
        return mapper.loginTeacher(id, password);
    }

    @Override
    public Teacher getTeacher(String id) {
        List<Teacher> list = mapper.selectTeacher(id);
        return list.size() == 1? list.get(0) : null;
    }

    @Override
    public List<Teacher> getAllTeacher() {
        return mapper.selectTeacher(null);
    }

    @Override
    public boolean alterPassword(Teacher teacher, String newPd) {
        return mapper.updatePassword(teacher, newPd) == 1;
    }

    @Override
    public boolean alterTeacher(Teacher teacher) {
        return mapper.updateTeacher(teacher) == 1;
    }
}
