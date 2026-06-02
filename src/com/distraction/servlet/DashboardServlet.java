package com.distraction.servlet;

import java.io.IOException;
import java.util.List;

import com.distraction.dao.DistractionDao;
import com.distraction.model.Distraction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("studentId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int studentId = (int) session.getAttribute("studentId");

        DistractionDao dao = new DistractionDao();

        int total = dao.getTotalDistractionTime(studentId);
        int today = dao.getTodayDistraction(studentId);
        int last7 = dao.getLast7DaysDistraction(studentId);

        List<Distraction> history =
                dao.getAllDistractions(studentId);

        request.setAttribute("total", total);
        request.setAttribute("today", today);
        request.setAttribute("last7", last7);
        request.setAttribute("history", history);

        request.getRequestDispatcher("dashboard.jsp")
               .forward(request, response);
    }
}