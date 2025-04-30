package com.example.ahmed;
import java.sql.*;
public class DB {

    private static Connection con = null ;

    private DB(Connection con) {

    }

    public static Connection open_connection () throws SQLException {

        if (con == null)
        {
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/library" , "root", "");
        }
        return con ;
    }

    public static void close_connection (){
        if (con != null)
            con = null;
    }




}
