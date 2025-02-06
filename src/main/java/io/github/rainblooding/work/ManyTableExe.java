package io.github.rainblooding.work;

import io.github.rainblooding.work.base.MysqlTools;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.sql.SQLException;

import static javax.swing.LayoutStyle.ComponentPlacement.UNRELATED;

public class ManyTableExe extends JPanel {

    private RSyntaxTextArea sqlTextArea;
    private RSyntaxTextArea result;
    JButton run;
    JScrollPane resultScrollPane;

    public ManyTableExe() {
        initUI();
    }

    private void initUI() {


        sqlTextArea = new RSyntaxTextArea(20, 60);
        sqlTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
        sqlTextArea.setCodeFoldingEnabled(true);
        sqlTextArea.setEditable(true);
        sqlTextArea.setLineWrap(true);  // 启用自动换行

        // 将 JLabel 放入 JScrollPane 中
        JScrollPane scrollPane = new RTextScrollPane(sqlTextArea, true);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        run = new JButton("执行");
        result = new RSyntaxTextArea(20, 60);
        result.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
        result.setCodeFoldingEnabled(true);
        result.setEditable(false);
        result.setLineWrap(true);  // 启用自动换行

        // 将 JLabel 放入 JScrollPane 中
        resultScrollPane = new RTextScrollPane(result, true);
        resultScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        resultScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        createLayout(scrollPane, run , resultScrollPane);


        result.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                autoScrollToBottom();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // 不需要处理删除操作
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // 不需要处理属性更改
            }
        });
        run.addActionListener(e -> {
//            System.out.println(field.getText());

            new CreateTableTask().execute();
//            result.setText(field.getText());
        });

    }
    private void autoScrollToBottom() {
        // 确保所有 UI 更新都在事件调度线程中进行
        SwingUtilities.invokeLater(() -> {
            // 将滚动条移动到最底部
            JScrollBar verticalScrollBar = resultScrollPane.getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
        });
    }


    private void createLayout(Component... args) {
        GroupLayout gl = new GroupLayout(this);
        this.setLayout(gl);

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(50)
                .addGroup(gl.createParallelGroup()
                        .addComponent(args[0])
                        .addComponent(args[1])
                        .addComponent(args[2])
                )
                .addGap(50)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(50)
                .addGroup(gl.createSequentialGroup()
                        .addComponent(args[0])
                        .addComponent(args[1],  GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(UNRELATED)
                        .addComponent(args[2])
                )
                .addGap(50)
        );

    }

    // 定义一个 SwingWorker 子类来执行创建表的任务
    private class CreateTableTask extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {
            // 模拟耗时的数据库操作
            String sql = sqlTextArea.getText();
            try {
                // 调用你的 MySQL 工具类方法
                MysqlTools.createTable(result, sql); // 假设 result 是你传递给方法的参数之一
            } catch (Exception ex) {
                ex.printStackTrace();
                // 处理异常并更新状态标签
                SwingUtilities.invokeLater(() -> {
                    run.setEnabled(true);
                });
                return null;
            }
            return null;
        }
    }
}
