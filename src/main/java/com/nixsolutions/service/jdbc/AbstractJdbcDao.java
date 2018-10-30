package com.nixsolutions.service.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public abstract class AbstractJdbcDao {
    private static BasicDataSource dataSource = null;

    public static synchronized BasicDataSource getDataSource() {

        if (dataSource == null) {
            BasicDataSource basicDataSource = new BasicDataSource();
            ResourceBundle resourceBundle = ResourceBundle.getBundle("h2");
            //basicDataSource.setDriverClassName("jdbc.driver");
            basicDataSource.setUrl(resourceBundle.getString("jdbc.url"));
            basicDataSource
                    .setUsername(resourceBundle.getString("jdbc.username"));
            basicDataSource
                    .setPassword(resourceBundle.getString("jdbc.password"));
            basicDataSource.setMinIdle(5);
            basicDataSource.setMaxIdle(10);
            basicDataSource.setMaxOpenPreparedStatements(200);
            dataSource = basicDataSource;
        }
        return dataSource;
    }

    public Connection createConnection() throws SQLException {
        if (dataSource == null) {
            getDataSource();
        }
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);

        return connection;
    }
}
