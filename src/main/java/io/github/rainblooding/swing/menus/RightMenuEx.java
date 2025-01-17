package io.github.rainblooding.swing.menus;

import java.awt.EventQueue;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 * 右侧菜单
 * 该示例在左侧显示三个菜单，在右侧显示一个菜单。
 * 某些应用在右侧显示菜单。 通常，它是一个“帮助”菜单。
 */
public class RightMenuEx extends JFrame {

    public RightMenuEx() {

        initUI();
    }

    private void initUI() {

        createMenuBar();

        setTitle("Right menu");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createMenuBar() {

        // 将创建一个菜单栏和四个菜单对象。
        var menuBar = new JMenuBar();

        var fileMenu = new JMenu("File");
        var viewMenu = new JMenu("View");
        var toolsMenu = new JMenu("Tools");
        var helpMenu = new JMenu("Help");

        // 添加三个菜单后，我们使用Box.createHorizontalGlue()方法在菜单栏上添加水平胶水。
        // 胶水会吸收所有可用的额外空间。 这会将“帮助”菜单推到菜单栏的右侧。
        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        menuBar.add(toolsMenu);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new RightMenuEx();
            ex.setVisible(true);
        });
    }
}

