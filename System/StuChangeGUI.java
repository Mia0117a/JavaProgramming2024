package System;

import Dao.StudentActionDao;
import Dao.TeacherActionDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StuChangeGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JButton changeButton;

    public StuChangeGUI() {
        setTitle("修改密码");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel usernameLabel = new JLabel("用户名:");
        mainPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(10);
        mainPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel oldPasswordLabel = new JLabel("旧密码:");
        mainPanel.add(oldPasswordLabel, gbc);

        gbc.gridx = 1;
        oldPasswordField = new JPasswordField(10);
        mainPanel.add(oldPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel newPasswordLabel = new JLabel("新密码:");
        mainPanel.add(newPasswordLabel, gbc);

        gbc.gridx = 1;
        newPasswordField = new JPasswordField(10);
        mainPanel.add(newPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        changeButton = new JButton("修改密码");
        mainPanel.add(changeButton, gbc);

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] oldPasswordChars = oldPasswordField.getPassword();
                char[] newPasswordChars = newPasswordField.getPassword();

                // Convert char arrays to strings
                String oldPassword = new String(oldPasswordChars);
                String newPassword = new String(newPasswordChars);

                // You can implement your password change logic here
                StudentActionDao studentActionDao = new StudentActionDao();
                boolean res=studentActionDao.updateDao(username, newPassword);

                if(res){
                    String message = "用户名: " + username + "\n旧密码: " + oldPassword + "\n新密码: " + newPassword;
                    JOptionPane.showMessageDialog(StuChangeGUI.this, message, "密码修改成功", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                    new StuLogin();
                }else {

                    JOptionPane.showMessageDialog(StuChangeGUI.this, "密码修改失败", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StuChangeGUI();
            }
        });
    }
}
