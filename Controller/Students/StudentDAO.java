/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Students;

import DButils.DButil;
import Models.Students.Student;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */



public class StudentDAO {
    
    private Connection myConn;
    
    public StudentDAO() throws Exception {

                
                // Connect to database
                myConn = new DButil().getConnection();
                
                System.out.println("DB Connection successful ");
        
    }
    
     public void addStudent(Student theStudent) throws Exception {
            
            PreparedStatement myStmt = null;
            
            try{
                // Prepare Statement
                myStmt = myConn.prepareStatement("insert into employees (registration_number, first_name, last_name, gender, age, subject, student_class, mark) values(?,?,?,?,?,?,?,?)");
                
                // Set Parameters
                myStmt.setString(1, theStudent.getRegistrationNumber());
                myStmt.setString(2, theStudent.getFirstName());
                myStmt.setString(3, theStudent.getLastName());
                myStmt.setString(4, theStudent.getGender());
                myStmt.setInt(5, theStudent.getAge());
                myStmt.setString(6, theStudent.getSubject());
                myStmt.setString(7, theStudent.getStudentClass());
                myStmt.setInt(8, theStudent.getMark());
                
                //Execute SQL
                myStmt.executeUpdate();
                
            }
            finally {
            myStmt.close();
        } 
        }
	
	public List<Student> getAllStudents() throws Exception {
		List<Student> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from students");
			
			while (myRs.next()) {
				Student tempStudent = convertRowToStudent(myRs);
				list.add(tempStudent);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
        
        public List<Student> searchStudents(String registrationNumber) throws Exception {
		List<Student> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			registrationNumber += "%";
			myStmt = myConn.prepareStatement("select * from students where registration_number like ?");
			
			myStmt.setString(1, registrationNumber);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Student tempStudent = convertRowToStudent(myRs);
				list.add(tempStudent);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private Student convertRowToStudent(ResultSet myRs) throws SQLException {
		
		String registrationNumber = myRs.getString("registration_number");
		String firstName = myRs.getString("first_name");
		String lastName = myRs.getString("last_name");
		String gender = myRs.getString("gender");
		int age = myRs.getInt("age");
                String subject = myRs.getString("subject");
                String studentClass = myRs.getString("student_class");
                int mark = myRs.getInt("mark");
                
               
		
		Student tempStudent = new Student(registrationNumber, firstName, lastName, gender, age, subject, studentClass, mark);
		
		return tempStudent;
	}
        
        private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}

	public static void main(String[] args) throws Exception {
		
		StudentDAO dao = new StudentDAO();
		System.out.println(dao.searchStudents("thom"));

		System.out.println(dao.getAllStudents());
	}

   
}


