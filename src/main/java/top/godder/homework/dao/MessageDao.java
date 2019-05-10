package top.godder.homework.dao;

import top.godder.homework.domain.Message;

import java.util.Date;
import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/8
 */
public interface MessageDao {
    boolean createMessage(Message message);
    Message getMessage(Integer id);
    List<Message> getALLMessageByDate(Date date);
    List<Message> getALLMessageByTeachCourse(Integer teachCourseId);
    List<Message> getMessageByTeachCourseDate(Integer teachCourseId, Date date);
    List<Message> getAllMessage();

    boolean enditorMessage(Message mesage);
    boolean alterName(Integer id, String newName);
    boolean alertInfo(Integer id, String info);
    boolean deleteMessage(Integer id);
}
