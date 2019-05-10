package top.godder.homework.dao;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/8
 */
public interface SensitiveWordDao {
    boolean insertSensitiveWord(String word);
    List<String> getSensitiveWords();
}
