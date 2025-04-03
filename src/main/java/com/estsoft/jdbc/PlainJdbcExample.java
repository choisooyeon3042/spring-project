package com.estsoft.jdbc;

import java.sql.*;

/*
* 1. DB연결
* 2. SQL 실행
* 3. 결과 출력
* */
public class PlainJdbcExample {
    private static final String url = "jdbc:mysql://localhost:3306/test_db";
    private static final String username = "root";
    private static final String password = "0000";

    public static void main(String[] args) {

        try (
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from students")) {

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Age: " + resultSet.getInt("age"));
                System.out.println("Address: " + resultSet.getString("address"));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }

    }
}
