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



    class Kal {
        private int v;

        public int hit(int n, int y){
            return v = n + y;
        }

        public int getV(){
            return v;
        }
    }


    class Hitu extends Kal{

        @Override
        public int getV() {
            return super.getV();
        }

        @Override
        public int hit(int n, int y) {
            return super.hit(n, y);
        }
    }

    @org.junit.jupiter.api.Test
    void name() {
        Hitu hitu = new Hitu();

        hitu.hit(5,7);

        System.out.println(hitu.getV());
    }
}
