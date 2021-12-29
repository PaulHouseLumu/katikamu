package Views.Students;

import Views.Template;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Student {
    private JButton viewResults;
    private JButton viewTimetable;
    private JPanel jPanel;

    public Student() {
        viewResults.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Hello World1");
            }
        });
        viewTimetable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Hello World2");
            }
        });
    }

    public static void main(String[] args) {
//        Template frame=new Template("Student Dashboard");
        JFrame frame = new JFrame();

        frame.setContentPane(new Student().jPanel);
    }
}
