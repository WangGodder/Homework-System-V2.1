package top.godder.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.godder.homework.dao.MessageDao;
import top.godder.homework.domain.Message;
import top.godder.homework.service.MessageService;

/**
 * @author: godder
 * @date: 2019/5/9
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    @Override
    public boolean createMessage(Message message) {
        return messageDao.createMessage(message);
    }

    @Override
    public boolean changeMessage(Message message) {
        return messageDao.enditorMessage(message);
    }

    @Override
    public boolean deleteMessage(Integer messageId) {
        return messageDao.deleteMessage(messageId);
    }
}
