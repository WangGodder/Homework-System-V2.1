package top.godder.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.godder.homework.dao.*;
import top.godder.homework.domain.*;
import top.godder.homework.service.TeachCourseService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/8
 */
@Service
public class TeachCourseSerivceImpl implements TeachCourseService {
    @Autowired
    private TeachCourseDao teachCourseDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private AttendCourseDao attendCourseDao;
    @Autowired
    private GradeDao gradeDao;
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private HomeworkDao homeworkDao;
    @Autowired
    private DiscussionDao discussionDao;

    @Override
    public List<TeachCourse> getTeachCourseByStudent(String studentId) {
        return teachCourseDao.getTeachCourseByStudent(studentId);
    }

    @Override
    public List<TeachCourse> getTeachCourseByTeacher(String teacherId) {
        return teachCourseDao.getTeachCourseByTeacherId(teacherId);
    }

    @Override
    public List<TeachCourse> getEnterableTeachCourse(Integer gradeId) {
        return teachCourseDao.getEnterableTeachCourse(gradeId);
    }

    @Override
    public List<Course> getAllCourse() {
        return courseDao.getAllCourse();
    }

    @Override
    public List<Grade> getAllGrade() {
        return gradeDao.getAllGrade();
    }

    @Override
    public String[] getTeachCourseInfo(TeachCourse course, Student student) {
        Course c = this.courseDao.getCourse(course.getCourseId());
        Teacher teacher = this.teacherDao.getTeacher(course.getTeacherId());
        Integer finalScore = this.attendCourseDao.getFinalScore(course.getId(), student.getId());
        String finalScoreStr = finalScore >= 0 ? finalScore.toString() : "not define";
        String[] infos = {c.getName(), c.getId().toString(), course.printSemestor(), teacher.getName(), finalScoreStr, course.getId().toString()};
        return infos;
    }

    @Override
    public boolean attendTeachCourse(Student student, Integer teachCourseId) {
        return attendCourseDao.attendCourse(student.getId(), teachCourseId);
    }

    @Override
    public boolean createTeachCourse(TeachCourse course) {
        return teachCourseDao.createTeachCourse(course);
    }

    @Override
    public List<String[]> enterableCourseInfos(Student student) {
        List<TeachCourse> courses = getEnterableTeachCourse(student.getGradeId());
        List<String[]> infos = new ArrayList<>();
        for (TeachCourse course: courses) {
            if (!attendCourseDao.isAttend(student.getId(), course.getId())) {
                Course c = this.courseDao.getCourse(course.getCourseId());
                Teacher teacher = this.teacherDao.getTeacher(course.getTeacherId());
                Grade grade = this.gradeDao.getGrade(course.getGradeId());
                String[] info = {c.getName(), c.getId().toString(), course.printSemestor(), c.printType(), teacher.getName(), grade.getName(), course.getIsPublic().toString(), course.getId().toString()};
                infos.add(info);
            }
        }
        return infos;
    }

    @Override
    public TeachCourse getTeachCourse(Integer teachCourseId) {
        return teachCourseDao.getTeachCourse(teachCourseId);
    }

    @Override
    public Course getCourse(TeachCourse teachCourse) {
        return courseDao.getCourse(teachCourse.getCourseId());
    }

    @Override
    public Grade getGrade(TeachCourse teachCourse) {
        return gradeDao.getGrade(teachCourse.getGradeId());
    }

    @Override
    public List<Message> getMessageListOfTeachCourse(Integer teachCourseId) {
        return messageDao.getALLMessageByTeachCourse(teachCourseId);
    }

    @Override
    public List<Homework> getHomrworkListOfTeachCourse(Integer teachCourseId) {
        return homeworkDao.getTeachCourseHomework(teachCourseId);
    }

    @Override
    public List<AttendCourse> getAttendCourseOfTeachCourse(Integer teachCourseId) {
        return attendCourseDao.getByTeachCourse(teachCourseId);
    }

    @Override
    public List<Discussion> getDiscussionOfTeachCourse(Integer teachCourseId) {
        return discussionDao.getDiscussionByTeachCourse(teachCourseId);
    }
}
