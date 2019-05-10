package top.godder.homework.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.godder.homework.dao.CourseDao;
import top.godder.homework.domain.Course;
import top.godder.homework.mapper.CourseMapper;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/7
 */
@Service
public class CourseDaoImpl implements CourseDao {
    @Autowired
    private CourseMapper mapper;


    @Override
    public boolean createCourse(Course course) {
        return mapper.insertCourse(course) == 1;
    }

    @Override
    public Course getCourse(Integer id) {
        List<Course> list = mapper.selectCourse(id, null, null);
        return list.size() == 1? list.get(0) : null;
    }

    @Override
    public List<Course> getAllCourse() {
        return mapper.selectCourse(null, null, null);
    }

    @Override
    public List<Course> searchByName(String name) {
        return mapper.selectCourse(null, name, null);
    }

    @Override
    public List<Course> searchByType(Integer type) {
        return mapper.selectCourse(null, null, type);
    }

    @Override
    public String getName(Integer id) {
        return getCourse(id).getName();
    }

    @Override
    public boolean alterName(Integer id, String newName) {
        return mapper.updateCourse(id, newName, null, null) == 1;
    }

    @Override
    public boolean alterInfo(Integer id, String newInfo) {
        return mapper.updateCourse(id, null, newInfo, null) == 1;
    }

    @Override
    public boolean alertType(Integer id, Integer type) {
        return mapper.updateCourse(id, null, null, type) == 1;
    }

    @Override
    public boolean changeCourse(Course course) {
        return mapper.updateCourse(course.getId(), course.getName(), course.getInfo(), course.getType()) == 1;
    }

    @Override
    public boolean deleteCourse(Integer id) {
        return mapper.deleteCourse(id) == 1;
    }
}
