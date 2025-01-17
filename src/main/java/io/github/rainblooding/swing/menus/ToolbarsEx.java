package io.github.rainblooding.swing.menus;

import io.github.rainblooding.swing.utils.IconUtils;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.EventQueue;

/**
 * Swing 工具栏
 *
 * 窗口顶部显示两个工具栏。
 *
 * 通常需要在窗口上显示多个工具栏。 以下示例显示了如何执行此操作。
 */
public class ToolbarsEx extends JFrame {

    public ToolbarsEx() {

        initUI();
    }

    private void initUI() {

        createToolBars();

        setTitle("Toolbars");
        setSize(360, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * 使用JToolBar创建两个工具栏对象。
     */
    private void createToolBars() {

        var toolbar1 = new JToolBar();
        var toolbar2 = new JToolBar();

        var newIcon = IconUtils.getIcon("sw/new2.png");
        var openIcon = IconUtils.getIcon("sw/open2.png");
        var saveIcon = IconUtils.getIcon("sw/save2.png");
        var exitIcon = IconUtils.getIcon("sw/exit2.png");

        var newBtn = new JButton(newIcon);
        var openBtn = new JButton(openIcon);
        var saveBtn = new JButton(saveIcon);

        toolbar1.add(newBtn);
        toolbar1.add(openBtn);
        toolbar1.add(saveBtn);

        var exitBtn = new JButton(exitIcon);
        toolbar2.add(exitBtn);

        exitBtn.addActionListener((e) -> System.exit(0));

        createLayout(toolbar1, toolbar2);
    }

    private void createLayout(JComponent... arg) {

        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(arg[0], GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(arg[1], GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
        );
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new ToolbarsEx();
            ex.setVisible(true);
        });
    }
}

