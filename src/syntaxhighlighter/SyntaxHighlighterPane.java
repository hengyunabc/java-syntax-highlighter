/**
 * This is part of the Java SyntaxHighlighter.
 * 
 * It is distributed under MIT license. See the file 'readme.txt' for
 * information on usage and redistribution of this file, and for a
 * DISCLAIMER OF ALL WARRANTIES.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
package syntaxhighlighter;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JTextPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.BoxView;
import javax.swing.text.ComponentView;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.Highlighter;
import javax.swing.text.IconView;
import javax.swing.text.JTextComponent;
import javax.swing.text.LabelView;
import javax.swing.text.ParagraphView;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import syntaxhighlighter.Parser.MatchResult;

/**
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class SyntaxHighlighterPane extends JTextPane {

    private static final long serialVersionUID = 1L;
    //
    private int lineNumberOffset;
    //
    private Color highlightedBackground;
    private boolean highlightWhenMouseOver;
    private final List<Integer> highlightedLineList;
    private Highlighter.HighlightPainter highlightPainter;
    //
    private Theme theme;
    private Map<String, List<MatchResult>> styleList;
    //
    private int mouseOnLine;

    public SyntaxHighlighterPane() {
        super();

        setEditable(false);
        //<editor-fold defaultstate="collapsed" desc="editor kit">
        setEditorKit(new StyledEditorKit() {

            private static final long serialVersionUID = 1L;

            @Override
            public ViewFactory getViewFactory() {
                return new ViewFactory() {

                    @Override
                    public View create(Element elem) {
                        String kind = elem.getName();
                        if (kind != null) {
                            if (kind.equals(AbstractDocument.ContentElementName)) {
                                return new LabelView(elem) {

                                    @Override
                                    public int getBreakWeight(int axis, float pos, float len) {
                                        return 0;
                                    }
                                };
                            } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                                return new ParagraphView(elem) {

                                    @Override
                                    public int getBreakWeight(int axis, float pos, float len) {
                                        return 0;
                                    }
                                };
                            } else if (kind.equals(AbstractDocument.SectionElementName)) {
                                return new BoxView(elem, View.Y_AXIS);
                            } else if (kind.equals(StyleConstants.ComponentElementName)) {
                                return new ComponentView(elem) {

                                    @Override
                                    public int getBreakWeight(int axis, float pos, float len) {
                                        return 0;
                                    }
                                };
                            } else if (kind.equals(StyleConstants.IconElementName)) {
                                return new IconView(elem);
                            }
                        }
                        return new LabelView(elem) {

                            @Override
                            public int getBreakWeight(int axis, float pos, float len) {
                                return 0;
                            }
                        };
                    }
                };
            }
        });
        //</editor-fold>
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        lineNumberOffset = 0;

        //<editor-fold defaultstate="collapsed" desc="highlight">
        highlightedBackground = Color.black;
        highlightWhenMouseOver = true;
        highlightedLineList = new ArrayList<Integer>();

        highlightPainter = new Highlighter.HighlightPainter() {

            @Override
            public void paint(Graphics g, int p0, int p1, Shape bounds, JTextComponent c) {
                if (c.getParent() == null) {
                    return;
                }

                int startY = Math.abs(c.getY());
                int endY = startY + c.getParent().getHeight();

                FontMetrics textPaneFontMetrics = g.getFontMetrics(getFont());
                int textPaneFontHeight = textPaneFontMetrics.getHeight();

                int largerestLineNumber = c.getDocument().getDefaultRootElement().getElementCount();

                g.setColor(highlightedBackground);
                synchronized (highlightedLineList) {
                    for (Integer lineNumber : highlightedLineList) {
                        if (lineNumber > largerestLineNumber + lineNumberOffset) {
                            continue;
                        }
                        int _y = Math.max(0, textPaneFontHeight * (lineNumber - lineNumberOffset - 1));
                        if (_y + textPaneFontHeight < startY || _y > endY) {
                            continue;
                        }
                        g.fillRect(0, _y, c.getWidth(), textPaneFontHeight);
                    }
                }
                if (mouseOnLine != -1) {
                    if (mouseOnLine <= largerestLineNumber + lineNumberOffset) {
                        int _y = Math.max(0, textPaneFontHeight * (mouseOnLine - lineNumberOffset - 1));
                        if (_y + textPaneFontHeight > startY && _y < endY) {
                            g.fillRect(0, _y, c.getWidth(), textPaneFontHeight);
                        }
                    }
                }
            }
        };
        try {
            getHighlighter().addHighlight(0, 0, highlightPainter);
        } catch (BadLocationException ex) {
            Logger.getLogger(SyntaxHighlighterPane.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        mouseOnLine = -1;

        //<editor-fold defaultstate="collapsed" desc="mouse listener">
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseExited(MouseEvent e) {
                if (!highlightWhenMouseOver) {
                    return;
                }
                mouseOnLine = -1;
                repaint();
            }
        });
        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (!highlightWhenMouseOver) {
                    return;
                }

                Element defaultRootElement = getDocument().getDefaultRootElement();
                int documentOffsetStart = viewToModel(e.getPoint());

                int lineNumber = documentOffsetStart == -1 ? -1 : defaultRootElement.getElementIndex(documentOffsetStart) + 1 + lineNumberOffset;
                if (lineNumber == defaultRootElement.getElementCount()) {
                    try {
                        Rectangle rectangle = modelToView(documentOffsetStart);
                        if (e.getY() > rectangle.y + rectangle.height) {
                            lineNumber = -1;
                        }
                    } catch (BadLocationException ex) {
                        Logger.getLogger(SyntaxHighlighterPane.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (mouseOnLine != lineNumber) {
                    mouseOnLine = lineNumber;
                    repaint();
                }
            }
        });
        //</editor-fold>
    }

    @Override
    public void setHighlighter(Highlighter highlighter) {
        if (highlightPainter != null) {
            getHighlighter().removeHighlight(highlightPainter);
            try {
                highlighter.addHighlight(0, 0, highlightPainter);
            } catch (BadLocationException ex) {
                Logger.getLogger(SyntaxHighlighterPane.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        super.setHighlighter(highlighter);
    }

    public void setContent(String content) {
        DefaultStyledDocument document = (DefaultStyledDocument) getDocument();
        try {
            document.remove(0, document.getLength());
            if (theme != null) {
                document.insertString(0, content, theme.getPlain().getAttributeSet());
            } else {
                document.insertString(0, content, new SimpleAttributeSet());
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(SyntaxHighlighterPane.class.getName()).log(Level.SEVERE, null, ex);
        }
        setCaretPosition(0);
        styleList = null;
    }

    public void setStyle(Map<String, List<MatchResult>> styleList) {
        // not a deep copy
        this.styleList = new HashMap<String, List<MatchResult>>(styleList);

        if (theme == null) {
            return;
        }

        DefaultStyledDocument document = (DefaultStyledDocument) getDocument();
        document.setCharacterAttributes(0, document.getLength(), theme.getPlain().getAttributeSet(), true);

        for (String key : styleList.keySet()) {
            AttributeSet attributeSet = theme.getStyle(key).getAttributeSet();
            List<MatchResult> posList = styleList.get(key);
            for (MatchResult pos : posList) {
                document.setCharacterAttributes(pos.getOffset(), pos.getLength(), attributeSet, true);
            }
        }

        repaint();
    }

//    public void setCode(List<Segment> segmentList) {
////        FontMetrics fontMetrics = getFontMetrics(getFont());
////        int fontHeight = fontMetrics.getHeight() - fontMetrics.getLeading();
////        float alignmentY = (float) fontMetrics.getAscent() / (float) fontHeight;
//
//        Document document = getDocument();
//        try {
//            document.remove(0, document.getLength());
//        } catch (BadLocationException ex) {
//            Logger.getLogger(SyntaxHighlighterPane.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
//
//        for (Segment segment : segmentList) {
////            if (segment.getStyleKey().equals("string")) {
////                JCheckBox checkBox = new JCheckBox(segment.getContent());
////
////                checkBox.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 4));
////                checkBox.setOpaque(false);
////                checkBox.setAlignmentY(alignmentY);
////
////                Style style = segment.getStyle();
////                checkBox.setForeground(style.getColor());
////                checkBox.setBackground(style.getBackground());
////                checkBox.setFont(setFont(getFont(), style.isBold(), style.isItalic()));
////
////                checkBox.setPreferredSize(new Dimension(checkBox.getPreferredSize().width, fontHeight));
////
////                setCaretPosition(document.getLength());
////                insertComponent(checkBox);
////            } else {
//            try {
//                String content = segment.getContent();
//                segment.getStyle().setAttributeSet(attributeSet);
//                document.insertString(document.getLength(), content, attributeSet);
//            } catch (BadLocationException ex) {
//                Logger.getLogger(SyntaxHighlighterPane.class.getName()).log(Level.WARNING, null, ex);
//            }
////            }
//        }
//        setCaretPosition(0);
//    }
    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;

        setFont(theme.getFont());
        setBackground(theme.getBackground());
        setHighlightedBackground(theme.getHighlightedBackground());

        if (styleList != null) {
            setStyle(styleList);
        }
    }

    public int getLineNumberOffset() {
        return lineNumberOffset;
    }

    public void setLineNumberOffset(int offset) {
        lineNumberOffset = Math.max(lineNumberOffset, offset);
        repaint();
    }

    public Color getHighlightedBackground() {
        return highlightedBackground;
    }

    public void setHighlightedBackground(Color highlightedBackground) {
        this.highlightedBackground = highlightedBackground;
        repaint();
    }

    public boolean isHighlightWhenMouseOver() {
        return highlightWhenMouseOver;
    }

    public void setHighlightWhenMouseOver(boolean highlightWhenMouseOver) {
        this.highlightWhenMouseOver = highlightWhenMouseOver;
        if (!highlightWhenMouseOver) {
            mouseOnLine = -1;
        }
        repaint();
    }

    public List<Integer> getHighlightedLineList() {
        List<Integer> returnList;
        synchronized (highlightedLineList) {
            returnList = new ArrayList<Integer>(highlightedLineList);
        }
        return returnList;
    }

    public void setHighlightedLineList(List<Integer> highlightedLineList) {
        synchronized (this.highlightedLineList) {
            this.highlightedLineList.clear();
            this.highlightedLineList.addAll(highlightedLineList);
        }
        repaint();
    }

    public void addHighlightedLine(int lineNumber) {
        highlightedLineList.add(lineNumber);
        repaint();
    }

    protected static Font setFont(Font font, boolean bold, boolean italic) {
        if ((font.getStyle() & Font.ITALIC) != 0) {
            if (!bold) {
                return font.deriveFont(font.getStyle() ^ Font.BOLD);
            }
        } else {
            if (bold) {
                return font.deriveFont(font.getStyle() | Font.BOLD);
            }
        }
        if ((font.getStyle() & Font.ITALIC) != 0) {
            if (!italic) {
                return font.deriveFont(font.getStyle() ^ Font.ITALIC);
            }
        } else {
            if (italic) {
                return font.deriveFont(font.getStyle() | Font.ITALIC);
            }
        }
        return font;
    }
}
