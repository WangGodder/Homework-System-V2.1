package top.godder.homework.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.godder.homework.utils.BaseMapper;
import top.godder.homework.domain.Message;

import java.util.Date;
import java.util.List;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
    long countMessage(@Param("date") Date date,
                      @Param("teachCourseId") Integer teachCourseId);

    int deleteMessage(Integer id);

    List<Message> selectMessage(@Param("id") Integer id,
                                @Param("date") Date date,
                                @Param("teachCourseId") Integer teachCourseId);

    int updateMessage(Message message);

    int insertMessage(Message message);
}