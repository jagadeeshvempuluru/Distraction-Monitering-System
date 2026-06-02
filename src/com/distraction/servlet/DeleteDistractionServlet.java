package com.distraction.servlet;

import java.io.IOException;

import com.distraction.dao.DistractionDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteDistraction")
public class DeleteDistractionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {

        	int did = Integer.parseInt(
        	        request.getParameter("did"));

        	DistractionDao dao =
        	        new DistractionDao();

        	dao.deleteDistraction(did);

        	response.sendRedirect("dashboard");
        } catch(Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                "<h2>Error while deleting distraction</h2>"
            );
        }
    }
}