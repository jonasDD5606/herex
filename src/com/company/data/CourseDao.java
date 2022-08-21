package com.company.data;

import com.company.Course;
import com.company.Session;
import com.company.Student;
import com.company.Tutor;

import java.sql.*;
import java.util.ArrayList;

public class CourseDao extends Dao {
    public static void getAllTutorsPerCourse(int courseid) {
        Session.tutorPerCourse = new ArrayList<Tutor>();
        String query = "SELECT uid FROM users_courses RIGHT JOIN users ON users_courses.uid = users.id WHERE (cid=" + courseid + " AND role = 'T');";
        int id = 0;
        String tutorname;
        String role;
        try {
            Statement stmt = getConn().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("uid");
                String query2 = "SELECT firstname, lastname FROM users WHERE id =" + id + ";";
                Statement statement = getConn().createStatement();
                ResultSet result = statement.executeQuery(query2);
                while (result.next()) {
                    tutorname = result.getString("firstname") + " " + result.getString("lastname");
                    Tutor tutor = new Tutor(tutorname, id);
                    Session.tutorPerCourse.add(tutor);
                }

            }


            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void getAllStudentPerCourse(int courseid) {
        Session.studentPerCourse = new ArrayList<Student>();
        String query = "SELECT uid FROM users_courses RIGHT JOIN users ON users_courses.uid = users.id WHERE (cid=" + courseid + " AND role = 'S');";
        int id = 0;
        String tutorname;
        String role;
        try {
            Statement stmt = getConn().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("uid");


            }
            String query2 = "SELECT firstname, lastname FROM users WHERE id =" + id + ";";
            Statement statement = getConn().createStatement();
            ResultSet result = statement.executeQuery(query2);
            while (result.next()) {
                String fname = result.getString("firstname");
                String lname = result.getString("lastname");
                Student student = new Student(id, fname, lname);
                Session.studentPerCourse.add(student);
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ArrayList<Course> getAllCourses() throws SQLException {
        String query = "select * from courses", cname = null;
        ArrayList<Course> arrayList = new ArrayList<Course>();
        int id = 0;
        int year = 0;
        int admin = 0;
        try {
            Statement stmt = getConn().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                cname = rs.getString("name");
                id = rs.getInt("id");
                year = rs.getInt("year");
                admin = rs.getInt("admin");
                Course crs = new Course(id, cname, year, admin);
                arrayList.add(crs);
            }
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arrayList;
    }

    public static void giveStudentPerCourse(int course, int student) throws SQLException {
        String sql = "INSERT INTO `users_courses`(`cid`, `uid`) VALUES ('" + course + "','" + student + "')";
        PreparedStatement ps = getConn().prepareStatement(sql);
        ps.executeUpdate();

    }

    public static void insertCour(String name, int year) throws SQLException {
        String sql = "INSERT INTO courses ( id, name, year) VALUES (NULL, '" + name + "', " + year + " )";
        PreparedStatement ps = getConn().prepareStatement(sql);
        ps.executeUpdate();
    }

    public static void askHelp(int tutor, int student, int course) {
        try {
            Connection c = getConn();
            PreparedStatement ps = c.prepareStatement("INSERT INTO `tutoring`VALUES ('" + tutor + "', '" + student + "', '" + course + "', NULL, NULL, 'T' )");
            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteFromWaitList(int course, int student){
        String sql = "DELETE FROM `users_courses` WHERE (cid =" + course + " AND uid =" + student + ");";
        System.out.println(sql);
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
