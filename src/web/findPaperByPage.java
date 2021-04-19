package web;

import domain.PageBean;
import domain.Paper;
import service.PaperService;
import service.impl.PaperServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/findPaperByPageServlet")
public class findPaperByPage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtain parameters from browser
        String currentPage = req.getParameter("currentPage"); // Current page
        String rows = req.getParameter("rows"); // Total number of papers each page
        String condition = req.getParameter("w");
        // Handle situations where parameters from browsers are null
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)) {
            rows = "5";
        }
        // Obtain pageBean object
        PaperService paperService = new PaperServiceImpl();
        PageBean<Paper> pb = paperService.findPaperByPage(currentPage, rows, condition);
        // Store pageBean object in session
        req.getSession().setAttribute("pb", pb);
        resp.sendRedirect(req.getContextPath() + "/library/index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
