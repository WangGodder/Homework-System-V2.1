package top.godder.homework.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.godder.homework.dao.AdministratorDao;
import top.godder.homework.domain.Administrator;
import top.godder.homework.mapper.AdministratorMapper;

/**
 * @author: godder
 * @date: 2019/5/7
 */
@Service
public class AdministratorDaoImpl implements AdministratorDao {
    @Autowired
    private AdministratorMapper mapper;

    @Override
    public boolean createAdministrator(Administrator administrator) {
        return mapper.insertAdmin(administrator) == 1;
    }

    @Override
    public Administrator getAdministrator(String id, String password) {
        return mapper.selectAdmin(id, password);
    }

    @Override
    public boolean updatePassword(Administrator administrator, String newPassword) {
        return mapper.updatePassword(administrator.getId(), administrator.getPassword(), newPassword) == 1;
    }

    @Override
    public boolean deleteAdministrator(Administrator administrator) {
        return mapper.deleteAdmin(administrator) == 1;
    }

    @Override
    public Long countAdministratorByType(Integer type) {
        return mapper.countByType(type);
    }
}
