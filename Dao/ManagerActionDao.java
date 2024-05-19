package Dao;

import java.sql.*;

public class ManagerActionDao {
    public class ManagerDao {
        private static final String uri = "jdbc:mysql://localhost:3306/managedb?useSSL=true";
        private static final String user = "root";
        private static final String password ="MSH12345_";

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
    public String loginDao(String id, String password){
        String sql_login = "select * from manage where id=? and password=?";
        try {
            Connection conn = ManagerActionDao.ManagerDao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql_login);
            stmt.setString(1,id);
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
    public void addDao(int id, String name, String password){
        String sql_add = "INSERT INTO manage(id,name,password) VALUES(?,?,?)";
        try {
            Connection conn =ManagerActionDao.ManagerDao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql_add);
            stmt.setInt(1, id);
            stmt.setString(2, name);
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
    }
    public void deleteDao(int id){
        String sql_delete = "DELETE FROM manage WHERE id=?";
        try {
            Connection conn =ManagerActionDao.ManagerDao.getConnection();
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
    public void updateDao(int id, String password){
        String sql_update = "UPDATE manage SET password=? WHERE id=?";
        try {
            Connection conn =ManagerActionDao.ManagerDao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql_update);
            stmt.setInt(1, id);
            stmt.setString(3, password);
            stmt.executeUpdate();
            int rowUpdate = stmt.executeUpdate();
            if (rowUpdate > 0) {
                System.out.println("更新成功");
            }else {
                System.out.println("更新失败");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
