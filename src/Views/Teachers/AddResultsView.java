package Views.Teachers;

import Controller.Teachers.TeacherHomeController;
import Views.Template;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Controller.Teachers.TeacherHomeController.retrieveStudents;
import static Controller.Teachers.TeacherHomeController.retrieveSubjects;

public class AddResultsView implements ActionListener {
    Template frame=new Template("Add new Results");
    JButton back = new JButton("Back");
    JLabel heading = new JLabel("Update Student Result Record");
    JComboBox<Object> student=  new JComboBox<>();
    JComboBox<Object> subject = new JComboBox<>();

    JLabel markLabel= new JLabel("Mark:");
    JTextField mark =  new JTextField();
    JLabel studentLabel;
    JLabel subjectLabel;
    JButton sub = new JButton("Submit");
    public AddResultsView(){
        heading.setBounds(170,25,500,30);
        heading.setForeground(Color.blue);
        heading.setFont(new Font(null,Font.BOLD,25));
        back.setBounds(10,10,80,20);
        back.addActionListener(this);
        frame.add(back);
        frame.add(heading);
        for(Object sub:retrieveSubjects()){
            subject.addItem(sub);
        }
        studentLabel = new JLabel("Student:");
        studentLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        studentLabel.setBounds(100,100,300, 25);
        frame.add(studentLabel);

        for(Object sub:retrieveStudents()){
            student.addItem(sub);
        }
        if(retrieveStudents().size()<1){
            JOptionPane.showMessageDialog(null, "No Student Available");
        }
        student.setFont(new Font("Arial", Font.PLAIN, 20));
        student.setBounds(200,100,300, 25);
        student.addActionListener(this);
        student.setRenderer(new ListRenderer());
        frame.add(student);

        subjectLabel = new JLabel("Subject:");
        subjectLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        subjectLabel.setBounds(100,150,300, 25);
        frame.add(subjectLabel);

        subject.setFont(new Font("Arial", Font.PLAIN, 15));
        subject.setBounds(200,150,300, 25);
        subject.addActionListener(this);
        subject.setRenderer(new ListRenderer());
        frame.add(subject);

        markLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        markLabel.setBounds(100,200,300, 20);
        frame.add(markLabel);

        mark.setFont(new Font("Arial", Font.PLAIN, 20));
        mark.setBounds(200,200,300, 20);
        frame.add(mark);


        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setBounds(150,250,300, 20);
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
        if(e.getSource()==back){
            frame.dispose();
            new TeacherDashView();
        }

        if(e.getSource()==sub){
            try {
                Object[] selected_subject = (Object[]) subject.getSelectedItem();
                Object[] selected_student = (Object[]) student.getSelectedItem();
                Float marks = Float.parseFloat(mark.getText());
                int subjectInd=0;
                if(selected_subject != null) {subjectInd= (Integer) selected_subject[0];}else {
                    JOptionPane.showMessageDialog(null, "The please select a Student");
                }
                int studentInd=0;
                if(selected_student != null) studentInd=(Integer)selected_student[0];else {
                    JOptionPane.showMessageDialog(null, "The please select a Subject");
                }
                 if(mark !=null && selected_student != null && selected_subject != null ) {
                     System.out.println(subjectInd);
                     System.out.println(studentInd);
                     if (new TeacherHomeController().recordStudentMarks(studentInd, marks, subjectInd)) {
                         mark.setText("");
                         JOptionPane.showMessageDialog(null, "Update was successful");
                     } else {
                         JOptionPane.showMessageDialog(null, "The selected Student doesn't offer the subject");
                     }
                 }else{
                     JOptionPane.showMessageDialog(null, "The student mark can't null");
                 }


            }catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"All Fields are required");
            }
        }
        if(e.getSource()==student){
            System.out.println(student.getSelectedItem());
        }
    }

}

/**
 * Tell the comboBox view what to show from the object list
 */
class ListRenderer extends JLabel implements ListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Object[] itemData = (Object[])value;
    if(itemData!=null) setText((String)itemData[1]);

        return this;
    }
}