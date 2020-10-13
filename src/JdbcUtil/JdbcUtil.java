package JdbcUtil;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {
    static Properties p = new Properties();
    static InputStream is = JdbcUtil.class.getResourceAsStream("/db.properties");
    public static String driver = "";
    public static String URL = "";
    public static String USER = "";
    public static String PASSWORD = "";

    static {
        try {
            p.load(is);
            driver = p.getProperty("driver");
            URL = p.getProperty("URL");
            USER = p.getProperty("USER");
            PASSWORD = p.getProperty("PASSWORD");
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static PreparedStatement getPreparedStatement(String sql, Connection conn) throws SQLException {
        return conn.prepareStatement(sql);
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);

    }

    public static Statement getStatement(Connection conn) throws SQLException {
        return conn.createStatement();
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void empUpdate(String sql, Object... params) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement ps = JdbcUtil.getPreparedStatement(sql, conn);
        for (int i = 0; i < params.length; ++i) {
            ps.setObject(i + 1, params[i]);
        }
        ps.executeUpdate();
        close(null, ps, conn);
    }

    public ResultSet empQuery(String sql, Object... params) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement ps = JdbcUtil.getPreparedStatement(sql, conn);
        for (int i = 0; i < params.length; ++i) {
            ps.setObject(i + 1, params[i]);
        }
        return ps.executeQuery();
    }
}
