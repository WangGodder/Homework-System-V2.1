package top.godder.homework.service;

import java.util.List;

/**
 * @author: godder
 * @date: 2019/5/9
 */
public interface SensitiveWordService {
    boolean containSensitiveWord(String message);

    boolean addSensitiveWord(String word);

    List<String> listSensitiveWords();
}
