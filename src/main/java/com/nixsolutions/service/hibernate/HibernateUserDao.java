package com.nixsolutions.service.hibernate;

import com.nixsolutions.service.dao.UserDao;
import com.nixsolutions.service.impl.User;

import java.util.List;
import java.util.Objects;

public class HibernateUserDao implements UserDao {

    private HibernateDao hibernateDao = new HibernateDao();

    @Override
    public void create(User user) {
        emptyFieldsChecker(user);
        User userChecker = findByLogin(user.getLogin());
        if (userChecker != null) {
            throw new IllegalArgumentException("User with login " + user.getLogin()
                    + " already exists in DB");
        }
        hibernateDao.createObject(user);
    }

    @Override
    public void update(User user) {
        emptyFieldsChecker(user);
        if (findByLogin(user.getLogin()) == null) {
            throw new RuntimeException(user.toString() + "doesn't exist in DB");
        }
        hibernateDao.updateObject(user);
    }

    @Override
    public void remove(User user) {
        emptyFieldsChecker(user);
        Objects.requireNonNull(user.getId());
        if (findByLogin(user.getLogin()) == null) {
            throw new RuntimeException(user.toString() + "doesn't exist in DB");
        }
        hibernateDao.removeObject(user);
    }

    @Override
    public List<User> findAll() {
        String hql = "FROM User";
        return hibernateDao.findList(hql);
    }

    @Override
    public User findByLogin(String login) {
        Objects.requireNonNull(login);
        String hql = "FROM User U WHERE U.login = :search_factor";
        return (User) hibernateDao.findObject(hql, login);
    }

    @Override
    public User findByEmail(String email) {
        Objects.requireNonNull(email);
        String hql = "FROM User U WHERE U.email = :search_factor";
        return (User) hibernateDao.findObject(hql, email);
    }

    private void emptyFieldsChecker(User user) {
        if (user == null || user.getLogin() == null || user.getPassword() == null
                || user.getEmail() == null || user.getFirstName() == null
                || user.getLastName() == null || user.getBirthday() == null
                || user.getRole_id() == null) {
            throw new IllegalArgumentException("user has empty fields");
        }
    }
}
