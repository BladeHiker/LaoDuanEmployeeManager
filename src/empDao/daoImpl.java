package empDao;

import JdbcUtil.JdbcUtil;
import POJO.Employee;

import java.sql.*;

public class daoImpl {
    public void empCreate(Employee employee) throws Exception {
        JdbcUtil jdbcUtil = new JdbcUtil();
        String sql = "INSERT INTO employee (EMP_ID, EMP_NAME, EMP_DEPARTMENT, EMP_AGE, EMP_SEX) VALUES (?,?,?,?,?);";
        jdbcUtil.empUpdate(sql, employee.getEmpId(), employee.getEmpName(), employee.getEmpDepartment(), employee.getEmpAge(), employee.getEmpSex());
    }

    public void empUpdate(Employee employee) throws Exception {
        JdbcUtil jdbcUtil = new JdbcUtil();
        String sql = "UPDATE employee SET EMP_NAME=?,EMP_SEX=?,EMP_AGE=?,EMP_DEPARTMENT=? WHERE EMP_ID=?;";
        jdbcUtil.empUpdate(sql, employee.getEmpName(), employee.getEmpSex(), employee.getEmpAge(), employee.getEmpDepartment(), employee.getEmpId());
    }

    public Employee empRetrieve(Long Id) throws Exception {
        JdbcUtil jdbcUtil = new JdbcUtil();
        String sql = "SELECT * FROM employee WHERE EMP_ID=?;";
        ResultSet rs = jdbcUtil.empQuery(sql, Id);
        Employee T = new Employee();
        T.setEmpAge(rs.getInt("EMP_AGE"));
        T.setEmpBirth(rs.getDate("EMP_BIRTH"));
        T.setEmpDepartment(rs.getInt("EMP_DEPARTMENT"));
        T.setEmpId(rs.getInt("EMP_ID"));
        T.setEmpName(rs.getString("EMP_NAME"));
        return T;
    }

    public void empDelete(int empId) throws Exception {
        JdbcUtil jdbcUtil = new JdbcUtil();
        String sql = "DELETE FROM employee where EMP_ID=?;";
        jdbcUtil.empUpdate(sql, empId);
    }
}
