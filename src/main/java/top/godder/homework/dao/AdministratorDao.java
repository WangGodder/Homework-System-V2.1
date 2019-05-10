package top.godder.homework.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import top.godder.homework.domain.Administrator;
import top.godder.homework.mapper.AdministratorMapper;

/**
 * @author: godder
 * @date: 2019/5/7
 */
public interface AdministratorDao {
    boolean createAdministrator(Administrator administrator);
    Administrator getAdministrator(String id, String password);
    boolean updatePassword(Administrator administrator, String newPassword);
    boolean deleteAdministrator(Administrator administrator);

    Long countAdministratorByType(Integer type);
}
