package io.github.rainblooding.ide;

import org.fife.ui.rtextarea.RTextAreaBase;

import javax.swing.text.BadLocationException;
import javax.swing.text.Position;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LineHighlightManager {

    private RTextAreaBase textArea;
    private List<LineHighlightInfo> lineHighlights;
    private LineHighlightInfoComparator comparator;

    class LineHighlightInfo {
        private Position offs;
        private Color color;

        public LineHighlightInfo(Position offs, Color color) {
            this.offs = offs;
            this.color = color;
        }

        public int getOffset() {
            return offs.getOffset();
        }

        public Color getColor() {
            return color;
        }
    }

    private static final class LineHighlightInfoComparator
            implements Comparator<LineHighlightInfo> {

        @Override
        public int compare(LineHighlightInfo lhi1, LineHighlightInfo lhi2) {
            if (lhi1.getOffset() < lhi2.getOffset()) {
                return -1;
            }
            return lhi1.getOffset() == lhi2.getOffset() ? 0 : 1;
        }

    }


    public LineHighlightManager(RTextAreaBase textArea) {
        this.textArea = textArea;
        comparator = new LineHighlightInfoComparator();
        lineHighlights = new ArrayList<>(1);
    }

    /**
     * 给指定行添加高亮
     *
     * @param line
     * @param color
     * @return
     */
    public Object addLineHighlight(int line, Color color) throws BadLocationException {
        // 获取指定行的偏移位置 并构造高亮信息
        int offset = textArea.getLineStartOffset(line);
        LineHighlightInfo lhi = new LineHighlightInfo(textArea.getDocument().createPosition(offset), color);

        int index = Collections.binarySearch(lineHighlights, lhi, comparator);
        if (index<0) { // Common case
            index = -(index+1);
        }

        lineHighlights.add(index, lhi);
        repaintLine(lhi);
        return lhi;
    }


    private void repaintLine(LineHighlightInfo lhi) {
        int offset = lhi.getOffset();

        if (offset >= 0 && offset <= textArea.getDocument().getLength()) {
            try {
                int y = textArea.yForLineContaining(offset);
                if (y > -1) {
                    textArea.repaint(0, y, textArea.getWidth(), textArea.getLineHeight());
                }
            } catch (BadLocationException ble) {
                ble.printStackTrace(); // Never happens
            }
        }
    }

    /**
     * 删除高亮
     *
     * @param tag
     */
    public void removeLineHighlight(Object tag) {
        if (tag instanceof LineHighlightInfo) {
            lineHighlights.remove(tag);
            repaintLine((LineHighlightInfo)tag);
        }
    }

    /**
     * 删除所有高亮
     *
     */
    public void removeAllLineHighlights() {
        if (lineHighlights!=null) {
            lineHighlights.clear();
            textArea.repaint();
        }
    }

    protected List<Object> getCurrentLineHighlightTags() {
        return lineHighlights == null ? Collections.emptyList() :
                new ArrayList<>(lineHighlights);
    }

    protected int getLineHighlightCount() {
        return lineHighlights == null ? 0 : lineHighlights.size();
    }

    /**
     * 绘制高亮
     *
     * @param g
     */
    public void paintLineHighlights(Graphics g) {

        int count = lineHighlights == null ? 0 : lineHighlights.size();
        if (count>0) {

            int docLen = textArea.getDocument().getLength();
            Rectangle vr = textArea.getVisibleRect();
            int lineHeight = textArea.getLineHeight();

            try {

                for (int i = 0; i < count; i++) {
                    LineHighlightInfo lhi = lineHighlights.get(i);
                    int offs = lhi.getOffset();
                    if (offs >= 0 && offs <= docLen) {
                        int y = textArea.yForLineContaining(offs);
                        if (y > vr.y - lineHeight) {
                            if (y < vr.y + vr.height) {
                                g.setColor(lhi.getColor());
                                g.fillRect(0, y, textArea.getWidth(), lineHeight);
                            } else {
                                break; // Out of visible rect
                            }
                        }
                    }
                }

            } catch (BadLocationException ble) { // Never happens
                ble.printStackTrace();
            }
        }

    }

}
