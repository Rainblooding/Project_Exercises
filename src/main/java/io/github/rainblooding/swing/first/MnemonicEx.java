package io.github.rainblooding.swing.first;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * 助记符
 * 
 * @author rainblooding
 */
public class MnemonicEx extends JFrame {

    public void initUI() {
        var quitButton = new JButton("Quit");
        quitButton.setMnemonic(KeyEvent.VK_B);
        quitButton.addActionListener((event) -> System.exit(0));
        this.createLayout(quitButton);

        this.setTitle("Mnemonic");
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
        SwingUtilities.invokeLater(() -> {
            var ex = new MnemonicEx();
            ex.initUI();
            ex.setVisible(true);
        });
    }
}
