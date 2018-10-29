package com.nixsolutions.service.hibernate;

import com.nixsolutions.service.dao.RoleDao;
import com.nixsolutions.service.hibernate.HibernateDao;
import com.nixsolutions.service.impl.Role;

import java.util.Objects;

public class HibernateRoleDao implements RoleDao {

    private HibernateDao hibernateDao = new HibernateDao();

    @Override
    public void create(Role role) {
        emptyFieldsChecker(role);
        Role roleChecker = findByName(role.getName());
        if (roleChecker != null) {
            throw new IllegalArgumentException("Role with name " + role.getName()
                    + " already exists in DB");
        }
        hibernateDao.createObject(role);
    }

    @Override
    public void update(Role role) {
        emptyFieldsChecker(role);
        if (findByName(role.getName()) != null) {
            throw new RuntimeException(role.toString() + "doesn't exist in DB");
        }
        hibernateDao.updateObject(role);
    }

    @Override
    public void remove(Role role) {
        emptyFieldsChecker(role);
        if (findByName(role.getName()) == null) {
            throw new RuntimeException(role.toString() + "doesn't exist in DB");
        }
        hibernateDao.removeObject(role);
    }

    @Override
    public Role findByName(String name) {
        Objects.requireNonNull(name);
        String hql = "FROM Role R WHERE R.name = :search_factor";
        Role result = (Role) hibernateDao.findObject(hql, name);
        return result;
    }

    private void emptyFieldsChecker(Role role) {
        if (role == null || role.getName() == null) {
            throw new IllegalArgumentException("user has empty fields");
        }
    }
}
