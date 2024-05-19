package Dao;

import java.sql.*;

public class StudentActionDao {
    public class StudentDao {
        private static final String uri = "jdbc:mysql://localhost:3306/stu1db?useSSL=true";
        private static final String user = "root";
        private static final String password = "MSH12345_";

        public static void main(String[] args) throws ClassNotFoundException {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("正常加载驱动");
                Connection con = getConnection();
                System.out.println("链接数据库成功。");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(uri, user, password);
        }


    }

    public String loginDao(String username, String password) {
        String sql_login = "select * from class1 where idstudent=? and password=?";
        try {
            Connection conn = StudentDao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql_login);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return "success";
            } else {
                return "failure";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*public void addDao(int id, String username, String password) {

        String sql_add = "INSERT INTO class1(idstudent,name,password) VALUES(?,?,?)";
        try {
            Connection conn = StudentDao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql_add);
            stmt.setInt(1, id);
            stmt.setString(2, username);
            stmt.setString(3, password);
            stmt.executeUpdate();
            int rowAdd = stmt.executeUpdate();
            if (rowAdd > 0) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    public void deleteDao(int id) {

        String sql_delete = "delete from class1 where idstudent=?";
        try {
            Connection conn = StudentDao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql_delete);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            int rowDelete = stmt.executeUpdate();
            if (rowDelete > 0) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateDao(String id, String password) {

        String sql_update = "UPDATE class1 SET password = ? WHERE idstudent = ?";
        try {
            Connection conn = StudentDao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql_update);
            stmt.setString(1, password);
            stmt.setString(2,id);
            int rowUpdate = stmt.executeUpdate();
            if (rowUpdate > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
