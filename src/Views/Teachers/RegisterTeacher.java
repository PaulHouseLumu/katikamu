package Views.Teachers;

import Controller.Teachers.TeacherHomeController;
import Models.Teachers.Teacher;
import Models.Timetable.Timetable;
import Views.Template;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static Controller.Teachers.TeacherHomeController.addTimeTable;

public class RegisterTeacher implements ActionListener {
    Template frame=new Template("Add New Teacher");
    JButton back = new JButton("Back");

    JLabel heading = new JLabel("Add a New Teacher");
    JLabel nameLabel = new JLabel("Name :");
    JLabel emailLabel = new JLabel("Email:");
    JTextField name=  new JTextField();
    JTextField email =  new JTextField();
    JButton sub = new JButton("Submit");
    public  RegisterTeacher(){
        heading.setBounds(170,25,500,30);
        heading.setForeground(Color.blue);
        back.setBounds(10,10,80,20);
        back.addActionListener(this);
        frame.add(back);
        heading.setFont(new Font(null,Font.BOLD,25));
        frame.add(heading);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        nameLabel.setBounds(100,90,300, 25);
        frame.add(nameLabel);
        name.setFont(new Font("Arial", Font.PLAIN, 15));
        name.setBounds(200,90,300, 25);
        frame.add(name);

        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        emailLabel.setBounds(100,120,300, 25);
        frame.add(emailLabel);
        email.setFont(new Font("Arial", Font.PLAIN, 15));
        email.setBounds(200,120,300, 25);
        email.addActionListener(this);
        frame.add(email);

        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setBounds(150,170,300, 20);
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
            String emailValue = email.getText();
            String nameValue = name.getText();
            if (!emailValue.contains("@")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid email");
            }else if(name != null){
                Teacher tt=new Teacher();
                tt.setEmail(emailValue);
                tt.setName(nameValue);
                try {
                    if(TeacherHomeController.addNewTeacher(tt)){
                        email.setText("");
                        name.setText("");
                    JOptionPane.showMessageDialog(null, "Teacher added successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Something Went wrong");
                }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(null, "All fields are required");
            }
        }
    }

    public static void main(String[] args) {
        new RegisterTeacher();
    }
}
