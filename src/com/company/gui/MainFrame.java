package com.company.gui;


import com.company.*;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class MainFrame extends JFrame {
    private JPanel jp1;
    private JPanel jp2;
    private JPanel jp3;
    private JPanel jp4;
    private JPanel jp5;
    private JPanel jp6;
    private JPanel jp7;
    private JPanel jp8;
    private ScrollPane sp;
    private ScrollPane sp2;
    private ScrollPane sp3;


    public ScrollPane getSp() throws SQLException {
        if(sp == null){
            sp = new ScrollPane();
        }
        sp.setVisible(true);
        sp.setPreferredSize(new Dimension(screenSize.width / 4, screenSize.height * 2 / 3));
        sp.add(getJp6());
        sp.setBackground(Color.darkGray);
        return sp;
    }
    public ScrollPane getSp2() throws SQLException {
        if(sp2 == null){
            sp2 = new ScrollPane();
        }
        sp2.setVisible(true);
        sp2.setPreferredSize(new Dimension(screenSize.width / 4, screenSize.height * 2 / 3));
        sp2.add(getJp7());
        sp2.setBackground(Color.darkGray);
        return sp2;
    }
    public ScrollPane getSp3() throws SQLException {
        if(sp3 == null){
            sp3 = new ScrollPane();
        }
        sp3.setVisible(true);
        sp3.setPreferredSize(new Dimension(screenSize.width / 4, screenSize.height * 2 / 3));
        sp3.add(getJp8());
        sp3.setBackground(Color.darkGray);
        return sp3;
    }


    private Dimension screenSize;
    static boolean login = false;
    int num = 0;

    public void setNum(int num) {
        this.num = num;
    }


    public void setScreenSize(Dimension screenSize) {
        this.screenSize = screenSize;
    }

    public void popupMainMenu(int num){
        jp6.removeAll();
        setScreenSize(Toolkit.getDefaultToolkit().getScreenSize());
        for (int i = num;i < Course.courses.size(); i++) {
            JButton btn = new JButton(Course.courses.get(i + num).getName());
            JPanel btnpan = new JPanel();
            final int id = Course.courses.get(i).getId();
            final Course crs = Course.courses.get(i + num);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GuiHelpers.setTargetCourse(crs);
                    jp7.setVisible(true);
                    jp8.setVisible(true);
                    jp6.setVisible(false);
                    sp2.setVisible(true);
                    sp.setVisible(false);
                    jp7.add(getJp5());

                }
            });
            btnpan.setPreferredSize(new Dimension(screenSize.width / 6, screenSize.height / 25));
            btn.setBackground(Color.darkGray);
            btnpan.add(btn);
            btnpan.setBackground(Color.darkGray);
            btn.setForeground(new Color(174, 4, 13));
            jp6.add(btnpan, BoxLayout.X_AXIS);
        }


    }

    public MainFrame() throws IOException, SQLException {
        this.setScreenSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setSize(screenSize);

        if (login == false) {
            Dimension dim = new Dimension(screenSize.width * 2 / 3, screenSize.height * 2 / 3);
            LoginFrame.getInstance().initLogin(dim);
            this.setVisible(false);

        } else {
            LoginFrame.getInstance().closeLf();
            this.setTitle("Tutoring Ehb");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.add(getJp3(), BorderLayout.NORTH);
            this.add(getJp1(), BorderLayout.WEST);
            this.add(getJp2(), BorderLayout.CENTER);
            this.add(getJp4(), BorderLayout.EAST);
            this.setVisible(true);
            this.show();
        }

    }

    JPanel getJp1() throws IOException, SQLException {
        //NAV
        if (jp1 == null) {
            jp1 = new JPanel();
        }
        jp1.setBackground(Color.black);
        Dimension dimJp = new Dimension(screenSize.width / 18, screenSize.height);
        jp1.setPreferredSize(dimJp);
        FrameSituations nav = new FrameSituations();
        nav.setNavPan(jp1, getJp8(), getJp6(), getSp3(), getSp());

        return jp1;
    }

    JPanel getJp2() throws SQLException {
        if (jp2 == null) {
            jp2 = new JPanel();
        }
        JLabel label2 = new JLabel(" ");
        label2.setForeground(Color.red);
        jp2.add(label2);
        jp2.setBackground(Color.darkGray);
        Dimension dimJp = new Dimension(screenSize.width / 82, screenSize.height);
        jp2.setPreferredSize(dimJp);
        jp2.add(getSp());
        jp2.add(getSp2());
        jp2.add(getSp3());
        jp8.setVisible(true);
        jp7.setVisible(true);
        sp2.setVisible(false);
        sp3.setVisible(false);

        return jp2;
    }

    JPanel getJp3() throws IOException {
        if (jp3 == null) {
            jp3 = new JPanel();
        }
        Dimension dimJp = new Dimension(screenSize.width, screenSize.height / 22);
        jp3.setPreferredSize(dimJp);
        Color color = new Color(29, 29, 27);
        jp3.setBackground(color);
        BufferedImage myPicture = ImageIO.read(new File("src/com/company/gui/9962662704_fda962d06a_b (1).png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        Dimension dimFoto = new Dimension(screenSize.width / 18, screenSize.height / 22);
        picLabel.setPreferredSize(dimFoto);
        jp3.add(picLabel);

        return jp3;
    }

    JPanel getJp4() throws IOException {
        if (jp4 == null) {
            jp4 = new JPanel();
        }
        jp4.setBackground(Color.darkGray);
        Dimension dimJp = new Dimension(screenSize.width / 18, screenSize.height);
        jp4.setPreferredSize(dimJp);

        return jp4;
    }

    JPanel getJp6() throws SQLException {
        if (jp6 == null) {
            jp6 = new JPanel();
        }
        int  num = 0;
        int num2 = 12;
        GuiHelpers.showCourses();
        JPanel panel = new JPanel();
        Color color = new Color(48, 46, 46);
        panel.setBackground(color);
        Dimension dimSp = new Dimension(screenSize.width / 4, screenSize.height * 2 / 3);
        jp6.setPreferredSize(dimSp);
        jp6.setLocation((screenSize.width / 2) - 10, screenSize.height / 2);
        FrameSituations situation = new FrameSituations();
        popupMainMenu(num);

        return jp6;
    }
    JPanel getJp7() {
        if (jp7 == null) {
            jp7 = new JPanel();
        }
        Color color = new Color(48, 46, 46);
        jp7.setBackground(color);
        Dimension dimSp = new Dimension(screenSize.width / 4, screenSize.height * 2 / 3);
        jp7.setForeground(Color.gray);
        jp7.setPreferredSize(dimSp);
        return jp7;
    }
    public JPanel getJp5() {
        if (jp5 == null) {
            jp5 = new JPanel();
        }
        if (GuiHelpers.targetCourse != null) {
            jp5.removeAll();
            jp5.setVisible(true);
            jp5.setBackground(Color.gray);
            jp5.setPreferredSize(new Dimension(screenSize.width / 6, screenSize.height / 20));
            JButton qbtn = new JButton("Stel een vraag");
            JButton backbtn = new JButton("terug gaan");
            backbtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sp2.setVisible(false);
                    sp.setVisible(true);
                    jp6.setVisible(true);
                    sp3.setVisible(false);
                }
            });
            jp5.add(backbtn);


            JPanel pnl = new JPanel();
            pnl.setPreferredSize(new Dimension(screenSize.width / 6, screenSize.height / 20));
            pnl.setBackground(Color.gray);
            jp8.add(pnl);
            FrameSituations situation = new FrameSituations();
            try {
                if (Session.current instanceof Teacher){
                    sp3.setVisible(true);

                    situation.setTeacherOnCourseSituation(jp7, jp8, GuiHelpers.targetCourse.getId(), jp5);
                }else if(Session.current instanceof Student){

                    situation.setStudentCourseSituation(jp7, jp6,  GuiHelpers.targetCourse.getId(), Session.current.getId());
                }else if(Session.current instanceof  Tutor){
                    situation.setTutorCourseSituation(jp7, GuiHelpers.targetCourse.getId(), Session.current.getId());
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

        return jp5;
    }

    public JPanel getJp8() {
        if (jp8 == null) {
            jp8 = new JPanel();
        }
        Color color = new Color(48, 46, 46);
        jp8.setBackground(color);
        Dimension dimSp = new Dimension(screenSize.width / 4, screenSize.height * 2 / 3);
        jp8.setForeground(Color.gray);
        jp8.setPreferredSize(dimSp);
        jp8.setVisible(false);
        return jp8;
    }
}