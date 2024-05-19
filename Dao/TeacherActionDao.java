package Dao;
import javax.swing.*;
import java.sql.*;

public class TeacherActionDao {
    public class TeacherDao {
        private static final String uri = "jdbc:mysql://localhost:3306/teadb?useSSL=true";
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
        String sql_login = "select * from teacher where idteacher=? and password=?";
        try {
            Connection conn = TeacherDao.getConnection();
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

    public void addDao(String id, String name, String password, String college, String course, int sumgrade, int average) throws SQLException {

        String sql_add = "INSERT INTO teacher(id,name,password,college,course,sumgrade,average) VALUES(?,?,?,?,?,?,?)";
        try {
            Connection conn = TeacherDao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql_add);
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, password);
            stmt.setString(4, college);
            stmt.setString(5, course);
            stmt.setInt(6, sumgrade);
            stmt.setInt(7, average);
            int rowAdd = stmt.executeUpdate();
            if (rowAdd > 0) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteDao(int id) throws SQLException {

        String sql_delete = "DELETE FROM teacher WHERE id=?";
        try {
            Connection conn = TeacherDao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql_delete);
            stmt.setInt(1, id);
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

    public void updateDao(String id, String name, String college, String course, String sumgrade) throws SQLException {

        String sql_update = "UPDATE teacher SET nameteacher=?,college=?,course=?,sumgrade=? WHERE idteacher=?";
        try {
            Connection conn = TeacherDao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql_update);
            stmt.setString(1, name);

            stmt.setString(2, college);
            stmt.setString(3, course);
            stmt.setString(4, sumgrade);
            stmt.setString(5, id);
            int rowUpdate = stmt.executeUpdate();
            if (rowUpdate > 0) {
                System.out.println("更新成功");
            } else {
                System.out.println("更新失败");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void changesumgradeDao(String id, String sumgrade) throws SQLException {

        String sql_changesumgrade = "UPDATE teacher SET sumgrade=? WHERE idteacher=?";
        try {
            Connection conn = TeacherDao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql_changesumgrade);
            stmt.setString(1, sumgrade);
            stmt.setString(2, id);
            int rowUpdate = stmt.executeUpdate();

            if (rowUpdate > 0) {
                JOptionPane.showMessageDialog(null, "评分成功");
            } else {
                System.out.println("更新失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String queryDao(String id) throws SQLException {

        String sql_query = "SELECT * FROM teacher WHERE idteacher=?";
        try {
            Connection conn = TeacherDao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String grade = rs.getString("sumgrade");
                return grade;
            } else {
                System.out.println("查询失败");
                return "失败";
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
