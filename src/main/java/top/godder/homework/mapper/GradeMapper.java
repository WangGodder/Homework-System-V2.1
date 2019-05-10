package top.godder.homework.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.godder.homework.utils.BaseMapper;
import top.godder.homework.domain.Grade;

import java.util.List;

@Mapper
public interface GradeMapper extends BaseMapper<Grade> {
    long countGrade(Integer id);

    int deleteGrade(Integer id);

    List<Grade> selectGrade(@Param("id") Integer id);

    int updateGrade(@Param("id") Integer id, @Param("name") String newName, @Param("info") String newInfo);

    int insertGrade(Grade grade);
}