package servlet;

import POJO.Employee;
import empDao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/empAdd.do")
public class empAdd extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Employee employee = new Employee();
        employee.setEmpId(Integer.parseInt(req.getParameter("add_id")));
        employee.setEmpName(req.getParameter("add_name"));
        employee.setEmpAge(Integer.parseInt(req.getParameter("add_age")));
        employee.setEmpDepartment(Integer.parseInt(req.getParameter("add_dep")));
        employee.setEmpSex(Integer.parseInt(req.getParameter("add_sex")));
        try {
            daoImpl dao = new daoImpl();
            dao.empCreate(employee);
            resp.sendRedirect("../index.jsp");
        } catch (Exception e) {
            req.getRequestDispatcher("/servlet/empUpdate.do").forward(req, resp);
        }
    }
}
