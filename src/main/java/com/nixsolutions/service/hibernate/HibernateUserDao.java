package com.nixsolutions.service.hibernate;

import com.nixsolutions.service.dao.UserDao;
import com.nixsolutions.service.impl.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateUserDao extends HibernateDao implements UserDao {

    @Override
    public User findByLogin(String login) {
        String hql = "FROM User where login = :search_factor";
        return (User) findObject(hql, login);
    }

    @Override
    public User findById(Long id) {
        String hql = "FROM User where id = :search_factor";
        return (User) findORoleById(hql, id);
    }

    @Override
    public List<User> findAll() {
        String hql = "FROM User";
        return findList(hql);
    }

    @Override
    public void create(User user) {
        User existUser = findByLogin(user.getLogin());
        if (existUser != null) {
            throw new IllegalArgumentException(
                    "Login " + user.getLogin() + " already exists");
        }
        createObject(user);
    }

    @Override
    public void update(User user) {
        if (findByLogin(user.getLogin()) == null) {
            throw new RuntimeException(user.getLogin() + "doesn't exist");
        }
        updateObject(user);
    }

    @Override
    public void remove(User user) {
        if (findByLogin(user.getLogin()) == null) {
            throw new RuntimeException(user.getLogin() + "doesn't exist");
        }
        removeObject(user);
    }

}

