package com.company.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TutorDao extends Dao {

    public static void tutorForCourse(int course, int tutor) throws SQLException {
        String sql = "INSERT INTO `users_courses`(`cid`, `uid`) VALUES ('"+ course + "','" + tutor + "')";
        PreparedStatement ps = getConn().prepareStatement(sql);
        ps.executeUpdate();

    }
    public static boolean tutorAlreadyInTutoring(int course, int tutor){
        String query = "SELECT * FROM tutoring WHERE ( cid =" + course + " AND tid = " + tutor + ");";
        try {
            Statement statement = getConn().createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
