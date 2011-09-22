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

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JTextPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.BoxView;
import javax.swing.text.ComponentView;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.IconView;
import javax.swing.text.LabelView;
import javax.swing.text.ParagraphView;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import syntaxhighlighter.Parser.Segment;

/**
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class SyntaxHighlighterPane extends JTextPane {

    private static final long serialVersionUID = 1L;

    public SyntaxHighlighterPane() {
        setEditable(false);
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
                                return new ComponentView(elem);
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
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    }

    public void setCode(List<Segment> segmentList) {
//        FontMetrics fontMetrics = getFontMetrics(getFont());
//        int fontHeight = fontMetrics.getHeight();
//        float alignmentY = (float) fontMetrics.getAscent() / (float) fontHeight;

        Document document = getDocument();

        for (Segment segment : segmentList) {
//            if (segment.getStyleKey().equals("string")) {
//                JCheckBox checkBox = new JCheckBox(segment.getContent());
//
//                checkBox.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 4));
//                checkBox.setOpaque(false);
//                checkBox.setAlignmentY(alignmentY);
//                checkBox.setPreferredSize(new Dimension(checkBox.getPreferredSize().width, fontHeight));
//
//                Style style = segment.getStyle();
//                checkBox.setForeground(style.getColor());
//                checkBox.setFont(setFont(getFont(), style.isBold(), style.isItalic()));
//
//                setCaretPosition(document.getLength());
//                insertComponent(checkBox);
//            } else {
            try {
                document.insertString(document.getLength(), segment.getContent(), segment.getStyle().getAttributeSet());
            } catch (BadLocationException ex) {
                Logger.getLogger(SyntaxHighlighterPane.class.getName()).log(Level.WARNING, null, ex);
            }
//            }
            setCaretPosition(0);
        }
    }
//    public static Font setFont(Font font, boolean bold, boolean italic) {
//        if ((font.getStyle() & Font.ITALIC) != 0) {
//            if (!bold) {
//                return font.deriveFont(font.getStyle() ^ Font.BOLD);
//            }
//        } else {
//            if (bold) {
//                return font.deriveFont(font.getStyle() | Font.BOLD);
//            }
//        }
//        if ((font.getStyle() & Font.ITALIC) != 0) {
//            if (!italic) {
//                return font.deriveFont(font.getStyle() ^ Font.ITALIC);
//            }
//        } else {
//            if (italic) {
//                return font.deriveFont(font.getStyle() | Font.ITALIC);
//            }
//        }
//        return font;
//    }
}
