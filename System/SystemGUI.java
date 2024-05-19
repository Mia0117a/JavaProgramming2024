package System;

import Base.FileAction;
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
import java.util.Objects;

public class SystemGUI extends JFrame {

    private JPanel leftPanel, rightPanel, titlePanel, leftCellPanel, idPanel, namePanel, collegePanel, courcePanel, gradePanel, fileButtonPanel, actionPanel;
    private JSplitPane splitPane;
    private Integer row;
    private JLabel titleLabel, fileLabel;
    private JTextField idField, nameField, numberField, collegeField, courceField, gradeField;
    private JScrollPane tableScrollPane;
    private DefaultTableModel tableModel;
    private JTable classtable;
    private JButton fileButton, addButton, deleteButton, updateButton;
    private JComboBox<String> fileComboBox;
    private String SelectAll[] = {".xls", ".csv"};
    private String columnNames[] = {"职工号", "姓名", "学院", "教授课程", "成绩"};

    public SystemGUI() {
        this.setTitle("评教页面");
        this.setBounds(420, 315, 1040, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.leftCellPanel = new JPanel(new GridLayout(9, 1));
        // 设置标题标签
        this.titleLabel = new JLabel("教室信息汇总表");
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
        this.leftCellPanel=new JPanel(new GridLayout(9, 1));
        JLabel[] leftLabels = new JLabel[columnNames.length];
        this.idPanel = new JPanel();
        this.namePanel = new JPanel();
        this.collegePanel = new JPanel();
        this.courcePanel = new JPanel();
        this.gradePanel = new JPanel();
        this.fileButtonPanel = new JPanel();

        for (int i = 0; i < columnNames.length; i++) {
            leftLabels[i] = new JLabel(columnNames[i]+":");
            /*leftLabels[i].setFont(new Font("微软雅黑", Font.PLAIN, 16));
            leftLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
            leftLabels[i].setVerticalAlignment(SwingConstants.CENTER);
            leftLabels[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));*/
        }
        this.idField = new JTextField(12);
        this.idPanel.add(leftLabels[0]);
        this.idPanel.add(idField);

        this.nameField = new JTextField(12);
        this.namePanel.add(leftLabels[1]);
        this.namePanel.add(nameField);

        this.collegeField = new JTextField(12);
        this.collegePanel.add(leftLabels[2]);
        this.collegePanel.add(collegeField);

        this.courceField = new JTextField(12);
        this.courcePanel.add(leftLabels[3]);
        this.courcePanel.add(courceField);

        this.gradeField = new JTextField(12);
        this.gradePanel.add(leftLabels[4]);
        this.gradePanel.add(gradeField);
        leftCellPanel.add(gradePanel);



        // 设置文件导出按钮面板
        this.fileLabel = new JLabel("文件导出格式：");
        this.fileComboBox = new JComboBox<>(SelectAll);
        this.fileButton = new JButton("导出");
        this.fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileAction fileAction = new FileAction();
                if (Objects.requireNonNull(fileComboBox.getSelectedItem()).toString().equals(".xls")){
                    fileAction.fileAction_xls();
                    JOptionPane.showMessageDialog(null, "导出成功");
                }
            }


        } );
        this.fileButtonPanel.add(fileLabel);
        this.fileButtonPanel.add(fileComboBox);
        this.fileButtonPanel.add(fileButton);


        this.updateButton = new JButton("修改");
        this.updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = classtable.getSelectedRow();
                if (selectedRow >= 0) {
                    row = selectedRow;

                    TeacherActionDao teacherActionDao = new TeacherActionDao();
                    try {
                        teacherActionDao.updateDao(idField.getText(), nameField.getText(), collegeField.getText(), courceField.getText(), gradeField.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    tableModel.setValueAt(idField.getText(), row, 0);
                    tableModel.setValueAt(nameField.getText(), row, 1);
                    tableModel.setValueAt(collegeField.getText(), row, 2);
                    tableModel.setValueAt(courceField.getText(), row, 3);
                    tableModel.setValueAt(gradeField.getText(), row, 4);
                } else {
                    // 如果没有选中任何行，可能需要给出提示或者采取其他操作
                    JOptionPane.showMessageDialog(null, "请先选中要修改的行");
                }

            }
        });

        this.actionPanel = new JPanel();
        this.actionPanel.add(updateButton);


        this.leftCellPanel.add(idPanel);
        this.leftCellPanel.add(namePanel);
        this.leftCellPanel.add(collegePanel);
        this.leftCellPanel.add(courcePanel);
        this.leftCellPanel.add(gradePanel);
        this.leftCellPanel.add(fileButtonPanel);
        this.leftCellPanel.add(actionPanel);
        this.titlePanel = new JPanel();
        this.titlePanel.add(titleLabel);
        this.classtable = new JTable(tableModel);

        this.classtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            private Object[] value = new Object[columnNames.length];
            public void valueChanged(ListSelectionEvent e) {
                row = classtable.getSelectedRow();
                System.out.println("所选中的行号为" + row);
                if (row >= 0) {
                    for (int i = 0; i < 7; i++) {
                        this.value[i] = tableModel.getValueAt(row, i);
                    }
                    idField.setText(value[0].toString());
                    nameField.setText(value[1].toString());
                    collegeField.setText(value[2].toString());
                    courceField.setText(value[3].toString());
                    gradeField.setText(value[4].toString());
                }
            }
        });

        this.tableScrollPane = new JScrollPane(classtable);
        this.rightPanel = new JPanel(new BorderLayout());
        this.rightPanel.setBackground(Color.WHITE);
        this.tableScrollPane = new JScrollPane(classtable);
        this.tableScrollPane.setColumnHeaderView(titlePanel);
        this.rightPanel.add(titlePanel, BorderLayout.NORTH);
        this.rightPanel.add(tableScrollPane, BorderLayout.CENTER);

        this.leftPanel = new JPanel(new BorderLayout());
        this.leftPanel.setBackground(Color.GRAY);
        this.leftPanel.add(leftCellPanel, BorderLayout.CENTER);
        this.splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        this.splitPane.setDividerLocation(260);



        if (this.leftPanel == null) {
            // 如果为null，则创建一个新的JPanel并设置为this.leftPanel
            this.leftPanel = new JPanel();
        }
        this.rightPanel = new JPanel(new BorderLayout());
        this.classtable = new JTable(tableModel);
        this.tableScrollPane = new JScrollPane(classtable);
        this.rightPanel.add(tableScrollPane, BorderLayout.CENTER);
        this.leftPanel.add(leftCellPanel, BorderLayout.CENTER);
        this.splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        this.getContentPane().add(splitPane, BorderLayout.CENTER);
        setVisible(true);
    }
    // 设置表格面板
    public static void main(String[] args) {
        new SystemGUI();
    }

}
