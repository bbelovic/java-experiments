package net.bbelovic.javaexperiments.servlet4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/jspRedirect")
public class JspRedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var location = String.format("%s/%s", req.getContextPath(), "jsp/hello.jsp");
        resp.sendRedirect(location);
    }
}
