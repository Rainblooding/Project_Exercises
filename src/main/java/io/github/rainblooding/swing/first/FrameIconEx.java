package io.github.rainblooding.swing.first;

import io.github.rainblooding.utils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;

public class FrameIconEx extends JFrame {

    public FrameIconEx() throws MalformedURLException {
        initUI();
    }

    public void initUI() throws MalformedURLException {
        File file = FileUtils.getFileByResource("sw/web.png");
        var webIcon = new ImageIcon(file.toURI().toURL());

        this.setIconImage(webIcon.getImage());
        this.setTitle("Icon");
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                var ex = new FrameIconEx();
                ex.setVisible(true);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
    }
}
