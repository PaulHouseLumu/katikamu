/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.Students;

/**
 *
 * @author root
 */
public class Student {
    
        public String registrationNumber;
	public String firstName;
        public String lastName;
        public String gender;
        public int age;
        public String subject;
        public String studentClass;
        public int mark;
        public int ID;
//        public Student(String registrationNumber,String firstName, String lastName, String gender, int age, String subject, String studentClass, int mark)
//	{
//		super();
//		this.registrationNumber = registrationNumber;
//		this.firstName = firstName;
//                this.lastName = lastName;
//		this.gender = gender;
//                this.age = age;
//                this.subject = subject;
//                this.studentClass = studentClass;
//                this.mark = mark;
//                
//	}

        public Student(String registrationNumber, String firstName, String lastName, String gender, int age, String studentClass, String subject) {
                super();
                this.registrationNumber = registrationNumber;
                this.firstName = firstName;
                this.lastName = lastName;
		this.gender = gender;
                this.age = age;
                this.studentClass = studentClass;
                this.subject = subject;
        }

        public Student(String registrationNumber, String firstName, String lastName, String gender, int age, String studentClass) {
                super();
                this.registrationNumber = registrationNumber;
                this.firstName = firstName;
                this.lastName = lastName;
		this.gender = gender;
                this.age = age;
                this.studentClass = studentClass;
                
    }
        

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
        
        public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
        
        public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

         public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}
        
        public int getMark(){
            return mark;
        }
        
        public void setMark(int mark){
            this.mark = mark;
        }
        
        
        
	@Override
	public String toString() {
		return String
				.format("Student [registrationNumber=%s, firstName=%s, lastName=%s, gender=%s, age=%s, studentClass=%s]",
						registrationNumber, firstName, lastName, gender, age, studentClass);
	}
	
	
		
}

