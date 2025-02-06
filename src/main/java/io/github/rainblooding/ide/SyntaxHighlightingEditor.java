package io.github.rainblooding.ide;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.util.regex.*;

public class SyntaxHighlightingEditor extends JTextPane {

    public SyntaxHighlightingEditor() {
        StyledDocument doc = getStyledDocument();
        // 设置默认样式
        Style defaultStyle = addStyle("default", null);
        StyleConstants.setForeground(defaultStyle, Color.BLACK);

        // 监听文档变化
        doc.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> applyHighlighting(doc, e.getOffset(), e.getLength()));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> applyHighlighting(doc, e.getOffset(), e.getLength()));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> applyHighlighting(doc, e.getOffset(), e.getLength()));
            }
        });
    }

    private void applyHighlighting(StyledDocument doc, int offset, int length) {
        try {
            String text = doc.getText(offset, length);
            // 清除当前样式（仅限于变化部分）
            doc.setCharacterAttributes(offset, length, getStyle("default"), true);

            // 定义语法规则和样式
            applyPattern(doc, text, "\\b(int|double|String|void|public|private|class|static|if|else|for|while)\\b", Color.BLUE); // 关键字
            applyPattern(doc, text, "//.*", Color.GRAY); // 单行注释
            applyPattern(doc, text, "\"(.*?)\"", new Color(0, 128, 0)); // 字符串
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    private static void applyPattern(StyledDocument doc, String text, String pattern, Color color) {
        // 创建样式
        StyleContext styleContext = StyleContext.getDefaultStyleContext();
        AttributeSet attributeSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

        // 应用正则表达式匹配并设置样式
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher m = p.matcher(text);
        while (m.find()) {
            doc.setCharacterAttributes(m.start(), m.end() - m.start(), attributeSet, false);
        }
    }

    public static void main(String[] args) {
        // 确保在事件调度线程中创建和更新所有 Swing 组件
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        // 创建 JFrame
        JFrame frame = new JFrame("Syntax Highlighting Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // 创建 SyntaxHighlightingEditor 并添加到 JFrame
        SyntaxHighlightingEditor editor = new SyntaxHighlightingEditor();
        frame.add(new JScrollPane(editor), BorderLayout.CENTER);

        // 设置可见
        frame.setVisible(true);
    }
}