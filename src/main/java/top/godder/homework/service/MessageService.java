package top.godder.homework.service;

import top.godder.homework.domain.Message;

/**
 * @author: godder
 * @date: 2019/5/9
 */
public interface MessageService {
    boolean createMessage(Message message);

    boolean changeMessage(Message message);

    boolean deleteMessage(Integer messageId);
}
