package top.godder.homework.dao;

import top.godder.homework.domain.Homework;

import java.util.Date;
import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/7
 */
public interface HomeworkDao {
     boolean createHomework(Homework homework);
     Homework getHomework(Integer id);
     List<Homework> getAllHomework();
     List<Homework> getTeachCourseHomework(Integer teachCourseId);
     boolean alertHomework(Homework homework);
     boolean alterName(Integer id, String newName);
     boolean alterInfo(Integer id, String newInfo);
     boolean alterDeadline(Integer id, Date newDeadline);
     boolean alterProportion(Integer id, Integer proportion);
     boolean deleteHomework(Integer id);
     boolean deleteCourse(Integer courseId);
}
