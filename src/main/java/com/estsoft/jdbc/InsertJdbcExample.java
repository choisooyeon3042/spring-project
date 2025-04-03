package com.estsoft.jdbc;

import java.sql.*;

/*
 * 1. DB연결
 * 2. SQL 실행 -> INSERT INTO 쿼리
 * 3. 결과 출력 -> insert 개수
 * */
public class InsertJdbcExample {
    private static final String url = "jdbc:mysql://localhost:3306/test_db";
    private static final String username = "root";
    private static final String password = "0000";

    public static void main(String[] args) {
        try (
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement statement = conn.createStatement();
        ) {
            String insertQuery = "INSERT INTO students (id, name, age, address) VALUES "
                    + "(5, 'sooyeon', 20, 'address'),"
                    + "(11, 'jam', 24, 'address2')";

            int rowInsert = statement.executeUpdate(insertQuery);

            System.out.println("insert 개수: " + (rowInsert));
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}
