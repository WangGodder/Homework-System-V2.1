package top.godder.homework.dao;

import org.springframework.stereotype.Repository;
import top.godder.homework.domain.AssignHomework;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/7
 */
public interface AssignHomeworkDao {
    boolean submitHomework(String studentId, Integer teachCourseId, Integer homeworkId);
    boolean scoreHomework(String studentId, Integer homeworkId, Integer score);
    boolean remarkHomework(String studentId, Integer homeworkId, String remark);
    List<AssignHomework> getByStudent(String studentId);
    List<AssignHomework> getByTeachCourse(Integer teachCourseId);
    List<AssignHomework> getByHomework(Integer homeworkId);
    AssignHomework getOne(String studentId, Integer teachCourseId, Integer homeworkId);
    boolean deleteOne(String studentId, Integer teachCourseId, Integer homeworkId);
    boolean deleteByStudent(String studentId);
    boolean deleteByTeachCourse(Integer teachCourseId);
    boolean deleteByHomework(Integer homeworkId);
    boolean deleteStudentInTeachCourse(String studentId, Integer teachCorseId);

}
