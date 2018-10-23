package com.nixsolutions.service.jdbc;

import com.nixsolutions.service.dao.RoleDao;
import com.nixsolutions.service.impl.Role;

import java.sql.*;

public class JdbcRoleDao extends AbstractJdbcDao implements RoleDao {

    @Override public void create(Role role) {
        if (role == null) {
            throw new NullPointerException("Role is null");
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String name = role.getName();

        String insertTableSQL = "INSERT INTO ROLE"
                + "(ROLENAME) VALUES"
                + "(?)";
        try {
            connection = createConnection();
            preparedStatement = connection.prepareStatement(insertTableSQL,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    role.setId(generatedKeys.getLong(1));
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

    @Override public void update(Role role) {
        try {
            createConnection().createStatement().execute("UPDATE ROLE"
                    + " SET ROLENAME = "+"'"+role.getName()+"'"
                    + " WHERE ROLE_ID = " + "'"+role.getId()+"'" + ";");
        } catch (SQLException e) {
            e.printStackTrace();
    }}

    @Override public void remove(Role role) {
        try {
            createConnection().createStatement().execute("DELETE FROM ROLE WHERE ROLE_ID = "+"'"+role.getId()+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override public Role findByName(String name) {
        Role role = null;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = createConnection();
            statement = connection.createStatement();
            String s = "Select * From ROLE where ROLENAME = " + "'" + name + "'";
            ResultSet rst;
            rst = statement.executeQuery(s);
            while (rst.next()) {
                role = new Role(rst.getLong("ROLE_ID"), rst.getString("ROLENAME"));
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

        return role;
    }
}
