package com.nixsolutions.service;

import com.nixsolutions.service.dao.UserDao;
import com.nixsolutions.service.impl.Role;
import com.nixsolutions.service.impl.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @EnableTransactionManagement public class UserService {
    @Autowired private UserDao userDao;

    @Transactional public void create(User user) {
        userDao.create(user);
    }

    @Transactional public void update(User user) {
        userDao.update(user);
    }

    @Transactional public void remove(String login) {
        userDao.remove(userDao.findByLogin(login));
    }

    @Transactional(readOnly = true) public List<User> findAll() {
        return userDao.findAll();
    }

    @Transactional(readOnly = true) public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Transactional(readOnly = true) public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

}
