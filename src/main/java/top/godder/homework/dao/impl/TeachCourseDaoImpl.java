package top.godder.homework.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.godder.homework.dao.TeachCourseDao;
import top.godder.homework.domain.TeachCourse;
import top.godder.homework.mapper.TeachCourseMapper;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author: godder
 * @date: 2019/5/8
 */
@Service
public class TeachCourseDaoImpl implements TeachCourseDao {
    @Autowired
    private TeachCourseMapper mapper;

    @Override
    public boolean createTeachCourse(TeachCourse course) {
        return mapper.insertTeachCourse(course) == 1;
    }

    @Override
    public TeachCourse getTeachCourse(Integer courseId, String teacherId, Integer gradeId) {
        List<TeachCourse> list = mapper.selectTeachCourse(null, courseId, teacherId, gradeId, null);
        return list.size() == 1? list.get(0) : null;
    }

    @Override
    public TeachCourse getTeachCourse(Integer id) {
        List<TeachCourse> list = mapper.selectTeachCourse(id, null, null, null, null);
        return list.size() == 1? list.get(0) : null;
    }

    @Override
    public boolean alterTeachCourse(TeachCourse teachCourse) {
        return mapper.updateTeachCourse(teachCourse) == 1;
    }

    @Override
    public List<TeachCourse> getTeachCourseByCourse(Integer courseId) {
        return mapper.selectTeachCourse(null, courseId, null, null, null);
    }

    @Override
    public List<TeachCourse> getTeachCourseByTeacherId(String teacherId) {
        return mapper.selectTeachCourse(null, null, teacherId, null, null);
    }

    @Override
    public List<TeachCourse> getTeachCourseByGrade(Integer gradeId) {
        return mapper.selectTeachCourse(null, null, null, gradeId, null);
    }

    @Override
    public List<TeachCourse> getTeachCourseByStudent(String studentId) {
        return mapper.selectTeachCourseByStudent(studentId);
    }

    @Override
    public List<TeachCourse> getAllTeachCourse() {
        return mapper.selectTeachCourse(null, null, null, null, null);
    }

    @Override
    public List<TeachCourse> getEnterableTeachCourse(Integer gradeId) {
        List<TeachCourse> list = getTeachCourseByGrade(gradeId);
        list.removeIf(teachCourse -> !teachCourse.getIsPublic());
        return list;
    }

    @Override
    public boolean deleteTeachCourse(Integer id) {
        return mapper.deleteTeachCourse(id, null) == 1;
    }

    @Override
    public boolean deleteCourse(Integer courseId) {
        return mapper.deleteTeachCourse(null, courseId) > 0;
    }
}
