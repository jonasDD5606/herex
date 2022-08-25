package com.company.gui;

import com.company.data.AdminDao;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class  LoginFrame extends JFrame {
    private static LoginFrame lf;
    private JPanel jPanel1 ,jPanel2, jPanel3;
    private JButton jbtn;

    Dimension dim;

    public static LoginFrame getInstance() throws IOException {
        if (lf == null) {
            lf = new LoginFrame();
        }

        return lf;
    }
    public void closeLf() throws IOException {
        lf.setVisible(false);
    }


    public LoginFrame() throws IOException {
        this.setVisible(false);



    }
    public void initLogin(Dimension dimension) throws IOException {
            lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            lf.setTitle("Login");


            dim = new Dimension(dimension);
            lf.setSize(dimension);

            lf.add(getjPanel1(), BorderLayout.CENTER);
            lf.add(getjPanel2(), BorderLayout.WEST);
            lf.add(getjPanel3(), BorderLayout.EAST);



            lf.setVisible(true);


    }

    public JPanel getjPanel1() {
        if(jPanel1 == null){
            jPanel1 = new JPanel();
        }
        jPanel1.setBackground(Color.darkGray);

        final JTextField email = new JTextField(9);
        Dimension dimension = new Dimension(dim.width / 10, dim.height/32);
        email.setPreferredSize(dimension);
        jPanel1.add(new JLabel("Email: ")).setForeground(Color.white);
        jPanel1.add(email);
        final JPasswordField passwordField = new JPasswordField(9);
        Dimension dimension1 = new Dimension(dim.width / 10, dim.height/32);
        passwordField.setPreferredSize(dimension1);
        jPanel1.add(new JLabel("Password: ")).setForeground(Color.white);
        jPanel1.add(passwordField);
        jbtn = new JButton("Login");
        jbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                String passWordValue = passwordField.getText();
                String textFieldValue = email.getText();
                try {
                    if (AdminDao.Login(textFieldValue, passWordValue) == true){
                        MainFrame.login = true;
                        MainFrame mf = new MainFrame();
                    }
                } catch (SQLException | InvalidKeySpecException | NoSuchAlgorithmException | IllegalBlockSizeException | InvalidKeyException | BadPaddingException | NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        jPanel1.add(jbtn);


        return jPanel1;
    }

    public JPanel getjPanel2() throws IOException {
        if(jPanel2 == null){
            jPanel2 = new JPanel();
        }
        Color color = new Color(29,29,27);
        jPanel2.setBackground(color);

        Dimension dimmension = new Dimension(dim.width/6, dim.height);
        jPanel2.setPreferredSize(dimmension);
        BufferedImage myPicture = ImageIO.read(new File("src/com/company/gui/9962662704_fda962d06a_b (1).png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        Dimension dimFoto = new Dimension(dim.width/18, dim.height/22);
        //picLabel.setPreferredSize(dimFoto);
        jPanel2.add(picLabel);

        return jPanel2;
    }
    public JPanel getjPanel3() {
        if(jPanel3 == null){
            jPanel3 = new JPanel();
        }
        Color color = new Color(29,29,27);
        jPanel3.setBackground(color);

        Dimension dimmension = new Dimension(dim.width/6, dim.height);
        jPanel3.setPreferredSize(dimmension);

        return jPanel3;
    }
}
