package top.godder.homework.service;

import top.godder.homework.domain.Grade;
import top.godder.homework.domain.Notice;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/8
 */
public interface MainPageService {
    List<Notice> getNoticeList();

    Grade getGrade(Integer gradeId);

}
