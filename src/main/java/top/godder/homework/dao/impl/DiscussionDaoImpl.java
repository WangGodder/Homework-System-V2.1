package top.godder.homework.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.godder.homework.dao.DiscussionDao;
import top.godder.homework.domain.Discussion;
import top.godder.homework.mapper.DiscussionMapper;

import java.util.List;

import static javax.swing.UIManager.get;

/**
 * @author: godder
 * @date: 2019/5/7
 */
@Service
public class DiscussionDaoImpl implements DiscussionDao {
    @Autowired
    private DiscussionMapper mapper;

    @Override
    public boolean createDiscussion(Discussion discussion) {
        return mapper.insertDiscussion(discussion) == 1;
    }

    @Override
    public Discussion getDiscussion(Integer id) {
        List<Discussion> list = mapper.selectDiscussion(id, null, null);
        return list.size() == 1 ? list.get(0) : null;
    }

    @Override
    public List<Discussion> getAllDiscussion() {
        return mapper.selectDiscussion(null, null, null);
    }

    @Override
    public List<Discussion> getDiscussionByTeachCourse(Integer teachCourseId) {
        return mapper.selectDiscussion(null, teachCourseId, null);
    }

    @Override
    public List<Discussion> getDiscussionByPublisherId(Integer publisherId) {
        return mapper.selectDiscussion(null, null, publisherId);
    }

    @Override
    public List<Discussion> getDiscussionByTeachCoursePublisherId(Integer teachCourseId, Integer publisherId) {
        return mapper.selectDiscussion(null, teachCourseId, publisherId);
    }

    @Override
    public boolean alterContent(Integer id, String newContent) {
        return mapper.updateDiscussion(id, newContent) == 1;
    }

    @Override
    public boolean deleteDiscussion(Integer id) {
        return mapper.deleteDiscussion(id) == 1;
    }
}
