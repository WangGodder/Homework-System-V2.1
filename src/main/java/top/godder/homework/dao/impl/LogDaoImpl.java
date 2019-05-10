package top.godder.homework.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.godder.homework.dao.LogDao;
import top.godder.homework.domain.Log;
import top.godder.homework.mapper.LogMapper;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/7
 */
@Service
public class LogDaoImpl implements LogDao {
    @Autowired
    private LogMapper mapper;

    @Override
    public boolean log(Log log) {
        return mapper.insertLog(log) == 1;
    }

    @Override
    public List<Log> getLogs() {
        return mapper.selectLog(null, null, null);
    }
}
