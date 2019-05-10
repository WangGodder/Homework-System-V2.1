package top.godder.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.godder.homework.dao.*;
import top.godder.homework.domain.*;
import top.godder.homework.service.AttendCourseService;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/9
 */
@Service
public class AttendCourseServiceImpl implements AttendCourseService {
    @Autowired
    private AssignHomeworkDao assignHomeworkDao;
    @Autowired
    private HomeworkDao homeworkDao;
    @Autowired
    private AttendCourseDao attendCourseDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private GradeDao gradeDao;

    @Override
    public List<AttendCourse> listAttendCourse(Integer teachCourseId) {
        return attendCourseDao.getByTeachCourse(teachCourseId);
    }

    @Override
    public AssignHomework getAssignHomework(Student student, Integer teachCourseId, Integer homworkId) {
        return assignHomeworkDao.getOne(student.getId(), teachCourseId, homworkId);
    }

    @Override
    public boolean createHomework(Homework homework) {
        return homeworkDao.createHomework(homework);
    }

    @Override
    public Homework getHomework(Integer homeworkId) {
        return homeworkDao.getHomework(homeworkId);
    }

    @Override
    public List<Homework> listHomework(Integer teachCourseId) {
        return homeworkDao.getTeachCourseHomework(teachCourseId);
    }

    @Override
    public boolean changeHomework(Homework homework) {
        return homeworkDao.alertHomework(homework);
    }

    @Override
    public boolean deleteHomework(Integer homeworkId) {
        return homeworkDao.deleteHomework(homeworkId);
    }

    @Override
    public boolean submitHomework(Student student, Integer teachCourseId, Integer homeworkId) {
        return assignHomeworkDao.submitHomework(student.getId(), teachCourseId, homeworkId);
    }

    @Override
    public boolean changeHomeworkScore(String studentId, Integer teachCourseId) {
        Integer score = attendCourseDao.getSumHomework(studentId, teachCourseId);
        return attendCourseDao.alterHomeworkScore(studentId, teachCourseId, score);
    }

    @Override
    public boolean changeUsualScore(String studentId, Integer teachCourseId, Integer score) {
        return attendCourseDao.alterUsualScore(studentId, teachCourseId, score);
    }

    @Override
    public boolean changeFinalScore(Integer teachCourseId, Double usualProp) {
        return attendCourseDao.alterFinalScore(teachCourseId, usualProp);
    }

    @Override
    public List<AssignHomework> listAssignHomework(Integer homeworkId) {
        return assignHomeworkDao.getByHomework(homeworkId);
    }

    @Override
    public boolean remarkHomework(String studentId, Integer homeworkId, Integer score, String remark) {
        return assignHomeworkDao.scoreHomework(studentId, homeworkId, score) && assignHomeworkDao.remarkHomework(studentId, homeworkId, remark);
    }

    @Override
    public Student getStudent(String studentId) {
        return studentDao.getStudent(studentId);
    }

    @Override
    public Grade getGrade(String StudentId) {
        return gradeDao.getGrade(studentDao.getStudent(StudentId).getGradeId());
    }

    @Override
    public boolean scoreHomework(String studentId, Integer homeworkId, Integer score) {
        return assignHomeworkDao.scoreHomework(studentId, homeworkId, score);
    }

    @Override
    public List<AttendCourse> getStudentFinalScoreBigger(Integer teachCourse, Integer score) {
        return attendCourseDao.getStudentFinalScoreBigger(teachCourse, score);
    }

    @Override
    public List<AttendCourse> getStudentFinalScoreSmaller(Integer teachCourseId, Integer score) {
        return attendCourseDao.getStudentFinalScoreSmaller(teachCourseId, score);
    }

    @Override
    public boolean deleteStudent(String studentId, Integer teachCourseId) {
        return attendCourseDao.deleteOne(studentId, teachCourseId) && assignHomeworkDao.deleteByStudent(studentId);
    }


}
