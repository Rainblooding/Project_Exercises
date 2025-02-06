package io.github.rainblooding.work;

import javax.swing.*;
import java.awt.*;

public class WorkTools extends JFrame {

    public WorkTools() {
        initUI();
    }

    private void initUI() {
        JTabbedPane tab = new JTabbedPane();
        tab.addTab("菜单同步", new MenuSync());
        tab.addTab("多数据库sql执行", new ManyTableExe());

        createLayout(tab);
        this.setTitle("开发工具");
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createLayout(JComponent... arg) {

        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );

        gl.setVerticalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
        );

        pack();
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            var ex = new WorkTools();
            ex.setVisible(true);
        });
    }


}
