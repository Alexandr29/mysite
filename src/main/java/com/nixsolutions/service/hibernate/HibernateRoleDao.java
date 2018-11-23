package com.nixsolutions.service.hibernate;

import com.nixsolutions.service.dao.RoleDao;
import com.nixsolutions.service.hibernate.HibernateDao;
import com.nixsolutions.service.impl.Role;
import com.nixsolutions.service.impl.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class HibernateRoleDao extends HibernateDao
        implements RoleDao {

    @Override
    public void create(Role role) {
        Role roleChecker = findByName(role.getName());
        if (roleChecker != null) {
            throw new IllegalArgumentException(
                    "Role with rolename " + role.getName()
                            + " already exists in DB");
        }
        createObject(role);
    }

    @Override
    public void update(Role role) {
        if (findByName(role.getName()) != null) {
            throw new RuntimeException(role.toString() + "doesn't exist in DB");
        }
        updateObject(role);
    }

    @Override
    public void remove(Role role) {
        if (findByName(role.getName()) == null) {
            throw new RuntimeException(role.toString() + "doesn't exist in DB");
        }
        removeObject(role);
    }

    @Override
    public Role findByName(String name) {
        Objects.requireNonNull(name);
        String hql = "FROM Role R WHERE R.rolename = :search_factor";
        Role result = (Role) findObject(hql, name);
        return result;
    }

    @Override
    public List findAll() {
        String hql = "FROM Role";
        return findList(hql);
    }

    @Override
    public Role findById(Long id) {
        String hql = "FROM Role R WHERE R.id = :search_factor";
        Role result = (Role) findORoleById(hql, id);
        return result;
    }
}