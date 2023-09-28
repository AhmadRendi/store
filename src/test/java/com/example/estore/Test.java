package com.example.estore;

import java.sql.*;



public class Test {


    @org.junit.jupiter.api.Test
    void test() throws SQLException {
        String url = "jdbc:mysql://localhost:port/nama_database";
        String username = "";
        String password = " ";
        Connection connection = DriverManager.getConnection(url, username, password);
        connection.close();

        String sql = """
                SELECT * FROM 
                """;

        try(Statement statement = connection.createStatement()){

            statement.executeUpdate(sql);

        }catch (SQLException exception){

        }
    }



}
