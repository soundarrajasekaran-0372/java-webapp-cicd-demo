package com.soundar.app;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Soundar DevOps Demo</title></head>");
        out.println("<body>");
        out.println("<h1>Hello from Soundar's Automated DevOps Pipeline Cloud Deployment!</h1>");
	out.println("<p>Version: 1.0</p>");
        out.println("<p>Application built using Maven and deployed to Tomcat.</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
