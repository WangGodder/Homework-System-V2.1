package top.godder.homework.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.godder.homework.dao.NoticeDao;
import top.godder.homework.domain.Notice;
import top.godder.homework.mapper.NoticeMapper;

import java.util.Date;
import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/8
 */
@Service
public class NoticeDaoImpl implements NoticeDao {
    @Autowired
    private NoticeMapper mapper;

    @Override
    public boolean createNotice(Notice notice) {
        return mapper.insertNotice(notice) == 1;
    }

    @Override
    public Notice getNotice(Integer id) {
        List<Notice> list = mapper.selectNotice(id, null);
        return list.size() == 1? list.get(0) : null;
    }

    @Override
    public List<Notice> getAllNotice() {
        return mapper.selectNotice(null, null);
    }

    @Override
    public boolean alterName(Integer id, String newName) {
        Notice notice = new Notice();
        notice.setId(id);
        notice.setName(newName);
        return mapper.updateNotice(notice) == 1;
    }

    @Override
    public boolean alterInfo(Integer id, String newInfo) {
        Notice notice = new Notice();
        notice.setId(id);
        notice.setInfo(newInfo);
        return mapper.updateNotice(notice) == 1;
    }

    @Override
    public boolean deleteNotice(Integer id) {
        return mapper.deleteNotice(id) == 1;
    }

    @Override
    public String getInfo(Integer id) {
        return getNotice(id).getInfo();
    }

    @Override
    public List<Notice> getNotices(Date date) {
        return mapper.selectNotice(null, date);
    }
}
