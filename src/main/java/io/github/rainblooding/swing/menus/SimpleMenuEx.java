package io.github.rainblooding.swing.menus;

import io.github.rainblooding.utils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;

/**
 * 我们的示例将显示一个菜单项。 选择退出菜单项，我们关闭应用。
 * 一个简单的菜单栏示例开始。
 */
public class SimpleMenuEx extends JFrame {

    public SimpleMenuEx() {
        initUI();
    }

    private void initUI() {
        createMenuBar();
        this.setTitle("Simple Menu");
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createMenuBar() {
        // 使用JMenuBar创建菜单栏。
        JMenuBar menuBar = new JMenuBar();
        //菜单中显示退出图标。
        File file = FileUtils.getFileByResource("sw/exit.png");
        ImageIcon exitIcon = null;
        try {
            exitIcon = new ImageIcon(file.toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // 使用JMenu类创建菜单对象。 也可以通过键盘访问菜单。 要将菜单绑定到特定键，我们使用setMnemonic()方法。 在我们的情况下，可以使用 Alt + F 快捷方式打开菜单。
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        // 菜单对象由菜单项组成。 使用JMenuItem类创建一个菜单项。 菜单项具有其自己的助记符。 可以使用 Alt + F + E 组合键激活。
        JMenuItem exitItem = new JMenuItem("Exit", exitIcon);
        exitItem.setMnemonic(KeyEvent.VK_E);

        // 此代码行为菜单项创建工具提示。
        exitItem.setToolTipText("exit application");

        // JMenuItem是一种特殊的按钮组件。 我们向它添加了一个动作监听器，它终止了应用。
        exitItem.addActionListener((event) -> System.exit(0));


        // 菜单项被添加到菜单对象，菜单对象被插入菜单栏。
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        // setJMenuBar()方法设置JFrame容器的菜单栏。
        setJMenuBar(menuBar);

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new SimpleMenuEx();
            ex.setVisible(true);
        });
    }
}
