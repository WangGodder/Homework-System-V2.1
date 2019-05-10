package top.godder.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.godder.homework.dao.GradeDao;
import top.godder.homework.dao.NoticeDao;
import top.godder.homework.domain.Grade;
import top.godder.homework.domain.Notice;
import top.godder.homework.service.MainPageService;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/8
 */
@Service
public class MainPageServiceImpl implements MainPageService {
    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private GradeDao gradeDao;

    @Override
    public List<Notice> getNoticeList() {
        return noticeDao.getAllNotice();
    }

    @Override
    public Grade getGrade(Integer gradeId) {
        return gradeDao.getGrade(gradeId);
    }
}
