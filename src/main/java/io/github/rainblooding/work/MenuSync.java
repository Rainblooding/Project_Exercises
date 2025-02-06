package io.github.rainblooding.work;

import io.github.rainblooding.work.base.MysqlTools;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import static javax.swing.LayoutStyle.ComponentPlacement.UNRELATED;

public class MenuSync extends JPanel {

    public MenuSync() {
        initUI();
    }

    private void initUI() {
        JLabel lbl = new JLabel("模块编号");
        JTextField field = new JTextField(15);
        JButton get = new JButton("获取插入语句");


        RSyntaxTextArea result = new RSyntaxTextArea(20, 60);
        result.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
        result.setCodeFoldingEnabled(true);
        result.setEditable(false);
        result.setLineWrap(true);  // 启用自动换行

        // 将 JLabel 放入 JScrollPane 中
        JScrollPane scrollPane = new RTextScrollPane(result, true);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        get.addActionListener(e -> {
//            System.out.println(field.getText());

            try {
                // "ENR151546"
                result.setText(MysqlTools.getMenuByTable("hsxy_pt_20240108", field.getText()));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
//            result.setText(field.getText());
        });
        createLayout(lbl, field, get, scrollPane);

    }

    private void createLayout(Component... args) {
        GroupLayout gl = new GroupLayout(this);
        this.setLayout(gl);

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(50)
                .addGroup(gl.createParallelGroup()
                        .addComponent(args[0])
                        .addComponent(args[1])
                        .addComponent(args[2])
                        .addComponent(args[3])
                )
                .addGap(50)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(50)
                .addGroup(gl.createSequentialGroup()
                        .addComponent(args[0])
                        .addComponent(args[1],  GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(UNRELATED)
                        .addComponent(args[2])
                        .addComponent(args[3])
                )
                .addGap(50)
        );

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            JFrame jFrame = new JFrame();
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setContentPane(new MenuSync());
            jFrame.setVisible(true);
            jFrame.setSize(300, 300);
        });
    }
}
