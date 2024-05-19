package System;

import Base.Teacher;
import Dao.TeacehrDao;
import Dao.TeacherActionDao;
import Dao.TeacherDaoImp;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;



public class StuGUI extends JFrame {


    private JPanel leftPanel, rightPanel, titlePanel, gradePanel, actionPanel,idPanel;
    private JSplitPane splitPane;
    private Integer row;
    private JLabel titleLabel;
    private JTextField gradeField, actionField,idField;
    private JScrollPane tableScrollPane;
    private DefaultTableModel tableModel;
    private JTable classtable;
    private JButton updateButton;
    private String columnNames[] = {"职工号", "姓名", "学院", "教授课程", "成绩"};

    public StuGUI() {
        this.setTitle("评教页面");
        this.setBounds(420, 315, 1040, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // 设置标题标签
        this.titleLabel = new JLabel("教师信息汇总表");
        this.titleLabel.setFont(new Font("华文隶书", Font.BOLD, 32));

        // 创建表格模型并添加列名
        this.tableModel = new DefaultTableModel();
        this.tableModel.setColumnIdentifiers(columnNames);
        this.classtable = new JTable(this.tableModel);

        // 从数据库中查询教室信息并添加到表格模型中
        TeacehrDao teacherDao = new TeacherDaoImp();
        List<Teacher> teachers = teacherDao.findAll();
        for (Teacher teacher : teachers) {
            Object[] rowData = {
                    teacher.getIdteacher(),
                    teacher.getNameteacher(),
                    teacher.getCollege(),
                    teacher.getCourse(),
                    teacher.getSumgrade()
            };
            tableModel.addRow(rowData);
        }

        // 设置左侧单元格面板
        this.gradePanel = new JPanel();
        JLabel gradeLabel = new JLabel(columnNames[4] + ":");
        this.gradeField = new JTextField(12);
        this.gradePanel.add(gradeLabel);
        this.gradePanel.add(gradeField);
        this.idPanel = new JPanel();
        JLabel idLabel = new JLabel(columnNames[0] + ":");
        this.idField = new JTextField(12);
        this.idPanel.add(idLabel);
        this.idPanel.add(idField);

        // 设置修改按钮
        this.updateButton = new JButton("修改");
        this.updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = classtable.getSelectedRow();
                if (selectedRow >= 0) {
                    row = selectedRow;
                    TeacherActionDao teacherActionDao = new TeacherActionDao();
                    try {
                        teacherActionDao.changesumgradeDao(idField.getText(),gradeField.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    tableModel.setValueAt(gradeField.getText(), row, 4);
                } else {
                    JOptionPane.showMessageDialog(null, "请先选中要修改的行");
                }
            }
        });

        this.actionPanel = new JPanel();
        this.actionPanel.add(updateButton);

        // 将面板添加到左侧面板
        this.leftPanel = new JPanel(new GridLayout(3, 2));
        this.leftPanel.add(idPanel);
        this.leftPanel.add(gradePanel);

        this.leftPanel.add(actionPanel);

        // 设置标题面板
        this.titlePanel = new JPanel();
        this.titlePanel.add(titleLabel);

        // 设置表格和滚动面板
        this.tableScrollPane = new JScrollPane(classtable);
        this.rightPanel = new JPanel(new BorderLayout());
        this.rightPanel.add(titlePanel, BorderLayout.NORTH);
        this.rightPanel.add(tableScrollPane, BorderLayout.CENTER);

        // 添加事件监听器
        this.classtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                row = classtable.getSelectedRow();
                if (row >= 0) {
                    idField.setText(tableModel.getValueAt(row, 0).toString());
                    gradeField.setText(tableModel.getValueAt(row, 4).toString());
                }
            }
        });

        // 设置分割面板
        this.splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        this.splitPane.setDividerLocation(260);
        this.getContentPane().add(splitPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new StuGUI();
    }
}
