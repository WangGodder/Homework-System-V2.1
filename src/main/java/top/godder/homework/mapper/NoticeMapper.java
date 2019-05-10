package top.godder.homework.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.godder.homework.utils.BaseMapper;
import top.godder.homework.domain.Notice;

import java.util.Date;
import java.util.List;

@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
    long countNotice();

    int deleteNotice(Integer id);

    List<Notice> selectNotice(@Param("id") Integer id, @Param("date") Date date);

    int updateNotice(Notice notice);

    int insertNotice(Notice notice);
}