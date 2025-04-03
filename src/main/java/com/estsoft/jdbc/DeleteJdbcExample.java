package com.estsoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * 1. DB연결
 * 2. SQL 실행 -> DELETE 처리
 * 3. 결과 출력 -> DELETE 개수
 * */
public class DeleteJdbcExample {
    private static final String url = "jdbc:mysql://localhost:3306/test_db";
    private static final String username = "root";
    private static final String password = "0000";

    public static void main(String[] args) {
        try (
                Connection conn = DriverManager.getConnection(url, username, password);
                Statement statement = conn.createStatement();
        ) {
            String deleteQuery = "DELETE FROM students WHERE name = 'jam'";

            int rowDelete = statement.executeUpdate(deleteQuery);

            System.out.println("삭제 row 수: " + rowDelete);

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}
