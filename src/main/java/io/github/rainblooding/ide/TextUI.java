package io.github.rainblooding.ide;


import javax.swing.plaf.basic.BasicTextAreaUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import java.awt.*;

public class TextUI extends BasicTextAreaUI {

    private RTextAreaBaseTest textArea;

    public TextUI(RTextAreaBaseTest textArea) {
        this.textArea = textArea;
    }

    @Override
    public void paintBackground(Graphics g) {
        // Only fill in the background if an image isn't being used.
        Color bg = textArea.getBackground();
        if (bg!=null) {
            g.setColor(bg);
            //g.fillRect(0, 0, textArea.getWidth(), textArea.getHeight());
            Rectangle r = g.getClipBounds();
            g.fillRect(r.x,r.y, r.width,r.height);
        }

        paintEditorAugmentations(g);

    }

    /**
     * RTextArea添加的油漆编辑器增强: 突出显示的线条，
     * 当前行突出显示和边距线。
     *
     * @param g The graphics context with which to paint.
     */
    protected void paintEditorAugmentations(Graphics g) {
        Rectangle visibleRect = textArea.getVisibleRect();
        paintLineHighlights(g);
        paintCurrentLineHighlight(g, visibleRect);
        paintMarginLine(g, visibleRect);
    }

    private void paintCurrentLineHighlight(Graphics g, Rectangle visibleRect) {
        if (textArea.getHighlightCurrentLine()) {

            Caret caret = textArea.getCaret();
            if (caret.getDot() == caret.getMark()) {

                Color highlight = textArea.getCurrentLineHighlightColor();
                int height = textArea.getLineHeight();

                if (textArea.getFadeCurrentLineHighlight()) {
                    Graphics2D g2d = (Graphics2D) g;
                    Color bg = textArea.getBackground();
                    GradientPaint paint = new GradientPaint(
                            visibleRect.x, 0, highlight,
                            visibleRect.x + visibleRect.width, 0,
                            bg == null ? Color.WHITE : bg);
                    g2d.setPaint(paint);
                    g2d.fillRect(visibleRect.x, textArea.getCurrentCaretY(),
                            visibleRect.width, height);
                } else {
                    g.setColor(highlight);
                    g.fillRect(visibleRect.x, textArea.getCurrentCaretY(),
                            visibleRect.width, height);
                }

            }
        }
    }

        /**
         * Paints any line highlights.
         *
         * @param g The graphics context.
         */
        protected void paintLineHighlights(Graphics g) {
            LineHighlightManager lhm = textArea.getLineHighlightManager();
            if (lhm!=null) {
                lhm.paintLineHighlights(g);
            }
        }


    /**
     * Draws the "margin line" if enabled.
     *
     * @param g The graphics context to paint with.
     * @param visibleRect The visible rectangle of this text area.
     */
    protected void paintMarginLine(Graphics g, Rectangle visibleRect) {
        if (textArea.isMarginLineEnabled()) {
            g.setColor(textArea.getMarginLineColor());
            Insets insets = textArea.getInsets();
            int marginLineX = textArea.getMarginLinePixelLocation() +
                    (insets==null ? 0 : insets.left);
            g.drawLine(marginLineX,visibleRect.y,
                    marginLineX,visibleRect.y+visibleRect.height);
        }
    }

    public int yForLineContaining(int offs) throws BadLocationException {
        Rectangle r = modelToView(textArea, offs);
        return r!=null ? r.y : -1;
    }
}
