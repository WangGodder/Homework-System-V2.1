package top.godder.homework.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.godder.homework.utils.BaseMapper;
import top.godder.homework.domain.Administrator;

@Mapper
public interface AdministratorMapper extends BaseMapper<Administrator> {
    long countByType(int type);

    int deleteAdmin(Administrator administrator);

    Administrator selectAdmin(@Param("id") String id, @Param("password") String password);

    int updatePassword(@Param("id") String id, @Param("password") String password, @Param("newPassword") String newPs);

    int insertAdmin(Administrator administrator);
}