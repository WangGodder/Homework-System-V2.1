package top.godder.homework.dao;

import top.godder.homework.domain.Course;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/7
 */
public interface CourseDao {
     boolean createCourse(Course course);
     Course getCourse(Integer id);
     List<Course> getAllCourse();
     List<Course> searchByName(String name);
     List<Course> searchByType(Integer type);
     String getName(Integer id);
     boolean alterName(Integer id, String newName);
     boolean alterInfo(Integer id, String newInfo);
     boolean alertType(Integer id, Integer type);

     boolean changeCourse(Course course);
     boolean deleteCourse(Integer id);
}
