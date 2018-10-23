package com.nixsolutions.service.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AbstractJdbcDao {
    static Connection connection;
    private static BasicDataSource dataSource;

    public static BasicDataSource getDataSource() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("h2");
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(resourceBundle.getString("jdbc.driver"));
        ds.setUrl(resourceBundle.getString("jdbc.url"));
        ds.setUsername(resourceBundle.getString("jdbc.username"));
        ds.setPassword(resourceBundle.getString("jdbc.password"));

        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
        dataSource = ds;
        return dataSource;
    }

    public static Connection createConnection() {

        try {
            BasicDataSource basicDataSource = AbstractJdbcDao.getDataSource();
            connection = basicDataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e.getCause());
        }
        return connection;
    }

    public void createTables() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("drop table IF EXISTS USER");
        statement.execute("drop table IF EXISTS ROLE");
        statement.execute("CREATE TABLE IF NOT EXISTS ROLE("
                + "ROLE_ID INT(11) NOT NULL auto_increment primary key, "
                + "ROLENAME VARCHAR);");
        statement.execute("CREATE TABLE IF NOT EXISTS USER("
                + "USER_ID INT(11) NOT NULL auto_increment primary key, "
                + "LOGIN VARCHAR, " + "PASSWORD VARCHAR, " + "EMAIL VARCHAR, "
                + "FIRSTNAME VARCHAR, " + "LASTNAME VARCHAR, " + "DATE DATE,"
                + "ROLE_ID INT(11),"
                + "FOREIGN KEY(ROLE_ID) REFERENCES ROLE(ROLE_ID));");

    }
}
