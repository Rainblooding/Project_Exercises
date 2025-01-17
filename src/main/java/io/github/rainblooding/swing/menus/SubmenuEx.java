package io.github.rainblooding.swing.menus;

import io.github.rainblooding.swing.utils.IconUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Swing 子菜单
 * 本示例创建一个子菜单，并使用菜单分隔符分隔菜单项组。
 *
 * 每个菜单也可以有一个子菜单。 这样，我们可以将类似的命令分组。
 * 例如，我们可以将用于隐藏和显示各种工具栏（例如个人栏，地址栏，状态栏或导航栏）的命令放在称为工具栏的子菜单中。 在菜单中，我们可以使用分隔符来分隔命令。
 * 分隔符是一条简单的线。 通常的做法是使用单个分隔符将“新建”，“打开”，“保存”等命令与“打印”，“打印预览”等命令分开。 除助记符外，还可通过加速器启动菜单命令。
 */
public class SubmenuEx extends JFrame {

    public SubmenuEx() {
        initUI();
    }

    private void initUI() {
        createMenuBar();

        this.setTitle("Sibmenu");
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        ImageIcon iconNew = IconUtils.getIcon("sw/new.png");
        ImageIcon iconOpen  = IconUtils.getIcon("sw/open.png");
        ImageIcon iconSave  = IconUtils.getIcon("sw/save.png");
        ImageIcon iconExit  = IconUtils.getIcon("sw/exit.png");

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        // 子菜单与其他任何普通菜单一样。 它是用相同的方式创建的。 我们只需将菜单添加到现有菜单即可。
        JMenu impMenu = new JMenu("Import");
        impMenu.setMnemonic(KeyEvent.VK_I);
        JMenuItem newsMenuItem  = new JMenuItem("Import newsfeed list...");
        JMenuItem bookmarksMenuItem  = new JMenuItem("Import bookmarks...");
        JMenuItem importMailMenuItem  = new JMenuItem("Import mail...");
        impMenu.add(newsMenuItem);
        impMenu.add(bookmarksMenuItem);
        impMenu.add(importMailMenuItem);

        // 此JMenuItem构造器创建带有标签和图标的菜单项。
        var newMenuItem = new JMenuItem("New", iconNew);
        var openMenuItem = new JMenuItem("Open", iconOpen);
        var saveMenuItem = new JMenuItem("Save", iconSave);

        var exitMenuItem = new JMenuItem("Exit", iconExit);
        // 使用setToolTipText()方法将工具提示设置为“退出”菜单项。
        exitMenuItem.setToolTipText("Exit application");

        exitMenuItem.addActionListener((event) -> System.exit(0));


        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        // 分隔符是一条水平线，可以在视觉上分隔菜单项。 这样，我们可以将项目分组到一些合理的位置。 使用addSeparator()方法创建分隔符。
        fileMenu.addSeparator();
        fileMenu.add(impMenu);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);



        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new SubmenuEx();
            ex.setVisible(true);
        });
    }
}
