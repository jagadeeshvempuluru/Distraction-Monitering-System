package com.distraction.servlet;

import java.io.IOException;

import com.distraction.dao.StudentDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("============== LOGIN DEBUG ==============");
        System.out.println("Email = " + email);
        System.out.println("Password = " + password);

        StudentDao dao = new StudentDao();
        int studentId = dao.loginStudent(email, password);

        System.out.println("Student ID = " + studentId);

        if (studentId != -1) {

            System.out.println("LOGIN SUCCESS");

            HttpSession session = request.getSession();
            session.setAttribute("studentId", studentId);

            String dashboardUrl = request.getContextPath() + "/dashboard";
            System.out.println("Redirecting To = " + dashboardUrl);

            response.sendRedirect(dashboardUrl);

        } else {

            System.out.println("LOGIN FAILED");

            request.setAttribute("error",
                    "Invalid Email or Password");

            request.getRequestDispatcher("login.jsp")
                   .forward(request, response);
        }

        System.out.println("=========================================");
    }
}