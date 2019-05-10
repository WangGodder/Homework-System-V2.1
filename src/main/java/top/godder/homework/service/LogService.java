package top.godder.homework.service;

import top.godder.homework.domain.Log;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/8
 */
public interface LogService {
    void log(Log log);

    List<Log> listAllLog();
}
