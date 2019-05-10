package top.godder.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.godder.homework.dao.LogDao;
import top.godder.homework.domain.Log;
import top.godder.homework.service.LogService;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/8
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;
    @Override
    public void log(Log log) {
        logDao.log(log);
    }

    @Override
    public List<Log> listAllLog() {
        return logDao.getLogs();
    }
}
