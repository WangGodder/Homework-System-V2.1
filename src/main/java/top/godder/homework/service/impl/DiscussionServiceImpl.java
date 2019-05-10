package top.godder.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.godder.homework.dao.DiscussionDao;
import top.godder.homework.dao.StudentDao;
import top.godder.homework.domain.Discussion;
import top.godder.homework.domain.Student;
import top.godder.homework.service.DiscussionService;

/**
 * @author: godder
 * @date: 2019/5/8
 */
@Service
public class DiscussionServiceImpl implements DiscussionService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private DiscussionDao discussionDao;
    @Override
    public Student getStudent(Discussion discussion) {
        return studentDao.getStudent(discussion.getPublisherid());
    }

    @Override
    public boolean createDiscussion(Discussion discussion) {
        return discussionDao.createDiscussion(discussion);
    }
}
