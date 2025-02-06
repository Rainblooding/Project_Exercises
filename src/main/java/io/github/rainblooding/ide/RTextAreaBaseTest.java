package io.github.rainblooding.ide;

import io.github.rainblooding._3D.ObjJFrame;
import org.fife.ui.rtextarea.RTextAreaBase;
import org.fife.ui.rtextarea.RTextAreaUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTextAreaUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import java.awt.*;

public class RTextAreaBaseTest extends RTextAreaBase {

    private LineHighlightManager lineHighlightManager;

    public RTextAreaBaseTest(int rows, int clo) {
        super(rows, clo);
    }

    @Override
    protected RTAMouseListener createMouseListener() {
        return null;
    }

    @Override
    protected RTextAreaUI createRTextAreaUI() {
        return null;
    }

    public Object addLineHighlight(int line, Color color) throws BadLocationException {
        if (lineHighlightManager == null) {
            lineHighlightManager = new LineHighlightManager(this);
        }
        return lineHighlightManager.addLineHighlight(line, color);
    }

    public LineHighlightManager getLineHighlightManager() {
        return lineHighlightManager;
    }

    public int getCurrentCaretY() {
        return super.getCurrentCaretY();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            RTextAreaBaseTest rTextAreaBase = new RTextAreaBaseTest(20, 60);
            rTextAreaBase.setCurrentLineHighlightColor(rTextAreaBase.getCurrentLineHighlightColor());

            rTextAreaBase.setCaret(new DefaultCaret());
            rTextAreaBase.setUI(new TextUI(rTextAreaBase));
            try {
                rTextAreaBase.addLineHighlight(0, Color.RED);
            } catch (BadLocationException e) {
                throw new RuntimeException(e);
            }
            JScrollPane scrollPane = new JScrollPane(rTextAreaBase);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            JFrame j = new JFrame();
            j.setSize(500, 300);
            j.add(scrollPane);
            j.setVisible(true);
        });
    }
}
