package servlet;

import empDao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/empDel.do")
public class empDel extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String empId = req.getParameter("EMP_ID");
        try {
            daoImpl dao = new daoImpl();
            dao.empDelete(Integer.parseInt(empId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("../index.jsp");
    }
}
