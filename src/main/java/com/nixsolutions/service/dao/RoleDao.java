package com.nixsolutions.service.dao;

import com.nixsolutions.service.impl.Role;

import java.util.List;

public interface RoleDao {
    void create(Role role);

    void update(Role role);

    void remove(Role role);

    Role findByName(String name);

    List findAll();

    Role findById(Long id);
}
