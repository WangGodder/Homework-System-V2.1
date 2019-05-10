package top.godder.homework.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.godder.homework.dao.GradeDao;
import top.godder.homework.domain.Grade;
import top.godder.homework.mapper.GradeMapper;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/7
 */
@Service
public class GradeDaoImpl implements GradeDao {
    @Autowired
    private GradeMapper mapper;

    @Override
    public boolean createGrade(Grade grade) {
        return mapper.insertGrade(grade) == 1;
    }

    @Override
    public Grade getGrade(Integer id) {
        List<Grade> list = mapper.selectGrade(id);
        return list.size() == 1? list.get(0) : null;
    }

    @Override
    public List<Grade> getAllGrade() {
        return mapper.selectGrade(null);
    }

    @Override
    public String getName(Integer id) {
        return getGrade(id).getName();
    }

    @Override
    public boolean alterName(Integer id, String newName) {
        return mapper.updateGrade(id, newName, null) == 1;
    }

    @Override
    public boolean alterInfo(Integer id, String newInfo) {
        return mapper.updateGrade(id, null, newInfo) == 1;
    }

    @Override
    public boolean deleteGrade(Integer id) {
        return mapper.deleteGrade(id) == 1;
    }
}
