package System;

import Dao.StudentActionDao;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class StuLogin extends JFrame implements ActionListener {
    private JLabel loginText, usernameLabel, passwordLabel;
    private JTextField usernameField, passwordField;
    private JPanel northrPanel, inputPanel, usernamePanel, passwordPanel, southPanel;
    private JButton loginButton, registerButton;
    private String title;
    private JPanel panel1;

    public StuLogin() {
        this.setTitle("教室后台管理系统");
        this.setBounds(760, 340, 400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        // 标题面板
        this.usernamePanel = new JPanel();
        this.passwordPanel = new JPanel();
        this.northrPanel = new JPanel();
        this.inputPanel = new JPanel(new GridLayout(2, 1));
        this.loginText = new JLabel("登录");
        this.loginText.setFont(new Font("楷体", Font.BOLD, 30));
        this.usernameLabel = new JLabel("用户：");
        this.passwordLabel = new JLabel("密码：");

        // 用户名输入面板
        this.usernameField = new JTextField("请输入用户名", 12);
        this.usernamePanel.add(usernameLabel);
        this.usernamePanel.add(usernameField);

        // 密码输入面板
        this.passwordField = new JTextField("请输入密码", 12);
        this.passwordPanel.add(passwordLabel);
        this.passwordPanel.add(passwordField);

        // 输入信息面板
        this.inputPanel.add(usernamePanel);
        this.inputPanel.add(passwordPanel);

        // 按钮面板
        this.loginButton = new JButton("登录");
        this.loginButton.addActionListener(this);
        this.southPanel = new JPanel();
        this.southPanel.add(loginButton);
        this.registerButton = new JButton("修改密码");
        this.registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new StuChangeGUI();
            }
        });

        // 添加组件到窗口
        this.northrPanel.add(loginText);
        this.add(northrPanel, BorderLayout.NORTH);
        this.add(inputPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
        this.southPanel.add(registerButton);

        // 设置窗口可见
        this.setVisible(true);

    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        StudentActionDao studentActionDao = new StudentActionDao();
        String result = studentActionDao.loginDao(username, password);
        System.out.println(result);

        if (result.equals("success")) {
            JOptionPane.showMessageDialog(this, "登录成功");
            setVisible(false);
            new StuGUI();
        }else {
            JOptionPane.showMessageDialog(this, "用户名或密码错误");
        }
    }

    public static void main(String[] args) {

        new StuLogin();
    }

}
