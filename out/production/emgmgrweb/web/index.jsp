<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*,java.util.ResourceBundle" %>
<html>
<head>
    <title>员工信息管理系统</title>
    <link rel="stylesheet" href="main.css">
</head>
<body>
<%
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
//    ResourceBundle res = ResourceBundle.getBundle("db");
//    System.out.println(res.getString("URL"));
//    final String URL = res.getString("URL");
//    final String USER = res.getString("USER");
//    final String PASSWORD = res.getString("PASSWORD");
    final String URL = "jdbc:mysql://localhost:3306/empmgr?useUnicode=ture&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
    final String USER = "root";
    final String PASSWORD = "@Xop0wo!";


//    Class.forName(res.getString("driver"));
    Class.forName("com.mysql.cj.jdbc.Driver");

    con = DriverManager.getConnection(URL, USER, PASSWORD);
    stmt = con.createStatement();

    request.setCharacterEncoding("utf-8");
    String order = "";
    String filter = "";
    String SQL = "SELECT * FROM employee,department where EMP_DEPARTMENT=DEPART_ID AND (? IS NULL OR EMP_ID=? OR EMP_NAME LIKE ? OR DEPART_NAME=?) ORDER BY ";

%>
<header>
    <h1>员工信息管理系统</h1>
    <h3>Employee Information Management System</h3>
</header>


<table class="ev">
    <div class="find-con">
        <input type="text" id="filinp" name="filter"
               value="<%=(request.getParameter("filter")==null?"": request.getParameter("filter"))%>"
               class="filler" placeholder="使用关键字查找">
        <input type="button" value="查找"
               onclick="para('<%=request.getParameter("order")%>',document.getElementById('filinp').value) "
               class="find-btn"
               id="findBtn">
    </div>

    <thead>
    <tr no="">
        <td><a onclick="para('EMP_ID','<%=request.getParameter("filter")%>')"
               class="<%=(request.getParameter("order")!=null&&request.getParameter("order").equals("EMP_ID"))?"order":""%>">员工编号</a>
        </td>
        <td><a onclick="para('EMP_NAME','<%=request.getParameter("filter")%>')"
               class="<%=(request.getParameter("order")!=null&&request.getParameter("order").equals("EMP_NAME"))?"order":""%>">姓名</a>
        </td>
        <td><a onclick="para('EMP_DEPARTMENT','<%=request.getParameter("filter")%>')"
               class="<%=(request.getParameter("order")!=null&&request.getParameter("order").equals("EMP_DEPARTMENT"))?"order":""%>">部门</a>
        </td>
        <td><a onclick="para('EMP_AGE','<%=request.getParameter("filter")%>')"
               class="<%=(request.getParameter("order")!=null&&request.getParameter("order").equals("EMP_AGE"))?"order":""%>">年龄</a>
        </td>
        <td><a onclick="para('EMP_SEX','<%=request.getParameter("filter")%>')"
               class="<%=(request.getParameter("order")!=null&&request.getParameter("order").equals("EMP_SEX"))?"order":""%>">性别</a>
        </td>
        <td>操作</td>
    </tr>
    </thead>
    <tr no="ADD">
        <form name="add" id="add" action="servlet/empAdd.do" method="post">
            <td>
                <input name="add_id" type="number" id="add_id" placeholder="编号" required>
            </td>
            <td>
                <input name="add_name" type="text" id="add_name" placeholder="姓名" required>
            </td>
            <td>
                <select name="add_dep" id="add_dep" required>
                    <%
                        rs = stmt.executeQuery("SELECT * FROM department");
                        while (rs.next()) {
                    %>
                    <option value="<%=rs.getString("DEPART_ID")%>"><%=rs.getString("DEPART_NAME")%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </td>
            <td>
                <input name="add_age" type="number" id="add_age" placeholder="年龄" required>
            </td>
            <td>
                <select name="add_sex" id="add_sex" required>
                    <option value="1">男</option>
                    <option value="2">女</option>
                </select>
            </td>
            <td>
                <button type="submit">
                    添加/编辑
                </button>
            </td>
        </form>
    </tr>
    <%
        order = request.getParameter("order");
        filter = request.getParameter("filter");
        if (order == null || order.equals("")) order = " EMP_ID ASC";
        PreparedStatement ps = con.prepareStatement(SQL);
        ps.setString(1, filter);
        ps.setString(2, filter);
        ps.setString(3, "%" + filter + "%");
        ps.setString(4, filter);
        String sql = ps.toString().substring(43) + order + ";";
        rs = ps.executeQuery(sql);
        while (rs.next()) {
    %>
    <tr no="<%=rs.getString("EMP_ID") %>">
        <td><%=rs.getString("EMP_ID") %>
        </td>
        <td><%=rs.getString("EMP_NAME") %>
        </td>
        <td><%=rs.getString("DEPART_NAME") %>
        </td>
        <td><%=rs.getString("EMP_AGE") %>
        </td>
        <td><%=(rs.getString("EMP_SEX").equals("1") ? "男" : "女") %>
        </td>
        <td>
            <button class="del" type="submit"
                    onclick="confirm(this,<%=rs.getString("EMP_ID") %>)">
                删除
            </button>
        </td>
    </tr>
    <% } %>


</table>
<footer>
    <p>
        Copyright © 2020 BladeHiker All Rights Reserved.<br>
        Design by BladeHiker.
        Powered by JavaEE.
    </p>
</footer>
</body>
</html>
<script>
    function confirm(id, EMP_ID) {
        id.innerText = "确认删除?"
        console.log('servlet/empDel.do?EMP_ID=' + EMP_ID)
        id.onclick = () => {
            window.location.href = 'servlet/empDel.do?EMP_ID=' + EMP_ID
        }
        id.onmouseleave = () => {
            id.innerText = "删除"
            id.onclick = () => {
                confirm(id, EMP_ID)
            }
        }
    }

    document.onkeydown = (e) => {
        var curKey = e.which;
        if (curKey === 13) {
            document.getElementById("findBtn").click()
        }
    }

    function para(order, filter) {
        if (filter.toString() === "null")
            window.location.href = '?order=' + order
        else if (order.toString() === "null")
            window.location.href = '?filter=' + filter
        else
            window.location.href = '?filter=' + filter + '&order=' + order
    }
</script>