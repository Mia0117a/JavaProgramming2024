package Base;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.sql.*;

public class FileAction {
    private static final String FILE_PATH = "D:\\JavaWork\\teacher_scores.xls";
    private static final String[] COLUMN_NAMES = {"id", "姓名", "密码", "学院", "课程", "总成绩", "平均成绩"};
    private static final String SQL_QUERY = "SELECT * FROM teacher";

    public void fileAction_xls() {
        try (Connection conn = TeacherDao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_QUERY);
             ResultSet rs = stmt.executeQuery()) {

            HSSFWorkbook book = new HSSFWorkbook();
            HSSFSheet sheet = book.createSheet("教师评教成绩表");

            // Create header row
            HSSFRow rowHead = sheet.createRow(0);
            for (int i = 0; i < COLUMN_NAMES.length; i++) {
                HSSFCell cell = rowHead.createCell(i);
                cell.setCellValue(COLUMN_NAMES[i]);
            }

            int rowIndex = 1;
            while (rs.next()) {
                HSSFRow rowBody = sheet.createRow(rowIndex++);
                for (int i = 0; i < COLUMN_NAMES.length; i++) {
                    HSSFCell cell = rowBody.createCell(i);
                    cell.setCellValue(rs.getString(i + 1));
                }
            }

            try (FileOutputStream outputStream = new FileOutputStream(FILE_PATH)) {
                book.write(outputStream);
                System.out.println("文件写入成功");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static class TeacherDao {
        private static final String URI = "jdbc:mysql://localhost:3306/teadb?useSSL=true";
        private static final String USER = "root";
        private static final String PASSWORD = "MSH12345_";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URI, USER, PASSWORD);
        }
    }
}
