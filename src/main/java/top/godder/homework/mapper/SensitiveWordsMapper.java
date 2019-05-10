package top.godder.homework.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.godder.homework.utils.BaseMapper;
import top.godder.homework.domain.SensitiveWords;

import java.util.List;

@Mapper
public interface SensitiveWordsMapper extends BaseMapper<SensitiveWords> {
    long countWord();

    int deleteWord(String word);

    List<String> selectAllWord();

    int insertWord(String word);
}