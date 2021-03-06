/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package katikamuprimaryschool;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author root
 */
public class FilterStudents extends javax.swing.JFrame {

    
    
        private JPanel contentPane;
	private JTextField registrationNumberTextField;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTable table;
        private JButton btnAddStudent;
        private JButton btnFilterStudents;
        private JComboBox subjectComboBox;

	private StudentDAO studentDAO;
       
        
        /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FilterStudents frame = new FilterStudents();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
        
    /**
     * Creates new form StudentSearch
     */
    /**
     * Creates new form FilterStudents
     */
    public FilterStudents() {
        initComponents();
        
        
     // create the DAO
		try {
			studentDAO = new StudentDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}
		
		setTitle("Filter Students By Subject");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblSelectSubject = new JLabel("Select Subject:");
		panel.add(lblSelectSubject);
		String[] subjectsTaken = {
                    "ALL",
                    "English",
                    "SST",
                    "Maths",
                    "Science",
                    "No English",
                    "No SST",
                    "No Maths",
                    "No Science"
                };
		subjectComboBox = new JComboBox(subjectsTaken);
		panel.add(subjectComboBox);
		//subjectComboBox.setColumns(10);
		
                                                                                                                                                                btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get registration number from the text field

				// Call DAO and get students for the registration number

				// If last name is empty, then get all students

				// Print out students				
				
				try {
					String subject = (String)subjectComboBox.getSelectedItem();

					List<Student> students = null;

					if(subject == "ALL"){
						students = studentDAO.getAllStudents();
					}else if(subject != null && subject.trim().length() > 0) {
						students = studentDAO.filterStudents(subject);
                                                
					} else{
                                            
                                        }
					
					// create the model and update the "table"
					StudentTableModel model = new StudentTableModel(students);
					
					table.setModel(model);
					
					/*
					for (Student temp : students) {
						System.out.println(temp);
					}
					*/
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(FilterStudents.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		panel.add(btnSearch);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
                
                // Add Student Button
                
                JPanel panel_1 = new JPanel();
                contentPane.add(panel_1, BorderLayout.SOUTH);
                
                btnFilterStudents = new JButton("Filter Students");
                panel.add(btnFilterStudents);
                
                btnAddStudent = new JButton("Register Student");
                panel.add(btnAddStudent);
                
                btnAddStudent.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent event) {
                      //StudentSearch studentSearch = new StudentSearch();
                            // create Dialog
                            AddStudentDialog dialog = new AddStudentDialog(true);
                            
                            // show dialog
                            dialog.setVisible(true);    
                    } 
                });
                panel_1.add(btnFilterStudents);
                panel_1.add(btnAddStudent);  
    }
    
    public void refreshStudentsView() {
        try
        {
            List<Student> students = studentDAO.getAllStudents();
            
            // Create the model and update the table
            StudentTableModel  model = new StudentTableModel(students);
            
            table.setModel(model);
        }
        catch(Exception exc)
        {
            JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
                    JOptionPane.ERROR_MESSAGE);
                    
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify                     
    // End of variables declaration                   
}

