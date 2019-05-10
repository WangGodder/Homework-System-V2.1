package top.godder.homework.service;

import top.godder.homework.domain.Discussion;
import top.godder.homework.domain.Student;

/**
 * @author: godder
 * @date: 2019/5/8
 */
public interface DiscussionService {
    Student getStudent(Discussion discussion);

    boolean createDiscussion(Discussion discussion);
}
