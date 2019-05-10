package top.godder.homework.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.godder.homework.dao.SensitiveWordDao;
import top.godder.homework.mapper.SensitiveWordsMapper;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/8
 */
@Service
public class SensitiveWordDaoImpl implements SensitiveWordDao {
    @Autowired
    private SensitiveWordsMapper mapper;

    @Override
    public boolean insertSensitiveWord(String word) {
        return mapper.insertWord(word) == 1;
    }

    @Override
    public List<String> getSensitiveWords() {
        return mapper.selectAllWord();
    }
}
