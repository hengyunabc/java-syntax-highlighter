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
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;

/**
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class JTextComponentRowHeader extends JPanel {

    private static final long serialVersionUID = 1L;
    //
    private Font lineNumberFont = new Font("Verdana", Font.PLAIN, 10);
    private Object textAntiAliasing = RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT;
    private Color borderColor = new Color(184, 184, 184);
    private int paddingLeft = 7;
    private int paddingRight = 2;
    private int borderWidth = 1;
    //
    protected JScrollPane scrollPane;
    protected JTextComponent textComponent;
    //
    protected int panelWidth;
    protected int numberOfRows;
    protected int previousRecordedHeight;

    public JTextComponentRowHeader(JScrollPane scrollPane, JTextComponent textComponent) {
        super();

        this.scrollPane = scrollPane;
        this.textComponent = textComponent;
        this.textComponent.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }
        });

        update();
    }

    protected void update() {
        int _numberOfRows = textComponent.getDocument().getDefaultRootElement().getElementCount();
        int _panelWidth = getFontMetrics(lineNumberFont).stringWidth(Integer.toString(numberOfRows)) + paddingLeft + paddingRight;
        if (panelWidth != _panelWidth || numberOfRows != _numberOfRows) {
            panelWidth = _panelWidth;
            numberOfRows = _numberOfRows;
            updateSize();
        }
    }

    protected void updateSize() {
        Container parent = getParent();
        if (parent != null) {
            parent.doLayout();
            scrollPane.doLayout();
            parent.repaint();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        previousRecordedHeight = textComponent.getPreferredSize().height;
        return new Dimension(panelWidth, previousRecordedHeight);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (previousRecordedHeight != textComponent.getPreferredSize().height) {
            previousRecordedHeight = textComponent.getPreferredSize().height;
            updateSize();
        }

        JViewport viewport = scrollPane.getViewport();
        Point viewPosition = viewport.getViewPosition();
        Dimension viewportSize = viewport.getSize();

        Document document = textComponent.getDocument();
        Element defaultRootElement = document.getDefaultRootElement();

        // maybe able to get the value when font changed and cache them
        // however i'm not sure if there is any condition which will make the java.awt.FontMetrics get by getFontMetrics() from java.awt.Graphics is different from getFontMetrics() from java.awt.Component
        FontMetrics fontMetrics = g.getFontMetrics(lineNumberFont);
        int fontHeight = fontMetrics.getHeight();
        int fontAscent = fontMetrics.getAscent();
        int fontLeading = fontMetrics.getLeading();

        FontMetrics textPaneFontMetrics = g.getFontMetrics(textComponent.getFont());
        int textPaneFontHeight = textPaneFontMetrics.getHeight();


        int documentOffsetStart = textComponent.viewToModel(viewPosition);
        int documentOffsetEnd = textComponent.viewToModel(new Point(viewPosition.x + viewportSize.width, viewPosition.y + viewportSize.height));

        int startLine = defaultRootElement.getElementIndex(documentOffsetStart) + 1;
        int endLine = defaultRootElement.getElementIndex(documentOffsetEnd) + 1;


        // draw right border
        g.setColor(borderColor);
        g.fillRect(panelWidth - borderWidth, viewPosition.y, borderWidth, viewportSize.height);

        // draw line number
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, textAntiAliasing);
        g.setColor(getForeground());
        g.setFont(lineNumberFont);
        int startY = -1;
        try {
            startY = textComponent.modelToView(documentOffsetStart).y + (textPaneFontHeight / 2) + fontAscent - (fontHeight / 2) + fontLeading;
        } catch (BadLocationException ex) {
            Logger.getLogger(JTextComponentRowHeader.class.getName()).log(Level.WARNING, null, ex);
            return;
        }
        for (int i = startLine, y = startY; i <= endLine; y += textPaneFontHeight, i++) {
            String lineNumberString = Integer.toString(i);
            int lineNumberStringWidth = fontMetrics.stringWidth(lineNumberString);
            g.drawString(lineNumberString, panelWidth - lineNumberStringWidth - paddingRight, y);
        }
    }

    public Font getLineNumberFont() {
        return lineNumberFont;
    }

    public void setLineNumberFont(Font font) {
        this.lineNumberFont = font;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        update();
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        update();
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
        update();
    }

    public int getPaddingRight() {
        return paddingRight;
    }

    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
        update();
    }

    public Object getTextAntiAliasing() {
        return textAntiAliasing;
    }

    public void setTextAntiAliasing(Object textAntiAliasing) {
        this.textAntiAliasing = textAntiAliasing;
        update();
    }
}
