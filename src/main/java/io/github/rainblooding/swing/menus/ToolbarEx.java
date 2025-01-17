package io.github.rainblooding.swing.menus;

import io.github.rainblooding.swing.utils.IconUtils;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.EventQueue;

/**
 * 工具栏
 *
 * 该示例创建一个带有一个退出按钮的工具栏。
 *
 * 菜单将我们可以在应用中使用的命令分组。 使用工具栏可以快速访问最常用的命令。 在 Java Swing 中，JToolBar类在应用中创建一个工具栏。
 */
public class ToolbarEx extends JFrame {

    public ToolbarEx() {

        initUI();
    }

    private void initUI() {

        createMenuBar();
        createToolBar();

        setTitle("Simple toolbar");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createMenuBar() {

        var menuBar = new JMenuBar();
        var fileMenu = new JMenu("File");

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    private void createToolBar() {

        // 使用JToolBar创建工具栏。
        var toolbar = new JToolBar();
        var icon = IconUtils.getIcon("sw/exit.png");

        // 我们创建一个按钮并将其添加到工具栏。 插入工具栏中的按钮是常规JButton。
        var exitButton = new JButton(icon);
        toolbar.add(exitButton);

        exitButton.addActionListener((e) -> System.exit(0));

        // 工具栏位于BorderLayout的北部区域。 （BorderLayout是JFrame，JWindow，JDialog，JInternalFrame和JApplet内容窗格的默认布局管理器。
        add(toolbar, BorderLayout.NORTH);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new ToolbarEx();
            ex.setVisible(true);
        });
    }
}

