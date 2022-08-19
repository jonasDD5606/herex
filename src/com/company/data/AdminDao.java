package com.company.data;


import java.sql.*;
import java.util.ArrayList;

public class AdminDao extends Dao {

    public static void adUser() {
        try {
            Connection c = getConn();
            PreparedStatement ps = c.prepareStatement("INSERT INTO `users` (`id`, `firstname`, `lastname`, `email`, `password`, `confirmed`, `role`) VALUES (NULL, 'j', 'j', 'joko', 'j', 'T', 'S')");
            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<String> getQuestions(){
        return null;
    }
    public static void confirmTutor(int tutor) throws SQLException {
       String sql =  "UPDATE `users` SET `confirmed`='T' WHERE id = " + tutor + ";";
       PreparedStatement ps = getConn().prepareStatement(sql);
       ps.executeUpdate();
       conn.close();
    }

}
