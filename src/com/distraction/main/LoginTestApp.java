package com.distraction.main;

//import com.distraction.util.DBConnection;
import com.distraction.dao.StudentDao;
import java.util.Scanner;

public class LoginTestApp {

	public static void main(String[] args) {
		//DBConnection.getConnection();
		
		Scanner sc = new Scanner(System.in);
        StudentDao dao = new StudentDao();

        System.out.println("Enter Email:");
        String email = sc.next();

        System.out.println("Enter Password:");
        String password = sc.next();

        int studentId = dao.loginStudent(email, password);

        if (studentId != -1) {
            System.out.println("Login Successful!");
            System.out.println("Your Student ID is: " + studentId);
        } else {
            System.out.println("Invalid Email or Password");
        }

        sc.close();
    }
}

		
	


