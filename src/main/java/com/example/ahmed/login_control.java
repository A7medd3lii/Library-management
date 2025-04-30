package com.example.ahmed;

import com.example.ahmed.Admins;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class login_control {

    Statement st ;
    public boolean islogin (Admins m ) throws SQLException {

        st = com.example.ahmed.DB. open_connection().createStatement();
        ResultSet res = st.executeQuery("select * from admins where Name = '" + m.getM_Name() + "'and ID = '" + m.getM_ID()+"'");
        if (res.next())
            return true;
        return false ;

    }

}
