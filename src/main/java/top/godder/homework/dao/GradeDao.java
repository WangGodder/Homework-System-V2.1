package top.godder.homework.dao;

import top.godder.homework.domain.Grade;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/7
 */
public interface GradeDao {
     boolean createGrade(Grade grade);
     Grade getGrade(Integer id);
     List<Grade> getAllGrade();
     String getName(Integer id);
     boolean alterName(Integer id, String newName);
     boolean alterInfo(Integer id, String newInfo);
     boolean deleteGrade(Integer id);
}
