package com.nixsolutions.service.hibernate;

import com.nixsolutions.service.dao.RoleDao;
import com.nixsolutions.service.hibernate.HibernateDao;
import com.nixsolutions.service.impl.Role;
import com.nixsolutions.service.impl.User;

import java.util.List;
import java.util.Objects;

public class HibernateRoleDao implements RoleDao {

    private HibernateDao hibernateDao = new HibernateDao();

    @Override
    public void create(Role role) {
        emptyFieldsChecker(role);
        Role roleChecker = findByName(role.getName());
        if (roleChecker != null) {
            throw new IllegalArgumentException("Role with rolename " + role.getName()
                    + " already exists in DB");
        }
        try {
            hibernateDao.createObject(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Role role) {
        emptyFieldsChecker(role);
        if (findByName(role.getName()) != null) {
            throw new RuntimeException(role.toString() + "doesn't exist in DB");
        }
        try {
            hibernateDao.updateObject(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Role role) {
        emptyFieldsChecker(role);
        if (findByName(role.getName()) == null) {
            throw new RuntimeException(role.toString() + "doesn't exist in DB");
        }
        try {
            hibernateDao.removeObject(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Role findByName(String name) {
        Objects.requireNonNull(name);
        String hql = "FROM Role R WHERE R.rolename = :search_factor";
        Role result = null;
        try {
            result = (Role) hibernateDao.findObject(hql, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private void emptyFieldsChecker(Role role) {
        if (role == null || role.getName() == null) {
            throw new IllegalArgumentException("user has empty fields");
        }
    }


    public List<Role> findAll() {
        String hql = "FROM Role";
        try {
            return hibernateDao.findList(hql);
        } catch (Exception e) {
            e.printStackTrace();
        }return null;
    }
}
