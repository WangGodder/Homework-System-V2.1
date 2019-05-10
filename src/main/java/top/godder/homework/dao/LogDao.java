package top.godder.homework.dao;

import top.godder.homework.domain.Log;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/7
 */
public interface LogDao {
     boolean log(Log log);
     List<Log> getLogs();
}
