package System;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Timer;
import java.util.TimerTask;

public class Start {

    private JFrame frame;
    private JLabel backgroundLabel;
    private String[] imagePaths;
    private int currentIndex = 0;
    private Timer timer;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Start window = new Start();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Start() {
        initialize();
        loadImages("C:\\Users\\msh12\\Desktop\\Background"); // 指定图片所在的文件夹路径
        startSlideshow();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(200, 200, 550, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backgroundLabel = new JLabel();
        backgroundLabel.setSize(500, 500);
        frame.setContentPane(backgroundLabel);

        JLabel lblNewLabel = new JLabel("欢迎来到评教系统");
        lblNewLabel.setBackground(new Color(150, 170, 190));
        lblNewLabel.setFont(new Font("华文行楷", Font.BOLD | Font.ITALIC, 37));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton btnNewButton = new JButton("学生登录");
        btnNewButton.setFont(new Font("华文楷体", Font.BOLD, 30));
        btnNewButton.addActionListener(e -> new StuLogin());

        JButton btnNewButton_1 = new JButton("教师登录");
        btnNewButton_1.setFont(new Font("华文楷体", Font.BOLD, 30));
        btnNewButton_1.addActionListener(e -> new TeaLogin());

        JButton btnNewButton_2 = new JButton("管理登录");
        btnNewButton_2.setFont(new Font("华文楷体", Font.BOLD, 30));
        btnNewButton_2.addActionListener(e -> new ManLogin());

        JTextArea textArea = new JTextArea();
        textArea.setForeground(new Color(160, 250, 250));
        textArea.setBackground(new Color(229, 227, 227));
        textArea.setFont(new Font("华文行楷", Font.BOLD, 24));
        textArea.setEditable(false);
        textArea.setEnabled(false);
        textArea.setText("本系统供学生，教师\r\n和管理人员使用");

        GroupLayout groupLayout = new GroupLayout(backgroundLabel);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(32)
                                                .addComponent(textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(btnNewButton_1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                                                                        .addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                                                .addGap(68)))
                                .addContainerGap())
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(233)
                                                .addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(58)
                                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(textArea, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(btnNewButton)
                                                                .addGap(36)
                                                                .addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)))))
                                .addGap(132))
        );
        backgroundLabel.setLayout(groupLayout);
    }

    private void loadImages(String directoryPath) {
        File dir = new File(directoryPath);
        if (dir.isDirectory()) {
            File[] files = dir.listFiles((d, name) -> name.endsWith(".jpg") || name.endsWith(".png"));
            if (files != null) {
                imagePaths = new String[files.length];
                for (int i = 0; i < files.length; i++) {
                    imagePaths[i] = files[i].getAbsolutePath();
                }
            }
        }
    }

    private void startSlideshow() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (imagePaths != null && imagePaths.length > 0) {
                    updateBackground(imagePaths[currentIndex]);
                    currentIndex = (currentIndex + 1) % imagePaths.length;
                }
            }
        }, 0, 3000); // 每3秒更换一次背景图片
    }

    private void updateBackground(String path) {
        try {
            Image image = ImageIO.read(new File(path));
            ImageIcon icon = new ImageIcon(image.getScaledInstance(backgroundLabel.getWidth(), backgroundLabel.getHeight(), Image.SCALE_SMOOTH));
            backgroundLabel.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
