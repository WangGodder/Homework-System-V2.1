package top.godder.homework.dao;

import top.godder.homework.domain.Discussion;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/7
 */
public interface DiscussionDao {
     boolean createDiscussion(Discussion discussion);
     Discussion getDiscussion(Integer id);
     List<Discussion> getAllDiscussion();
     List<Discussion> getDiscussionByTeachCourse(Integer teachCourseId);
     List<Discussion> getDiscussionByPublisherId(Integer publisherId);
     List<Discussion> getDiscussionByTeachCoursePublisherId(Integer teachCourseId, Integer publisherId);
     boolean alterContent(Integer id, String newContent);
     boolean deleteDiscussion(Integer id);
}
