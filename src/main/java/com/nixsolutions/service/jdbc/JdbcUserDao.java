package com.nixsolutions.service.jdbc;

import com.nixsolutions.service.dao.UserDao;
import com.nixsolutions.service.impl.User;
import com.nixsolutions.service.jdbc.AbstractJdbcDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao extends AbstractJdbcDao implements UserDao {

    private List<User> users = new ArrayList<>();

    @Override public void create(User user) {

        if (user == null) {
            throw new NullPointerException("User is null");
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String login = user.getLogin();
        String password = user.getPassword();
        String email = user.getEmail();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        Date date = user.getBirthday();
        Long role_id = user.getRole_id();

        String insertTableSQL = "INSERT INTO USER"
                + "(LOGIN, PASSWORD, EMAIL, FIRSTNAME, LASTNAME, DATE, ROLE_ID) VALUES"
                + "(?,?,?,?,?,?,?)";
        try {
            connection = createConnection();
            preparedStatement = connection.prepareStatement(insertTableSQL,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, firstName);
            preparedStatement.setString(5, lastName);
            preparedStatement.setDate(6, date);
            preparedStatement.setLong(7, role_id);
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override public void update(User user) {
        try {
            createConnection().createStatement().executeUpdate("UPDATE USER"
                    + " SET LOGIN = "+"'"+user.getLogin()+"'"
                    + ", PASSWORD = " + "'"+user.getPassword()+"'"
                    + ", EMAIL = " + "'"+user.getEmail()+"'"
                    + ", FIRSTNAME = " + "'"+user.getFirstName()+"'"
                    + ", LASTNAME = " + "'"+user.getLastName()+"'"
                    + ", DATE = " + "'"+user.getBirthday()+"'"
                    + ", ROLE_ID = " + "'"+user.getRole_id()+"'"
                    + " WHERE USER_ID = " + "'"+user.getId()+"'" + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override public void remove(User user) {
        try {
            createConnection().createStatement().execute("DELETE FROM USER WHERE USER_ID = "+"'"+user.getId()+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override public User findByLogin(String login) {
        User user = null;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = createConnection();
            statement = connection.createStatement();
            String s = "Select * From USER where login = " + "'" + login + "'";
            ResultSet rst;
            rst = statement.executeQuery(s);
            while (rst.next()) {
                user = new User(rst.getLong("USER_ID"), rst.getString("LOGIN"),
                        rst.getString("PASSWORD"), rst.getString("EMAIL"),
                        rst.getString("FIRSTNAME"), rst.getString("LASTNAME"),
                        rst.getDate("DATE"), rst.getLong("ROLE_ID"));
                //System.out.println(user);
            }
        } catch (Exception e) {
            System.out.println("exeption " + e.getCause());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    @Override public User findByEmail(String email) {
        User user = null;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = createConnection();
            statement = connection.createStatement();
            String s = "Select * From USER where EMAIL = " + "'" + email + "'";
            ResultSet rst;
            rst = statement.executeQuery(s);
            while (rst.next()) {
                user = new User(rst.getLong("USER_ID"), rst.getString("LOGIN"),
                        rst.getString("PASSWORD"), rst.getString("EMAIL"),
                        rst.getString("FIRSTNAME"), rst.getString("LASTNAME"),
                        rst.getDate("DATE"), rst.getLong("ROLE_ID"));
                //System.out.println(user);
            }
        } catch (Exception e) {
            System.out.println("exeption " + e.getCause());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    @Override public List<User> findAll() {
        users.clear();
        User user;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = createConnection();
            statement = connection.createStatement();
            String s = "Select * From USER";
            ResultSet rst;
            rst = statement.executeQuery(s);
            while (rst.next()) {
                user = new User(rst.getLong("USER_ID"), rst.getString("LOGIN"),
                        rst.getString("PASSWORD"), rst.getString("EMAIL"),
                        rst.getString("FIRSTNAME"), rst.getString("LASTNAME"),
                        rst.getDate("DATE"), rst.getLong("ROLE_ID"));
                users.add(user);
            }
            //System.out.println(users.toString());
        } catch (Exception e) {
            System.out.println("exeption " + e.getCause());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return users;
    }

    private User findById(Long id) {
        User user = null;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = createConnection();
            statement = connection.createStatement();
            String s = "Select * From USER where USER_ID = " + "'" + id + "'";
            ResultSet rst;
            rst = statement.executeQuery(s);
            while (rst.next()) {
                user = new User(rst.getLong("USER_ID"), rst.getString("LOGIN"),
                        rst.getString("PASSWORD"), rst.getString("EMAIL"),
                        rst.getString("FIRSTNAME"), rst.getString("LASTNAME"),
                        rst.getDate("DATE"), rst.getLong("ROLE_ID"));
                //System.out.println(user);
            }
        } catch (Exception e) {
            System.out.println("exeption " + e.getCause());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }
}
