package top.godder.homework.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.godder.homework.utils.BaseMapper;
import top.godder.homework.domain.Discussion;

import java.util.List;

/**
 *
 * @author Godder
 */
@Mapper
public interface DiscussionMapper extends BaseMapper<Discussion> {
    long countDiscussion(@Param("teachCourseId") Integer teachCourseId,
                         @Param("publisherId") Integer publisherId);

    int deleteDiscussion(Integer id);

    List<Discussion> selectDiscussion(@Param("id") Integer id,
                                      @Param("teachCourseId") Integer teachCourseId,
                                      @Param("publisherId") Integer publisherId);

    int insertDiscussion(Discussion discussion);

    int updateDiscussion(@Param("id") Integer id, @Param("content") String newContent);
}