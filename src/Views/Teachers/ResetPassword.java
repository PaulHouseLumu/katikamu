package Views.Teachers;

import Controller.Teachers.TeacherHomeController;
import Views.Template;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ResetPassword implements ActionListener {
    Template frame=new Template("Reset Your Password");
    JButton back = new JButton("Back");
    JLabel heading = new JLabel("Reset Your Password");
    JLabel passwordLabel= new JLabel("New Password :");
    JPasswordField password = new JPasswordField();
    JButton sub = new JButton("Submit");
    public ResetPassword(){

        heading.setBounds(170,25,500,30);
        heading.setForeground(Color.blue);
        back.setBounds(10,10,80,20);
        back.addActionListener(this);
        frame.add(back);
        heading.setFont(new Font(null,Font.BOLD,25));
        frame.add(heading);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordLabel.setBounds(100,90,300, 25);
        frame.add(passwordLabel);
        password.setFont(new Font("Arial", Font.PLAIN, 15));
        password.setBounds(250,90,300, 25);
        frame.add(password);
        sub.setFont(new Font("Arial", Font.PLAIN, 20));
        sub.setBounds(150,150,300, 20);
        sub.addActionListener(this);
        frame.add(sub);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(620,420);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            frame.dispose();
            new TeacherDashView();
        }
        if (e.getSource() == sub) {
            String pass = String.valueOf(password.getPassword());
            try {
                if(new TeacherHomeController().resetPassword(pass)){
                    password.setText("");
                    JOptionPane.showMessageDialog(null, "Updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Something Went wrong");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

}
