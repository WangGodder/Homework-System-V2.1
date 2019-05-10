package top.godder.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.godder.homework.dao.SensitiveWordDao;
import top.godder.homework.service.SensitiveWordService;
import top.godder.homework.utils.SensitiveWordFilter;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/9
 */
@Service
public class SensitiveWordServiceImpl implements SensitiveWordService {
    @Autowired
    private SensitiveWordDao sensitiveWordDao;

    @Override
    public boolean containSensitiveWord(String message) {
        List<String> words = sensitiveWordDao.getSensitiveWords();
        SensitiveWordFilter filter = new SensitiveWordFilter(words);
        return filter.containSensitiveWord(message);
    }

    @Override
    public boolean addSensitiveWord(String word) {
        return sensitiveWordDao.insertSensitiveWord(word);
    }

    @Override
    public List<String> listSensitiveWords() {
        return sensitiveWordDao.getSensitiveWords();
    }
}
