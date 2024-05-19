package Dao;

import Base.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImp implements TeacehrDao {
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
    // 实现接口中的方法
    public List<Teacher> findAll() {
        List<Teacher> teachers = new ArrayList<>();
        String sql_All = "select * from teacher";
        try {
            Connection conn=TeacherDao.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sql_All);
            ResultSet rs=stmt.executeQuery();
            while (rs.next()){
                Teacher teacher=new Teacher();
                teacher.setIdteacher(rs.getString("idteacher"));
                teacher.setNameteacher(rs.getString("nameteacher"));
                teacher.setCollege(rs.getString("college"));
                teacher.setCourse (rs.getString("course"));
                teacher.setSumgrade(rs.getString("sumgrade"));
                teachers.add(teacher);
            }

        } catch (Exception e) {
            System.out.println("查询失败"+e);
        }
        return teachers;
    }
}


