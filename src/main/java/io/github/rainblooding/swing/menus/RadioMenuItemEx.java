package io.github.rainblooding.swing.menus;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;

/**
 * JRadioButtonMenuItem
 *
 * 该示例创建一个包含三个JRadioButtonMenuItem组件的菜单。
 *
 * JRadioButtonMenuItem使您可以从互斥的选项列表中进行选择。
 * 选择特定的JRadioButtonMenuItem会取消选择所有其他项目。 将JRadioButtonMenuItems放入ButtonGroup。
 */
public class RadioMenuItemEx extends JFrame {

    private JLabel statusBar;

    public RadioMenuItemEx() {

        initUI();
    }

    private void initUI() {

        createMenuBar();

        statusBar = new JLabel("Easy");
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        add(statusBar, BorderLayout.SOUTH);

        setTitle("JRadioButtonMenuItem");
        setSize(360, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createMenuBar() {

        var menuBar = new JMenuBar();
        var difMenu = new JMenu("Difficulty");
        difMenu.setMnemonic(KeyEvent.VK_F);

        // 该示例创建一个包含三个JRadioButtonMenuItem组件的菜单。
        // ButtonGroup用于为一组按钮创建一个多重排除范围。
        var difGroup = new ButtonGroup();

        // 创建一个新的JRadioButtonMenuItem。 用setSelected()方法选择它，并用add()方法放置在按钮组中。
        var easyRMenuItem = new JRadioButtonMenuItem("Easy");
        easyRMenuItem.setSelected(true);
        difMenu.add(easyRMenuItem);

        // ItemListener用于监听JRadioButtonMenuItem的事件。
        // getStateChange()确定状态更改的类型。 如果更改为ItemEvent.SELECTED，我们将在状态栏上更改状态。 （另一个状态更改是ItemEvent.DESELECTED。）
        easyRMenuItem.addItemListener((e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                statusBar.setText("Easy");
            }
        });

        var mediumRMenuItem = new JRadioButtonMenuItem("Medium");
        difMenu.add(mediumRMenuItem);

        mediumRMenuItem.addItemListener((e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                statusBar.setText("Medium");
            }
        });

        var hardRMenuItem = new JRadioButtonMenuItem("Hard");
        difMenu.add(hardRMenuItem);

        hardRMenuItem.addItemListener((e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                statusBar.setText("Hard");
            }
        });

        difGroup.add(easyRMenuItem);
        difGroup.add(mediumRMenuItem);
        difGroup.add(hardRMenuItem);

        menuBar.add(difMenu);

        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new RadioMenuItemEx();
            ex.setVisible(true);
        });
    }
}

