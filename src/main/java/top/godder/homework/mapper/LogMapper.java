package top.godder.homework.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.godder.homework.utils.BaseMapper;
import top.godder.homework.domain.Log;

import java.util.List;

@Mapper
public interface LogMapper extends BaseMapper<Log> {
    long countLog(@Param("ip") String ip,
                  @Param("operatorId") String operatorId,
                  @Param("type") Integer type);

    List<Log> selectLog(@Param("ip") String ip,
                        @Param("operatorId") String operatorId,
                        @Param("type") Integer type);

    int insertLog(Log log);
}