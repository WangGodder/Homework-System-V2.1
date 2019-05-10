package top.godder.homework.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.godder.homework.dao.HomeworkDao;
import top.godder.homework.domain.Homework;
import top.godder.homework.mapper.HomeworkMapper;

import java.util.Date;
import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/7
 */
@Service
public class HomeworkDaoImpl implements HomeworkDao {
    @Autowired
    private HomeworkMapper mapper;

    @Override
    public boolean createHomework(Homework homework) {
        return mapper.insertHomework(homework) == 1;
    }

    @Override
    public Homework getHomework(Integer id) {
        List<Homework> list = mapper.selectHomework(id, null);
        return list.size() == 1? list.get(0) : null;
    }

    @Override
    public List<Homework> getAllHomework() {
        return mapper.selectHomework(null, null);
    }

    @Override
    public List<Homework> getTeachCourseHomework(Integer teachCourseId) {
        return mapper.selectHomework(null, teachCourseId);
    }

    @Override
    public boolean alertHomework(Homework homework) {
        return mapper.updateHomework(homework) == 1;
    }

    @Override
    public boolean alterName(Integer id, String newName) {
        Homework homework = new Homework();
        homework.setId(id);
        homework.setName(newName);
        return mapper.updateHomework(homework) == 1;
    }

    @Override
    public boolean alterInfo(Integer id, String newInfo) {
        Homework homework = new Homework();
        homework.setId(id);
        homework.setInfo(newInfo);
        return mapper.updateHomework(homework) == 1;
    }

    @Override
    public boolean alterDeadline(Integer id, Date newDeadline) {
        Homework homework = new Homework();
        homework.setId(id);
        homework.setDeadline(newDeadline);
        return mapper.updateHomework(homework) == 1;
    }

    @Override
    public boolean alterProportion(Integer id, Integer proportion) {
        Homework homework = new Homework();
        homework.setId(id);
        homework.setProportion(proportion);
        return mapper.updateHomework(homework) == 1;
    }

    @Override
    public boolean deleteHomework(Integer id) {
        return mapper.deleteHomework(id) == 1;
    }

    @Override
    public boolean deleteCourse(Integer courseId) {
        return mapper.deleteCourse(courseId) > 0;
    }
}
