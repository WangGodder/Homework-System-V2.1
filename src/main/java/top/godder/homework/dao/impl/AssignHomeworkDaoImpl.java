package top.godder.homework.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.godder.homework.dao.AssignHomeworkDao;
import top.godder.homework.domain.AssignHomework;
import top.godder.homework.mapper.AssignHomeworkMapper;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/7
 */
@Service
public class AssignHomeworkDaoImpl implements AssignHomeworkDao {
    @Autowired
    private AssignHomeworkMapper mapper;

    @Override
    public boolean submitHomework(String studentId, Integer teachCourseId, Integer homeworkId) {
        return mapper.replaceAssignHomework(studentId, teachCourseId, homeworkId) == 1;
    }

    @Override
    public boolean scoreHomework(String studentId, Integer homeworkId, Integer score) {
        return mapper.updateAssignHomework(studentId, null, homeworkId, score, null) == 1;
    }

    @Override
    public boolean remarkHomework(String studentId, Integer homeworkId, String remark) {
        return mapper.updateAssignHomework(studentId, null, homeworkId, null, remark) == 1;
    }

    @Override
    public List<AssignHomework> getByStudent(String studentId) {
        return mapper.selectAssignHomework(studentId, null, null);
    }

    @Override
    public List<AssignHomework> getByTeachCourse(Integer teachCourseId) {
        return mapper.selectAssignHomework(null, teachCourseId, null);
    }

    @Override
    public List<AssignHomework> getByHomework(Integer homeworkId) {
        return mapper.selectAssignHomework(null, null, homeworkId);
    }

    @Override
    public AssignHomework getOne(String studentId, Integer teachCourseId, Integer homeworkId) {
        List<AssignHomework> list = mapper.selectAssignHomework(studentId, teachCourseId, homeworkId);
        return list.size() == 1? list.get(0) : null;
    }

    @Override
    public boolean deleteOne(String studentId, Integer teachCourseId, Integer homeworkId) {
        return mapper.deleteAssignHomework(studentId, teachCourseId, homeworkId) == 1;
    }

    @Override
    public boolean deleteByStudent(String studentId) {
        Long count = mapper.countAssignHomework(studentId, null, null);
        return mapper.deleteAssignHomework(studentId, null, null) == count.intValue();
    }

    @Override
    public boolean deleteByTeachCourse(Integer teachCourseId) {
        Long count = mapper.countAssignHomework(null, teachCourseId, null);
        return mapper.deleteAssignHomework(null, teachCourseId, null) == count.intValue();
    }

    @Override
    public boolean deleteByHomework(Integer homeworkId) {
        Long count = mapper.countAssignHomework(null, null, homeworkId);
        return mapper.deleteAssignHomework(null, null, homeworkId) == count.intValue();
    }

    @Override
    public boolean deleteStudentInTeachCourse(String studentId, Integer teachCorseId) {
        Long count = mapper.countAssignHomework(studentId, teachCorseId, null);
        return mapper.deleteAssignHomework(studentId, teachCorseId, null) == count.intValue();
    }


}
