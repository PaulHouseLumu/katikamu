/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.Teachers;

import Models.Students.Student;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author root
 */
public class FilterStudentsBySubjectTableModel extends AbstractTableModel{
    
        private static final int REGISTRATION_NUMBER_COL = 0;
        private static final int FIRST_NAME_COL = 1;
        private static final int LAST_NAME_COL = 2;
	private static final int GENDER_COL = 3;
	private static final int AGE_COL = 4;
        private static final int STUDENT_CLASS_COL = 5;
        private static final int SUBJECT_COL = 6;
	

	private String[] columnNames = { "Registration Number", "First Name", "Last Name",
			"Gender", "Age", "Student Class", "Subject" };
	private List<Student> students;

	public FilterStudentsBySubjectTableModel(List<Student> theStudents) {
		students = theStudents;
	}
    @Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return students.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Student tempStudent = students.get(row);

		switch (col) {
		case REGISTRATION_NUMBER_COL:
			return tempStudent.getRegistrationNumber();
		case FIRST_NAME_COL:
			return tempStudent.getFirstName();
                case LAST_NAME_COL:
			return tempStudent.getLastName();
		case GENDER_COL:
			return tempStudent.getGender();
		case AGE_COL:
			return tempStudent.getAge();
                case STUDENT_CLASS_COL:
			return tempStudent.getStudentClass();
                case SUBJECT_COL:
			return tempStudent.getSubject();
		
		default:
			return tempStudent.getRegistrationNumber();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}

    


