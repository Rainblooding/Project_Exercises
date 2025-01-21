package io.github.rainblooding._3D;

import io.github.rainblooding._3D.base._3DPoint;
import io.github.rainblooding.swing.utils.IconUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.KeyEvent;
import java.io.File;

public class ObjJFrame extends JFrame {

    private ObjViewer viewer;

    public ObjJFrame() {
        initUI();
    }

    private void initUI() {
        this.createMenuBar();
        this.viewer = new ObjViewer();

        this.add(this.viewer);
        this.viewer.set3DLocation(new _3DPoint(viewer.getWidth() / 2, viewer.getHeight() / 2, -180));
        this.viewer.requestFocus();

        this.setTitle("3D Object Viewer");
        this.setSize(500, 350);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem openItem = new JMenuItem("Open obj", IconUtils.getIcon("sw/open.png"));
        openItem.setMnemonic(KeyEvent.VK_O);
        openItem.addActionListener((e) -> {
            var fileChooser = new JFileChooser();
            var filter = new FileNameExtensionFilter("Java files", "obj");
            fileChooser.addChoosableFileFilter(filter);

            int ret = fileChooser.showDialog(viewer, "Open file");

            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                viewer.loadObj(file);
            }
        });

        fileMenu.add(openItem);
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            ObjJFrame ex = new ObjJFrame();
            ex.setVisible(true);
        });
    }
}
