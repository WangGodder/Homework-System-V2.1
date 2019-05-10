package top.godder.homework.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.godder.homework.dao.MessageDao;
import top.godder.homework.domain.Homework;
import top.godder.homework.domain.Message;
import top.godder.homework.mapper.MessageMapper;

import java.util.Date;
import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/8
 */
@Service
public class MessageDaoImpl implements MessageDao {
    @Autowired
    private MessageMapper mapper;

    @Override
    public boolean createMessage(Message message) {
        return mapper.insertMessage(message) == 1;
    }

    @Override
    public Message getMessage(Integer id) {
        List<Message> list = mapper.selectMessage(id, null, null);
        return list.size() == 1? list.get(0) : null;
    }

    @Override
    public List<Message> getALLMessageByDate(Date date) {
        return mapper.selectMessage(null, date, null);
    }

    @Override
    public List<Message> getALLMessageByTeachCourse(Integer teachCourseId) {
        return mapper.selectMessage(null, null, teachCourseId);
    }

    @Override
    public List<Message> getMessageByTeachCourseDate(Integer teachCourseId, Date date) {
        return mapper.selectMessage(null, date, teachCourseId);
    }

    @Override
    public List<Message> getAllMessage() {
        return mapper.selectMessage(null, null, null);
    }

    @Override
    public boolean enditorMessage(Message mesage) {
        return mapper.updateMessage(mesage) == 1;
    }

    @Override
    public boolean alterName(Integer id, String newName) {
        Message message = new Message();
        message.setId(id);
        message.setName(newName);
        return mapper.updateMessage(message) == 1;
    }

    @Override
    public boolean alertInfo(Integer id, String info) {
        Message message = new Message();
        message.setId(id);
        message.setInfo(info);
        return mapper.updateMessage(message) == 1;
    }

    @Override
    public boolean deleteMessage(Integer id) {
        return mapper.deleteMessage(id) == 1;
    }
}
