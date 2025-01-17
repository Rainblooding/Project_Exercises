package io.github.rainblooding.swing.first;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * 接受鼠标坐标
 */
public class MouseMoveEventEx extends JFrame {

    private JLabel coords;

    private MouseMoveEventEx() {
        initUI();
    }

    private void initUI() {
        coords = new JLabel("");
        createLayout(coords);
        addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {

                super.mouseMoved(e);

                int x = e.getX();
                int y = e.getY();

                var text = String.format("x: %d, y: %d", x, y);

                coords.setText(text);
            }
        });

        this.setTitle("Mouse move event");
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    private void createLayout(JComponent... arg) {

        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
                .addGap(250)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addGap(130)
        );

        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var ex = new MouseMoveEventEx();
            ex.setVisible(true);
        });
    }
}
