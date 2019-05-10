package top.godder.homework.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import top.godder.homework.domain.AttendCourse;
import top.godder.homework.mapper.AttendCourseMapper;
import top.godder.homework.dao.AttendCourseDao;

@Service
public class AttendCourseDaoImpl implements AttendCourseDao{
    @Autowired
    private AttendCourseMapper mapper;


    @Override
    public boolean attendCourse(String studentId, Integer teachCourseId) {
        return mapper.insertAttendCourse(studentId, teachCourseId) == 1;
    }

    @Override
    public boolean alterUsualScore(String studentId, Integer teachCourseId, Integer score) {
        return mapper.updateAttendCourse(studentId, teachCourseId, score, null, null) == 1;
    }

    @Override
    public boolean alterHomeworkScore(String studentId, Integer teachCourseId, Integer score) {
        return mapper.updateAttendCourse(studentId, teachCourseId, null, score, null) == 1;
    }

    @Override
    public boolean alterFinalScore(Integer teachCourseId, Double usualProp) {
        Long count = mapper.countAttendCourse(null, teachCourseId);
        return mapper.updateAttendCourse(null, teachCourseId, null, null, usualProp) == count.intValue();
    }

    @Override
    public List<AttendCourse> getByStudent(String studentId) {
        return mapper.selectAttendCourse(studentId, null, null, null);
    }

    @Override
    public List<AttendCourse> getByTeachCourse(Integer teachCourseId) {
        return mapper.selectAttendCourse(null, teachCourseId, null, null);
    }

    @Override
    public List<AttendCourse> getAll() {
        return mapper.selectAttendCourse(null, null, null, null);
    }

    @Override
    public List<AttendCourse> getStudentFinalScoreBigger(Integer teachCourseId, Integer score) {
        return mapper.selectAttendCourse(null, teachCourseId, null, score);
    }

    @Override
    public List<AttendCourse> getStudentFinalScoreSmaller(Integer teachCourseId, Integer score) {
        return mapper.selectAttendCourse(null, teachCourseId, score, null);
    }

    @Override
    public boolean isAttend(String studentId, Integer teachCourseId) {
        return mapper.countAttendCourse(studentId, teachCourseId) == 1;
    }

    @Override
    public Integer getSumHomework(String studentId, Integer teachCourseId) {
        return mapper.getHomeworkScore(studentId, teachCourseId);
    }

    @Override
    public Integer scoreHomeworkStudent(String studentId, Integer teachCourseId) {
        return mapper.selectAttendCourse(studentId, teachCourseId, null, null).get(0).getHomeworkScore();
    }

    @Override
    public Integer getFinalScore(Integer teachCourseId, String studentId) {
        List<AttendCourse> list = mapper.selectAttendCourse(studentId, teachCourseId, null, null);
        return list.size() == 1? list.get(0).getFinalScore() : -1;
    }

    @Override
    public boolean deleteOne(String studentId, Integer teachCourseId) {
        return mapper.deleteAttendCourse(studentId, teachCourseId) == 1;
    }

    @Override
    public boolean deleteByStudent(String studentId) {
        Long count = mapper.countAttendCourse(studentId, null);
        return mapper.deleteAttendCourse(studentId, null) == count.intValue();
    }

    @Override
    public boolean deleteByTeachCourse(Integer teachCourseId) {
        Long count = mapper.countAttendCourse(null, teachCourseId);
        return mapper.deleteAttendCourse(null, teachCourseId) == count.intValue();
    }

    @Override
    public boolean deleteCourse(Integer courseId) {
        return mapper.deleteCourse(courseId) > 0;
    }
}
