package top.godder.homework.dao;

import top.godder.homework.domain.Notice;

import java.util.Date;
import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/8
 */
public interface NoticeDao {
    boolean createNotice(Notice notice);
    Notice getNotice(Integer id);
    List<Notice> getAllNotice();
    boolean alterName(Integer id, String newName);
    boolean alterInfo(Integer id, String newInfo);
    boolean deleteNotice(Integer id);
    String getInfo(Integer id);
    List<Notice> getNotices(Date date);
}
