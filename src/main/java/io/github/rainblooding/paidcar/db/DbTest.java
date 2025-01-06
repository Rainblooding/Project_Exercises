package io.github.rainblooding.paidcar.db;

import io.github.rainblooding.pool.datasource.SkyDragonDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbTest {

    public static void main(String[] args) throws SQLException {
        SkyDragonDataSource dataSource = new SkyDragonDataSource();
        dataSource.setDriverClass("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:tcp://127.0.0.1:9092/E:/program/h2DB/task");
        dataSource.setUser("root");
        dataSource.setPassword("chen");

        dataSource.init();

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from task");
        while(resultSet.next()) {
            System.out.println(resultSet.getLong("id"));
            System.out.println(resultSet.getString("title"));
        }
        resultSet.close();
        statement.close();
        connection.close();
        dataSource.destroy();


    }
}
