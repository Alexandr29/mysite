package com.nixsolutions.service;

import com.nixsolutions.service.hibernate.HibernateUtil;
import com.nixsolutions.service.impl.Role;
import com.nixsolutions.service.impl.User;
import com.nixsolutions.service.jdbc.AbstractJdbcDao;
import com.nixsolutions.service.jdbc.JdbcRoleDao;
import com.nixsolutions.service.jdbc.JdbcUserDao;
import org.hibernate.SessionFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

//    public static void main(String[] args) {
//        java.sql.Date sqldate = null;
//        String sdate = "17.10.2018";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
//        try {
//            Date date = simpleDateFormat.parse(sdate);
//            sqldate = new java.sql.Date(date.getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Role role1 = new Role("admin");
//        Role role2 = new Role("user");
//
//        User user1 = new User("alex1", "1234", "alexru", "Alex", "Last",
//                sqldate, 1l);
//        User user2 = new User("tolik", "1234", "toliku", "Tolik", "Last",
//                sqldate, 1l);
//        AbstractJdbcDao abstractJdbcDao = new AbstractJdbcDao();
//
//        try{
//            AbstractJdbcDao.createConnection();
//            abstractJdbcDao.createTables();
//            JdbcUserDao jdbcUserDao = new JdbcUserDao();
//            JdbcRoleDao jdbcRoleDao = new JdbcRoleDao();
//
//            jdbcRoleDao.create(role1);
//            jdbcRoleDao.create(role2);
//
//            role1.setName("god");
//            jdbcRoleDao.update(role1);
//
//            jdbcUserDao.create(user1);
//            jdbcUserDao.create(user2);
//
//            System.out.println(jdbcUserDao.findAll());
//            System.out.println(jdbcUserDao.findByLogin("alex1"));
//            System.out.println(jdbcUserDao.findByEmail("toliku"));
//
//            user1.setPassword("4321");
//            user1.setLogin("alexupd");
//            System.out.println(jdbcRoleDao.findByName("god"));
//            jdbcUserDao.update(user1);
//            System.out.println(jdbcUserDao.findAll());
//
//        } catch (Exception e) {
//            e.getCause();
//        }
//    }
}
