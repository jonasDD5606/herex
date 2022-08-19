package com.company.data;

import com.company.AES;
import com.company.Session;
import com.company.User;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;

public class Dao {
    static Connection conn;

    public Dao() {

    }

    public static Connection getConn() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = Database.getInstance().getConnection();
        }
        return conn;
    }

    public static boolean Login(String email, String password) throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
        boolean checkLogin = false;
        Session.current = new User(email);
        email = AES.encryptToHex("u7k3g4e6n7t6h7l2", "9876543210fedcba", email);
        password = AES.encryptToHex("u7k3g4e6n7t6h7l2", "9876543210fedcba", password);

        String host = "127.0.0.1";
        String urlstr = "http://" + host + "/HerEx/scripts/loginjava.php?+var1=" + email + "&var2=" + password;
        URL url = null;
        try {
            url = new URL(urlstr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            URLConnection connection = url.openConnection();
            connection.getInputStream();
            if (connection.getURL().toString().equals("http://" + host + "/HerEx/scripts/loginjava.php?+check=true")) {
                checkLogin = true;
                getCurUser(Session.current.getEmail());


            } else {
                Session.current.setEmail("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return checkLogin;
    }

    public static void getCurUser(String mail) throws SQLException {
        String query = "SELECT * FROM users WHERE email= '" + mail + "'";
        Statement stmt = getConn().createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            int id = rs.getInt("id");
            String role = rs.getString("role");
            String conf = rs.getString("confirmed");
            String fname = rs.getString("firstname");
            String lname = rs.getString("lastname");
            String email = rs.getString("email");
            User usr = new User(id, fname, lname, email, role, conf);
            User.setCurUser(usr);
        }

    }
}
