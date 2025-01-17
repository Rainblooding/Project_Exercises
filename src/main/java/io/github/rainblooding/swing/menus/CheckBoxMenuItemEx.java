package io.github.rainblooding.swing.menus;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 * JCheckBoxMenuItem
 *
 * 本示例使用JCheckBoxMenuItem来切换状态栏的可见性。
 *
 * JCheckBoxMenuItem是可以选择或取消选择的菜单项。 如果选中该菜单项，通常会在其旁边带有对勾标记。
 * 如果未选择或取消选择，菜单项将显示而没有选中标记。 与常规菜单项一样，复选框菜单项可以具有与之关联的文本或图形图标，或两者都有。
 */
public class CheckBoxMenuItemEx extends JFrame {

    private JLabel statusBar;

    public CheckBoxMenuItemEx() {

        initUI();
    }

    private void initUI() {

        createMenuBar();

        // 状态栏是一个简单的JLabel组件。 我们在标签周围放置了凸起的EtchedBorder，以使其可见。
        statusBar = new JLabel("Ready");
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        add(statusBar, BorderLayout.SOUTH);

        setTitle("JCheckBoxMenuItem");
        setSize(360, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * JCheckBoxMenuItem是一种特殊的按钮组件。 它实现了ItemSelectable接口。 ItemListener可用于监听其状态变化。 根据其状态，我们显示或隐藏状态栏。
     */
    private void createMenuBar() {

        var menuBar = new JMenuBar();
        var fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        var viewMenu = new JMenu("View");
        viewMenu.setMnemonic(KeyEvent.VK_V);

        var showStatusBarMenuItem = new JCheckBoxMenuItem("Show statubar");
        showStatusBarMenuItem.setMnemonic(KeyEvent.VK_S);
        showStatusBarMenuItem.setDisplayedMnemonicIndex(5);

        // JCheckBoxMenuItem创建一个复选框菜单项。 标签上有两个字母；
        // 因此，我们使用setDisplayedMnemonicIndex()方法来选择要强调的内容。 我们选择了第二个。
        showStatusBarMenuItem.setSelected(true);

        showStatusBarMenuItem.addItemListener((e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                statusBar.setVisible(true);
            } else {
                statusBar.setVisible(false);
            }
        });

        viewMenu.add(showStatusBarMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(viewMenu);

        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new CheckBoxMenuItemEx();
            ex.setVisible(true);
        });
    }
}

