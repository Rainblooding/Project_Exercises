package io.github.rainblooding.swing.first;

import javax.swing.*;

public class TooltipEx extends JFrame {

    public void initUI() {
        JButton btn = new JButton("Button");
        btn.setToolTipText("Content pane");
        createLayout(btn);

        this.setTitle("Tooltip");
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void createLayout(JComponent... arg) {
        JPanel pane = (JPanel) this.getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        pane.setToolTipText("This is a tooltip");
        gl.setAutoCreateContainerGaps(true);
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TooltipEx ex = new TooltipEx();
            ex.initUI();
            ex.setVisible(true);
        });
    }
}
