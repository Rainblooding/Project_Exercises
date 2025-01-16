package io.github.rainblooding.swing.first;

import javax.swing.*;
import java.awt.*;

/**
 * 学习swing
 * JFrame是带有标题和边框的顶层窗口。 它用于组织其他组件，通常称为子组件。
 *
 */
public class SimpleEx extends JFrame {

    public SimpleEx() {
        initUI();
    }


    public void initUI() {
        var quitButton = new JButton("Quit");
        quitButton.addActionListener((event) -> System.exit(0));
        this.createLayout(quitButton);

        this.setTitle("Simple example");
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void createLayout(JComponent... arg) {
        var pane = this.getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
        gl.setAutoCreateContainerGaps(true);
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var ex = new SimpleEx();
            ex.setVisible(true);
        });
    }

}
