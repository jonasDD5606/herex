package com.company.data;
import java.sql.*;


public class Database {
    private static Database instance;
    private Connection conn = null;
    private String url = "jdbc:mariadb://127.0.0.1:3306/herexdh";
    private String uname = "User";
    private String pw = "C3Wf6]pFR7ZBLz.v";

    private Database()
    {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static Database getInstance() {
        if(instance== null)
            instance = new Database();
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if(conn==null || conn.isClosed())
        {
            conn = DriverManager.getConnection(url, uname, pw);
        }
        return conn;
    }
}