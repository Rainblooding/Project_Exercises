package io.github.rainblooding.swing.menus;


import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * 弹出菜单
 *
 * 该示例显示了带有两个命令的弹出菜单。 第一个命令最大化窗口，第二个命令退出应用。
 *
 * 菜单的另一种类型是弹出菜单。 Java Swing 具有此功能的JPopupMenu类。 它也称为上下文菜单，通常在右键单击组件时显示。
 * 想法是仅提供与当前上下文相关的命令。 说我们有一张图片。 通过右键单击图像，我们将弹出一个窗口，其中包含用于保存，缩放或移动图像的命令。
 */
public class PopupMenuEx extends JFrame {

    private JPopupMenu popupMenu;

    public PopupMenuEx() {

        initUI();
    }

    private void initUI() {

        createPopupMenu();

        setTitle("JPopupMenu");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createPopupMenu() {

        // JPopupMenu创建一个弹出菜单。
        popupMenu = new JPopupMenu();

        // 弹出菜单由JMenuItems组成。 此项目将最大化框架。 getExtendedState()方法确定帧的状态。
        // 可用状态为：NORMAL，ICONIFIED，MAXIMIZED_HORIZ，MAXIMIZED_VERT和MAXIMIZED_BOTH。 最大化帧后，我们将使用setEnabled()方法禁用菜单项。
        var maximizeMenuItem = new JMenuItem("Maximize");
        maximizeMenuItem.addActionListener((e) -> {
            if (getExtendedState() != JFrame.MAXIMIZED_BOTH) {
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                maximizeMenuItem.setEnabled(false);
            }
        });

        // 菜单项通过add()插入到弹出菜单中。
        popupMenu.add(maximizeMenuItem);

        var quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.addActionListener((e) -> System.exit(0));

        popupMenu.add(quitMenuItem);


        // 弹出菜单显示在我们用鼠标按钮单击的位置。 getButton()方法返回哪些鼠标按钮已更改状态。
        // MouseEvent.BUTTON3仅在右键单击时启用弹出菜单。 一旦窗口未最大化，我们将启用最大化菜单项。
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {

                if (getExtendedState() != JFrame.MAXIMIZED_BOTH) {
                    maximizeMenuItem.setEnabled(true);
                }

                if (e.getButton() == MouseEvent.BUTTON3) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new PopupMenuEx();
            ex.setVisible(true);
        });
    }
}
