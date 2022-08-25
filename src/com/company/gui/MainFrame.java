package com.company.gui;


import com.company.*;
import com.company.data.CourseDao;
import com.company.data.StudentDao;
import com.company.data.TutorDao;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private JPanel jp1;
    private JPanel jp2;
    private JPanel jp3;
    private JPanel jp4;
    private JPanel jp5;
    private JComboBox jcb;
    private JPanel jp6;
    private JPanel jp7;
    private JPanel jp8;
    private JPanel jp9;
    private JPanel jp10;
    private Dimension screenSize;
    static boolean login = false;

    public void setNav(JPanel navpan){
        if (Session.current instanceof Student){
            JPanel panel = new JPanel();
            JButton jbmytutors = new JButton("Mijn Tutoren");
            jbmytutors.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        Session.tutorPerStud = StudentDao.getTutorsPerStudent(Session.current.getId());

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    System.out.println(Session.tutorPerStud.size());
                    jp7.removeAll();
                    for (int i = 0; i < Session.tutorPerStud.size(); i++) {
                        JPanel pnl = new JPanel();
                        JLabel lbl = new JLabel(Session.tutorPerStud.get(i).getCourse().getName() + " : " + Session.tutorPerStud.get(i).getTutorName());
                        pnl.add(lbl);
                        pnl.setPreferredSize(new Dimension(screenSize.width/6, screenSize.height/25));
                        jp7.setVisible(true);
                        jp7.add(pnl);
                    }

                    jp6.setVisible(true);
                }
            });

            panel.add(jbmytutors);
            navpan.add(panel);



        }else if(Session.current instanceof Tutor){
            JPanel panel = new JPanel();
            JButton jpButton = new JButton("tutoring aanvragen");
            panel.setPreferredSize(new Dimension(screenSize.width/6, screenSize.height/25));
            panel.add(jpButton);
            navpan.add(panel);


        }else if(Session.current instanceof Teacher){
            JPanel panel = new JPanel();
            JButton jpButton = new JButton("voeg vak toe");
            panel.setPreferredSize(new Dimension(screenSize.width/6, screenSize.height/25));
            JPanel panel2 = new JPanel();
            JButton btntut = new JButton("Tutors accepteren");
            panel2.add(btntut);
            panel.add(jpButton);
            navpan.add(panel);
            navpan.add(panel2);
            JTextField jtxxt = new JTextField(10);
            JTextField jtextyear = new JTextField(10);
            jpButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton btn = new JButton("Voeg toe");
                    jp9.setVisible(true);
                    JLabel lbl1 = new JLabel("Naam : ");
                    JLabel lbl2 = new JLabel("Jaar: ");
                    jp9.add(lbl1);
                    jp9.add(jtxxt);
                    jp9.add(lbl2);
                    jp9.add(jtextyear);
                    jp9.add(btn);
                    btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String name = jtxxt.getText();
                            String year = jtextyear.getText();
                            int y = Integer.parseInt(year);
                            try {
                                CourseDao.insertCour(name, y);
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                    });

                }
            });
        }
    }
    public void setPanel(JPanel panel){

    }
    public void closePanel(JPanel panel){

    }
    public void openPanel(JPanel panel){

    }

    public void setScreenSize(Dimension screenSize) {
        this.screenSize = screenSize;
    }


    public MainFrame() throws IOException, SQLException {
        this.setScreenSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setSize(screenSize);

        if(login == false){
            Dimension dim = new Dimension(screenSize.width * 2/ 3, screenSize.height * 2 /3);
            LoginFrame.getInstance().initLogin(dim);
            this.setVisible(false);

        }else{
            LoginFrame.getInstance().closeLf();
            this.setTitle("Tutoring Ehb");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.add(getJp3(), BorderLayout.NORTH);
            this.add(getJp1(), BorderLayout.WEST);
            this.add(getJp2(), BorderLayout.CENTER);
            this.add(getJp4(), BorderLayout.EAST);
            //System.out.println(screenSize.height);


            this.setVisible(true);
            this.show();
        }



        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.pack();

    }
    JPanel getJp1() throws IOException {
        //NAV
        if(jp1 == null){
            jp1 = new JPanel();
        }
        jp1.setBackground(Color.black);
        Dimension dimJp = new Dimension(screenSize.width/18,screenSize.height);
        jp1.setPreferredSize(dimJp);
        setNav(jp1);


        return jp1;
    }
    JPanel getJp2() throws SQLException {
        if(jp2 == null){
            jp2 = new JPanel();
        }
        JLabel label2 = new JLabel(" ");
        label2.setForeground(Color.red);
        jp2.add(label2);
        jp2.setBackground(Color.darkGray);
        Dimension dimJp = new Dimension(screenSize.width/82,screenSize.height);
        jp2.setPreferredSize(dimJp);
        jp2.add(getJp6(), BoxLayout.X_AXIS);
        jp2.add(getJp7(), BoxLayout.Y_AXIS);
        jp2.add(getJp9(), BoxLayout.X_AXIS);
        jp7.setVisible(false);
        jp9.setVisible(false);
        JPanel panel = getJp8();
        panel.setVisible(false);
        jp2.add(panel);

        return jp2;
    }
    JPanel getJp3() throws IOException {
        if(jp3 == null){
            jp3 = new JPanel();
        }
        Dimension dimJp = new Dimension(screenSize.width, screenSize.height/22);
        jp3.setPreferredSize(dimJp);
        Color color = new Color(29,29,27);
        jp3.setBackground(color);
        BufferedImage myPicture = ImageIO.read(new File("src/com/company/gui/9962662704_fda962d06a_b (1).png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        Dimension dimFoto = new Dimension(screenSize.width/18, screenSize.height/22);
        picLabel.setPreferredSize(dimFoto);
        jp3.add(picLabel);





        return jp3;
    }
    JPanel getJp4() throws IOException {
        if(jp4 == null){
            jp4 = new JPanel();
        }
        jp4.setBackground(Color.darkGray);
        Dimension dimJp = new Dimension(screenSize.width/18,screenSize.height);
        jp4.setPreferredSize(dimJp);

        return jp4;
    }

    JComboBox getJcb(){
        if(jcb == null){
            jcb = new JComboBox();
        }
        JLabel label2 = new JLabel("NAV");
        label2.setForeground(Color.red);
        jcb.add(label2);
        return jcb;
    }
    JPanel getJp6() throws SQLException {
        if(jp6 == null){
            jp6 = new JPanel();
        }
        GuiHelpers.showCourses();
        Color color = new Color(48,46,46);
        jp6.setBackground(color);
        Dimension dimSp = new Dimension(screenSize.width/4, screenSize.height * 2 / 3);
        jp6.setPreferredSize(dimSp);
        jp6.setLocation((screenSize.width/2) - 10, screenSize.height/2);
        Dimension dimension = new Dimension(screenSize.width/20, screenSize.height/25);
        for(int i = 0; i < Course.courses.size(); i++) {
            JButton btn = new JButton(Course.courses.get(i).getName());
            JPanel btnpan = new JPanel();
            final int id = Course.courses.get(i).getId();
            final Course crs = Course.courses.get(i);

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GuiHelpers.setTargetCourse(crs);
                    jp6.setVisible(false);
                    jp7.setVisible(false);
                    jp7.add(getJp8());
                    jp9.removeAll();
                    if(Session.current instanceof Student || Session.current instanceof Tutor){
                        jp9.setVisible(false);

                    }



                }
            });
            btn.setBackground(Color.darkGray);
            btnpan.add(btn);
            btnpan.setBackground(Color.darkGray);
            btn.setForeground(new Color(174,4,13));
            btnpan.setPreferredSize(new Dimension(screenSize.width/6, screenSize.height/25));
            jp6.add(btnpan, BoxLayout.X_AXIS);
        }
        return jp6;
    }
    JPanel getJp7(){
        if(jp7 == null){
            jp7 = new JPanel();
        }
        Color color = new Color(174,4,13);
        jp7.setBackground(color);
        Dimension dimSp = new Dimension(screenSize.width/4, screenSize.height * 2 / 3);
        jp7.setForeground(Color.gray);
        jp7.setPreferredSize(dimSp);
        return jp7;
    }

    public JPanel getJp8() {
        if(jp8 == null){
            jp8 = new JPanel();
        }else if(GuiHelpers.targetCourse != null){
            jp8.removeAll();
            jp7.removeAll();
            jp8.setVisible(true);
            jp7.setVisible(true);
            JLabel welcome = new JLabel("Welkom bij: ");
            JLabel course = new JLabel(GuiHelpers.targetCourse.getName());
            JButton backbtn = new JButton("terug gaan");
            JButton qbtn = new JButton("Stel een vraag");
            jp8.add(backbtn);
            jp8.add(welcome);
            jp8.add(course);
            backbtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jp8.setVisible(false);
                    jp6.setVisible(true);
                    jp7.setVisible(false);
                    jp9.setVisible(false);
                }
            });
            if(Session.current instanceof Student){
                jp8.add(qbtn);


            qbtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jp8.setVisible(false);
                    jp7.add(getJp5());
                    jp5.setVisible(true);
                }
            });
                JButton tutorbtn = new JButton("Tutor aanvragen");
                JPanel pnl = new JPanel();
                pnl.add(tutorbtn);
                tutorbtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    }
                });
            jp7.add(pnl);



            } else if(Session.current instanceof Tutor){
                JButton tutorbtn = new JButton("voor dit vak tutoren");
                JPanel pnl = new JPanel();
                pnl.setPreferredSize(new Dimension(screenSize.width/6, screenSize.height/25));
                pnl.add(tutorbtn);
                jp7.add(pnl);

                tutorbtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            TutorDao.tutorForCourse(GuiHelpers.targetCourse.getId(), Session.current.getId());
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                });
            }else if(Session.current instanceof Teacher){
                CourseDao.getAllTutorsPerCourse(GuiHelpers.targetCourse.getId());
                for(int i = 0; i < Session.tutorPerCourse.size(); i++){
                    JPanel pnl = new JPanel();
                    JLabel lbl = new JLabel(Session.tutorPerCourse.get(i).getTutorName());
                    Tutor target = Session.tutorPerCourse.get(i);
                    int cid = GuiHelpers.targetCourse.getId();
                    JButton autoCombine = new JButton("Kopel met");
                    JTextArea student = new JTextArea();

                    autoCombine.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String textvalue = student.getText();
                            if(textvalue.equals(null)){

                            }else{
                                try {
                                    int num = Integer.parseInt(textvalue);
                                } catch (NumberFormatException nfe) {

                                }

                            }
                            }


                    });

                    pnl.add(lbl);
                    pnl.add(autoCombine);
                    pnl.add(student);
                    pnl.setPreferredSize(new Dimension(screenSize.width/6, screenSize.height/25));
                    jp7.add(pnl);

                }

            }

            CourseDao.getAllStudentPerCourse(GuiHelpers.targetCourse.getId());
            for (int i = 0; i < Session.studentPerCourse.size(); i++){

                JPanel pnl = new JPanel();
                String str = Session.studentPerCourse.get(i).getId() + " " + Session.studentPerCourse.get(i).getFirstname() + " " + Session.studentPerCourse.get(i).getLastname();
                JLabel lbl = new JLabel(str);
                pnl.add(lbl);
                jp7.add(pnl);

            }


            jp9.setVisible(true);
        }

        return jp8;
    }
    public JPanel getJp5(){
        if(jp5 == null){
           jp5 = new JPanel();
        }
        jp7.removeAll();
        jp5.removeAll();
        jp7.setVisible(true);
        final JTextField question = new JTextField(20);
        Dimension dimension = new Dimension(screenSize.width / 10, screenSize.height/32);
        question.setPreferredSize(dimension);

        JButton submit = new JButton("Stuur");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textFieldValue = question.getText();
                try {
                    StudentDao.askQuestion(textFieldValue,27);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
        JButton backbtn = new JButton("Terug gaan");
        backbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jp5.setVisible(false);
                jp8.setVisible(true);
                jp7.add(getJp8());
            }
        });
        jp5.add(backbtn);
        jp5.add(question);
        jp5.add(submit);

        return jp5;
    }
    JPanel getJp9(){
        if(jp9 == null){
            jp9 = new JPanel();
        }
        Color color = new Color(174,4,13);
        jp9.setBackground(color);
        Dimension dimSp = new Dimension(screenSize.width/4, screenSize.height * 2 / 3);
        jp9.setForeground(Color.gray);
        jp9.setPreferredSize(dimSp);
        return jp9;
    }
    JPanel getJp10(){
        if(jp10 == null){
            jp10 = new JPanel();
        }
        Color color = new Color(174,4,13);
        jp10.setBackground(color);
        Dimension dimSp = new Dimension(screenSize.width/4, screenSize.height * 2 / 3);
        jp10.setForeground(Color.gray);
        jp10.setPreferredSize(dimSp);
        return jp10;
    }
}
