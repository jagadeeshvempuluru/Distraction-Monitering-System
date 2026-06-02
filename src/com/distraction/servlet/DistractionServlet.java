package com.distraction.servlet;

import java.io.IOException;

import com.distraction.dao.DistractionDao;
import com.distraction.model.Distraction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/distraction")
public class DistractionServlet extends HttpServlet {

private static final long serialVersionUID = 1L;

@Override
protected void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws ServletException, IOException {

    System.out.println("===== DISTRACTION SERVLET HIT =====");

    try {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("studentId") == null) {

            System.out.println("Session not found");

            response.sendRedirect("login.jsp");
            return;
        }

        int studentId =
            (Integer) session.getAttribute("studentId");

        String type =
            request.getParameter("type");

        String minutesStr =
            request.getParameter("minutes");

        System.out.println("Student ID = " + studentId);
        System.out.println("Type = " + type);
        System.out.println("Minutes String = " + minutesStr);

        if (type == null || type.trim().isEmpty()) {

            System.out.println("Type is empty");

            response.sendRedirect("dashboard");
            return;
        }

        int minutes = Integer.parseInt(minutesStr);

        Distraction distraction = new Distraction();

        distraction.setStudentId(studentId);
        distraction.setType(type);
        distraction.setMinutes(minutes);

        DistractionDao dao = new DistractionDao();

        boolean saved =
            dao.addDistraction(distraction);

        System.out.println("Saved Result = " + saved);

        if (saved) {

            System.out.println("Redirecting to dashboard");

            response.sendRedirect("dashboard");

        } else {

            response.getWriter().println(
                "<h2>Failed to save distraction.</h2>"
            );
        }

    } catch (Exception e) {

        e.printStackTrace();

        response.getWriter().println(
            "<h2>Error occurred. Check Eclipse Console.</h2>"
        );
    }
}


}
