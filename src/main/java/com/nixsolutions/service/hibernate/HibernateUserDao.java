package com.nixsolutions.service.hibernate;

import com.nixsolutions.service.UserService;
import com.nixsolutions.service.dao.UserDao;
import com.nixsolutions.service.hibernate.HibernateDao;
import com.nixsolutions.service.impl.Role;
import com.nixsolutions.service.impl.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository @Qualifier("hibernate") public class HibernateUserDao
        extends HibernateDao implements UserDao {

    @Override public void create(User user) {
        emptyFieldsChecker(user);
        User userChecker = findByLogin(user.getLogin());
        if (userChecker != null) {
            throw new IllegalArgumentException(
                    "User with login " + user.getLogin()
                            + " already exists in DB");
        }
        createObject(user);
    }

    @Override public void update(User user) {
        emptyFieldsChecker(user);
        if (findByLogin(user.getLogin()) == null) {
            throw new RuntimeException(user.toString() + "doesn't exist in DB");
        }
        updateObject(user);
    }

    @Override public void remove(User user) {
        System.out.println(user.toString());
        Objects.requireNonNull(user.getLogin());
        if (findByLogin(user.getLogin()) == null) {
            throw new RuntimeException(user.toString() + "doesn't exist in DB");
        }
        removeObject(user);
    }

    @Override public List<User> findAll() {
        String hql = "FROM User";
        return findList(hql);
    }

    @Override public User findByLogin(String login) {
        Objects.requireNonNull(login);
        String hql = "FROM User where login = :search_factor";
        return (User) findObject(hql, login);
    }

    @Override public User findByEmail(String email) {
        Objects.requireNonNull(email);
        String hql = "FROM User U WHERE U.email = :search_factor";
        return (User) findObject(hql, email);
    }

    private void emptyFieldsChecker(User user) {
        if (user == null || user.getLogin() == null
                || user.getPassword() == null || user.getEmail() == null
                || user.getFirstName() == null || user.getLastName() == null
                || user.getBirthday() == null) {
            throw new IllegalArgumentException("user has empty fields");
        }
    }

}

