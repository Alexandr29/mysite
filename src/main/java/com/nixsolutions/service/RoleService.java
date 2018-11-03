package com.nixsolutions.service;

import com.nixsolutions.service.dao.RoleDao;
import com.nixsolutions.service.impl.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    @Transactional
    public void create(Role role) {
        roleDao.create(role);
    }

    @Transactional
    public void update(Role role) {
        roleDao.update(role);
    }

    @Transactional
    public void remove(Role role) {
        roleDao.remove(role);
    }

    @Transactional(readOnly = true)
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }

}
