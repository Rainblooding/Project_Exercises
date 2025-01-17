package io.github.rainblooding.swing.menus;

import io.github.rainblooding.swing.utils.IconUtils;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Swing 助记符和加速器
 *
 * 该示例包含多个助记符和一个加速器。 三个菜单项共享一个动作对象。 选择这三个菜单项会使它们的操作命令打印到控制台。
 *
 * 助记符和加速器
 * 助记符和加速键是使您能够通过键盘执行命令的快捷键。
 * 助记符导航菜单层次结构以选择特定的菜单项，而加速器则跳过菜单层次结构并直接激活菜单项。
 */
public class ShortcutsEx extends JFrame {

    public ShortcutsEx() {

        initUI();
    }

    private void initUI() {

        createMenuBar();

        setTitle("Mnemonics and accelerators");
        setSize(360, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createMenuBar() {

        var menuBar = new JMenuBar();

        var iconNew = IconUtils.getIcon("sw/new.png");
        var iconOpen = IconUtils.getIcon("sw/open.png");
        var iconSave = IconUtils.getIcon("sw/save.png");
        var iconExit = IconUtils.getIcon("sw/exit.png");

        var fileMenu = new JMenu("File");
        // 助记符设置为“文件”菜单。 现在可以使用 Alt + F 快捷键激活菜单。
        fileMenu.setMnemonic(KeyEvent.VK_F);

        // “新建”菜单项将操作对象作为参数。 其构造器将文本标签，图标和助记键作为参数。
        var newMenuItem = new JMenuItem(new MenuItemAction("New", iconNew,
                KeyEvent.VK_N));

        var openMenuItem = new JMenuItem(new MenuItemAction("Open", iconOpen,
                KeyEvent.VK_O));

        var saveMenuItem = new JMenuItem(new MenuItemAction("Save", iconSave,
                KeyEvent.VK_S));

        var exitMenuItem = new JMenuItem("Exit", iconExit);
        // “退出”菜单项不使用操作对象。 其功能是单独构建的。 我们调用setMnemonic()方法来设置助记键。 要使用助记符，该组件必须在屏幕上可见。
        // 因此，我们必须首先激活菜单对象，使“退出”菜单项可见，然后才能激活此菜单项。 这意味着此菜单项通过 Alt + F + E 组合键激活。
        exitMenuItem.setMnemonic(KeyEvent.VK_E);
        exitMenuItem.setToolTipText("Exit application");
        // 加速器是直接启动菜单项的快捷键。 在我们的情况下，通过按 Ctrl + W 关闭我们的应用。 通过setAccelerator()方法设置加速器。
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
                InputEvent.CTRL_DOWN_MASK));

        exitMenuItem.addActionListener((event) -> System.exit(0));

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
    }

    /**
     * 此动作类的一个实例由三个菜单项共享。 动作使用各种键来定义其功能。 putValue()方法将字符串值与指定的键关联。
     */
    private class MenuItemAction extends AbstractAction {

        public MenuItemAction(String text, ImageIcon icon,
                              Integer mnemonic) {
            super(text);

            putValue(SMALL_ICON, icon);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println(e.getActionCommand());
        }
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new ShortcutsEx();
            ex.setVisible(true);
        });
    }
}
