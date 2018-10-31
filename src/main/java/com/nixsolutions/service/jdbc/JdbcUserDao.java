package com.nixsolutions.service.jdbc;

import com.nixsolutions.service.dao.UserDao;
import com.nixsolutions.service.impl.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao extends AbstractJdbcDao implements UserDao {
    private final String SQL_INSERT_QUERY =
            "INSERT INTO User(login, password, email, firstname, lastname,"
                    + " date, role_Id) VALUES (?,?,?,?,?,?,?)";
    private final String SQL_UPDATE_QUERY =
            "UPDATE User SET login=?, password=?, email=?, firstname=?,"
                    + " lastname=?, date=?, role_Id=? WHERE login=?";
    private final String SQL_DELETE_QUERY = "DELETE FROM User WHERE user_id=?";
    private final String SQL_SELECT_ALL_QUERY = "SELECT * FROM User left join Role on user.role_id = Role.role_id";
    private final String SQL_SELECT_BY_LOGIN_QUERY = "SELECT * FROM User WHERE login=?";
    private final String SQL_SELECT_BY_EMAIL_QUERY = "SELECT * FROM User WHERE email=?";
    private final String SQL_SELECT_BY_ID_QUERY = "SELECT * FROM User WHERE user_id=?";

    public JdbcUserDao() {
    }

    @Override public void create(User user) {
        Connection connection = null;
        try {
            connection = createConnection();
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT_QUERY);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setDate(6, user.getBirthday());
            statement.setLong(7, user.getRole_id());
            //statement.setLong(8,statement.getGeneratedKeys().getLong(1));

            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException(e1.getSQLState(), e);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override public void update(User user) {
        Connection connection = null;
        try {
            connection = createConnection();
            PreparedStatement statement = connection
                    .prepareStatement(SQL_UPDATE_QUERY);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setDate(6, user.getBirthday());
            statement.setLong(7, user.getRole_id());
            statement.setString(8, user.getLogin());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException(e1.getSQLState(), e);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override public void remove(User user) {
        Connection connection = null;
        try {
            connection = createConnection();
            PreparedStatement statement = connection
                    .prepareStatement(SQL_DELETE_QUERY);
            statement.setLong(1, user.getId());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException(e1.getSQLState(), e);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        try {
            connection = createConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_QUERY);
            User user;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setBirthday(resultSet.getDate("date"));
                user.setRole_id(resultSet.getLong("role_Id"));
                user.setRolename(resultSet.getString("rolename"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return users;
    }

    @Override public User findByLogin(String login) {
        return findBySomething(login, SQL_SELECT_BY_LOGIN_QUERY);
    }

    @Override public User findByEmail(String email) {
        return findBySomething(email, SQL_SELECT_BY_EMAIL_QUERY);
    }

    public User findById(Long id) {
        return findBySomething(id, SQL_SELECT_BY_ID_QUERY);
    }

    private User findBySomething(Object parameter, String query) {
        Connection connection = null;
        try {
            connection = createConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, parameter);
            ResultSet resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setBirthday(resultSet.getDate("date"));
                user.setRole_id(resultSet.getLong("role_Id"));
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
