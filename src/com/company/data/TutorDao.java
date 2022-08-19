package com.company.data;

import com.company.Course;
import com.company.Session;
import com.company.Tutor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TutorDao extends Dao {

    public static void tutorForCourse(int course, int tutor) throws SQLException {
        String sql = "INSERT INTO `users_courses`(`cid`, `uid`) VALUES ('"+ course + "','" + tutor + "')";
        PreparedStatement ps = getConn().prepareStatement(sql);
        ps.executeUpdate();

    }
}
