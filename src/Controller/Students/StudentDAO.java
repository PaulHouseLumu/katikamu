/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Students;

import DButils.DButil;
import Models.Students.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.sql.*;
import java.io.*;

/**
 *
 * @author root
 */



public class StudentDAO {
    
    private Connection myConn;
    
    public StudentDAO() throws Exception {

                
                // Connect to database
                myConn = new DButil().getConnection();
                
                System.out.println("DB Connection successful");
        
    }
    
     public void addStudent(Student theStudent, int[] subjectsTaken) throws Exception {
            
            
            PreparedStatement myStmt1 = null;
            PreparedStatement myStmt2 = null;
            try{
                // Prepare Statement
                //int lastID;
                //lastID = myConn.("select * from students");
                
                myStmt1 = myConn.prepareStatement("insert into Students (registration_number, first_name, last_name, gender, age, class_id,password) values(?,?,?,?,?,?,SHA(?))", Statement.RETURN_GENERATED_KEYS);
                myStmt2 = myConn.prepareStatement("insert into Results (student_id,subject_id) values(?,?)");
                
                // Set Parameters
                System.out.println(theStudent.getRegistrationNumber());
                System.out.println(theStudent.getFirstName());
                int class_id=0;
                switch (theStudent.getStudentClass()) {
                    case "P.1":
                        class_id = 1;
                        break;
                    case "P.2":
                        class_id = 2;
                    case "P.3":
                        class_id = 3;
                        break;
                    case "P.4":
                        class_id = 4;
                        break;
                    case "P.5":
                        class_id = 5;
                        break;
                    case "P.6":
                        class_id = 6;
                        break;
                    case "P.7":
                        class_id = 7;
                        break;
                }
                //myStmt.setInt(1,3);
                myStmt1.setString(1, theStudent.getRegistrationNumber());
                myStmt1.setString(2, theStudent.getFirstName());
                myStmt1.setString(3, theStudent.getLastName());
                myStmt1.setString(4, theStudent.getGender());
                myStmt1.setInt(5, theStudent.getAge());
                myStmt1.setInt(6, class_id);
                myStmt1.setString(7,theStudent.getRegistrationNumber()+ theStudent.getAge());

                
                
                //Execute SQL
                myStmt1.executeUpdate();
                
                // Getting last the just inserted student ID to be used in storing subjects
                // taken by the student in tbl_Results
                ResultSet rs = myStmt1.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    
                    int index = 0;
                    while(index < subjectsTaken.length)
                    {
                        myStmt2.setInt(1, id);
                        myStmt2.setInt(2, subjectsTaken[index]);
                        
                        // Execute SQL
                        myStmt2.executeUpdate();
                        index++;
                    }
                }
                
            }
            finally {
            myStmt1.close();
            myStmt2.close();
        } 
        }
	
	public List<Student> getAllStudents() throws Exception {
		List<Student> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from Students");
			
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
			myStmt = myConn.prepareStatement("select * from Students where registration_number like ?");
			
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
        
         public List<Student> filterStudents(String subject) throws Exception {
		List<Student> list = new ArrayList<>();

		PreparedStatement myStmt1 = null;
		ResultSet myRs1 = null;

		try{
                    int id;
                    switch (subject){
                        case "English":
                            id = 1;
                            break;
                        case "SST":
                            id = 2;
                            break;
                        case "Maths":
                            id = 3;
                            break;
                        case "Science":
                            id = 4;
                            break;
                        case "No English":
                            id = 5;
                            break;
                        case "No SST":
                            id = 6;
                            break;
                        case "No Maths":
                            id = 7;
                            break;
                        case "No Science":
                            id = 8;
                            break;
                        default:
                            id=0;
                    }
                    
                     //myStmt1 = myConn.prepareStatement("SELECT students.registration_number, students.first_name, students.last_name, students.gender, students.age, students.student_class FROM students WHERE students.id ='"+id+"'");
                    
                    myStmt1 = myConn.prepareStatement("SELECT Students.registration_number, Students.first_name, Students.last_name, Students.gender, Students.age, Students.class_id "
                            + "FROM Students INNER JOIN Results ON Results.student_id = Students.id WHERE Results.subject_id ='"+id+"'");
			
                   // myStmt1.setString(1, subject);

                    myRs1 = myStmt1.executeQuery();

                    while (myRs1.next()) {
                                Student tempStudent = convertRowToStudent(myRs1);
				list.add(tempStudent);
			}
			
			return list;
		}
		finally {
			close(myStmt1, myRs1);
		}
	}
	
	private Student convertRowToStudent(ResultSet myRs) throws SQLException {
		
		String registrationNumber = myRs.getString("registration_number");
                String firstName = myRs.getString("first_name");
		String lastName = myRs.getString("last_name");
                String gender = myRs.getString("gender");
		int age = myRs.getInt("age");
                //String subject = myRs.getString("subject");
        String class_id=null;
        switch (myRs.getInt("class_id")) {
            case 1:
                class_id = "P.1";
                break;
            case 2:
                class_id = "P.2";
            case 3:
                class_id = "P.3";
                break;
            case 4:
                class_id = "P.4";
                break;
            case 5:
                class_id = "P.5";
                break;
            case 6:
                class_id = "P.6";
                break;
            case 7:
                class_id = "P.7";
                break;
        }
                String studentClass =class_id ;
                //int mark = myRs.getInt("mark");
                
               
		
		Student tempStudent = new Student(registrationNumber, firstName, lastName, gender, age, studentClass);
		
		return tempStudent;
	}
        
        
        private Student convertRowToStudent1(ResultSet myRs) throws SQLException {
		
		String registrationNumber = myRs.getString("registration_number");
                String firstName = myRs.getString("first_name");
		String lastName = myRs.getString("last_name");
                String gender = myRs.getString("gender");
		int age = myRs.getInt("age");
                String subject = myRs.getString("subject");
            String class_id=null;
            switch (myRs.getInt("class_id")) {
                case 1:
                    class_id = "P.1";
                    break;
                case 2:
                    class_id = "P.2";
                case 3:
                    class_id = "P.3";
                    break;
                case 4:
                    class_id = "P.4";
                    break;
                case 5:
                    class_id = "P.5";
                    break;
                case 6:
                    class_id = "P.6";
                    break;
                case 7:
                    class_id = "P.7";
                    break;
            }
            String studentClass =class_id ;
                //int mark = myRs.getInt("mark");
                
               
		
		Student tempStudent = new Student(registrationNumber, firstName, lastName, gender, age, studentClass, subject);
		
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

		System.out.println(dao.getAllStudents());
	}

    

   
}


