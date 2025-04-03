package com.estsoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/*
 * 1. DB연결
 * 2. SQL 실행 -> UPDATE 처리
 * 3. 결과 출력 -> UPDATE 개수
 * */
public class UpdateJdbcExample {
    private static final String url = "jdbc:mysql://localhost:3306/test_db";
    private static final String username = "root";
    private static final String password = "0000";

    public static void main(String[] args) {
        try (
                Connection conn = DriverManager.getConnection(url, username, password);
                Statement statement = conn.createStatement();
        ) {
            String updateQuery = "UPDATE students SET name = 'est sooyeon' WHERE id = 5";

            int rowUpdate = statement.executeUpdate(updateQuery);

            System.out.println("update 개수: " + (rowUpdate));
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}
