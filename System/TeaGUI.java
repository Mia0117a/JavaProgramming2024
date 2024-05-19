package System;

import Dao.TeacherActionDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TeaGUI extends JFrame {
    private JTextField idField;
    private JButton searchButton;

    public TeaGUI() {
        setTitle("教师成绩查询系统");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel idLabel = new JLabel("请输入职工号：");
        idField = new JTextField(10);
        searchButton = new JButton("查询");

        mainPanel.add(idLabel, gbc);
        mainPanel.add(idField, gbc);
        mainPanel.add(searchButton, gbc);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                // Call method to search for scores using the provided employee ID
                try {
                    searchScores(id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        add(mainPanel);
        setVisible(true);
    }

    private void searchScores(String id) throws SQLException {
        // Placeholder method for searching scores using the provided employee ID
        // You can implement your database query logic here
        // For demonstration, let's just display a message dialog with the entered ID
        TeacherActionDao teacherActionDao = new TeacherActionDao();
        String scores = teacherActionDao.queryDao(id);
        JOptionPane.showMessageDialog(this, "您输入的职工号是：" + id);
        JOptionPane.showMessageDialog(this, "您的成绩是：" + scores);
        // 这里可以添加跳转到成绩查询结果的逻辑

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TeaGUI();
            }
        });
    }
}
