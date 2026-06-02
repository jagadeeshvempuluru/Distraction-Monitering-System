package com.distraction.main;

import java.util.Scanner;

import com.distraction.dao.StudentDao;
import com.distraction.dao.DistractionDao;
import com.distraction.model.Student;
import com.distraction.model.Distraction;

public class MainMenuApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentDao studentDao = new StudentDao();
        DistractionDao distractionDao = new DistractionDao();

        int loggedInStudentId = -1;
        boolean running = true;

        while (running) {

            System.out.println("\n==== DISTRACTION MONITORING SYSTEM ====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Add Distraction");
            System.out.println("4. Today Distraction Report");
            System.out.println("5. Last 7 Days Distraction Report");
            System.out.println("6. Exit");
            System.out.print("Select your option: ");

            int choice = sc.nextInt();

            switch (choice) {

                // 1️⃣ REGISTER
                case 1:
                    Student s = new Student();
                    System.out.print("Enter your name: ");
                    s.setName(sc.next());
                    System.out.print("Enter your Email: ");
                    s.setEmail(sc.next());
                    System.out.print("Enter your Password: ");
                    s.setPassword(sc.next());

                    if (studentDao.registerStudent(s)) {
                        System.out.println("Registration Success 👍");
                    } else {
                        System.out.println("Registration Failed ❌");
                    }
                    break;

                // 2️⃣ LOGIN
                case 2:
                    System.out.print("Enter your Email: ");
                    String email = sc.next();
                    System.out.print("Enter your password: ");
                    String pass = sc.next();

                    loggedInStudentId = studentDao.loginStudent(email, pass);

                    if (loggedInStudentId != -1) {
                        System.out.println("Login Success 👍");
                    } else {
                        System.out.println("Invalid Login ❌");
                    }
                    break;

                // 3️⃣ ADD DISTRACTION
                case 3:
                    if (loggedInStudentId == -1) {
                        System.out.println("ముందుగా Login అవ్వాలి ❗");
                        break;
                    }

                    Distraction d = new Distraction();
                    d.setStudentId(loggedInStudentId);

                    System.out.print("Type Your Distraction: ");
                    d.setType(sc.next());

                    System.out.print("Give your Distraction Minutes: ");
                    d.setMinutes(sc.nextInt());

                    if (distractionDao.addDistraction(d)) {
                        System.out.println("Distraction Added 👍");
                    } else {
                        System.out.println("Failed ❌");
                    }
                    break;

                // 4️⃣ TODAY REPORT
                case 4:
                    if (loggedInStudentId == -1) {
                        System.out.println("ముందుగా Login అవ్వాలి ❗");
                        break;
                    }

                    int today = distractionDao.getTodayDistraction(loggedInStudentId);
                    System.out.println("Today's Distraction Time: " + today + " minutes");
                    break;

                // 5️⃣ LAST 7 DAYS REPORT
                case 5:
                    if (loggedInStudentId == -1) {
                        System.out.println("ముందుగా Login అవ్వాలి ❗");
                        break;
                    }

                    int week = distractionDao.getLast7DaysDistraction(loggedInStudentId);
                    System.out.println("Last 7 Days Distraction Time: " + week + " minutes");
                    break;

                // 6️⃣ EXIT
                case 6:
                    running = false;
                    System.out.println("Thank you! Program Closed 🙏");
                    break;

                default:
                    System.out.println("Wrong Option ❌");
            }
        }

        sc.close();
    }
}
