package Views.Students;

import Controller.Students.StudentHomeController;
import Views.Teachers.StudentSearch;
import Views.Teachers.TeacherDashView;
import Views.Teachers.TeacherLoginView;
import Views.Template;
import Views.WelcomeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentLoginView implements ActionListener {
    Template frame = new Template("Student Login");
    JButton loginBtn =new JButton("Login");
    JButton back = new JButton("Back");

    JTextField usernameField =new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JLabel usernameLabel=new JLabel("Registration Number :");
    JLabel passwordLabel= new JLabel("Password :");
    JLabel heading = new JLabel("Students' Login");
    public StudentLoginView(){
        heading.setBounds(170,25,300,30);
        heading.setForeground(Color.blue);
        back.setBounds(10,10,80,20);
        back.addActionListener(this);
        frame.add(back);
        heading.setFont(new Font(null,Font.BOLD,25));
       usernameLabel.setBounds(150,100,140,30);
       passwordLabel.setBounds(150,150,140,30);
       usernameField.setBounds(300,100,200,25);
       passwordField.setBounds(300,150,200,25);
       loginBtn.setBounds(180,200,200,25);
       loginBtn.setFocusable(false);
       loginBtn.addActionListener(this);
       frame.add(heading);
       frame.add(usernameLabel);
       frame.add(passwordLabel);
       frame.add(usernameField);
       frame.add(passwordField);
       frame.add(loginBtn);
       frame.setLayout(null);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setResizable(false);
       frame.setSize(620,420);
       frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            frame.dispose();
            new WelcomeView();
        }
        if(e.getSource()==loginBtn){
            String username=usernameField.getText();
            String password=String.valueOf(passwordField.getPassword());
            StudentHomeController studentDAO=new StudentHomeController();
            if(studentDAO.loginUser(username,password)){
                frame.dispose();
                StudentDashView frame = new StudentDashView();

            }else{
                JOptionPane.showMessageDialog(null,"failed! wrong username or password");
            }
        }

    }
}
