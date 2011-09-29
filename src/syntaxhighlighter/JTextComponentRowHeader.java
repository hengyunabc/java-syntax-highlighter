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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
 * A row header panel for JScrollPane.
 * It is used with JTextComponent for line number displaying.<br />
 * <b>The usage is not limited to this syntax highlighter, it can be used on all JTextComponent.</b>
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class JTextComponentRowHeader extends JPanel {

    private static final long serialVersionUID = 1L;
    //
    private Object textAntiAliasing = RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT;
    private Color borderColor = new Color(184, 184, 184);
    private Color highlightedColor = Color.black;
    private int paddingLeft = 7;
    private int paddingRight = 2;
    private int borderWidth = 1;
    //
    protected JScrollPane scrollPane;
    protected JTextComponent textComponent;
    //
    protected Document document;
    protected DocumentListener documentListener;
    //
    protected int panelWidth;
    protected int largestRowNumber;
    protected int textComponentHeight;
    //
    private int lineNumberOffset;
    private final List<Integer> highlightedLineList;
    //
    private boolean listenToDocumentUpdate;

    public JTextComponentRowHeader(JScrollPane scrollPane, JTextComponent textComponent) {
        super();

        setFont(new Font("Verdana", Font.PLAIN, 10));
        setForeground(Color.black);
        setBackground(new Color(233, 232, 226));

        this.scrollPane = scrollPane;
        this.textComponent = textComponent;

        panelWidth = 0;
        largestRowNumber = 1;
        textComponentHeight = 0;

        lineNumberOffset = 0;
        highlightedLineList = Collections.synchronizedList(new ArrayList<Integer>());

        listenToDocumentUpdate = true;

        document = textComponent.getDocument();
        documentListener = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                handleEvent(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleEvent(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleEvent(e);
            }

            public void handleEvent(DocumentEvent e) {
                if (!listenToDocumentUpdate) {
                    return;
                }
                Document _document = e.getDocument();
                if (document == _document) {
                    checkPanelSize();
                } else {
                    _document.removeDocumentListener(this);
                }
            }
        };

        document.addDocumentListener(documentListener);

        checkPanelSize();
    }

    protected void validateTextComponentDocument() {
        Document _currentDocument = textComponent.getDocument();
        if (document != _currentDocument) {
            document.removeDocumentListener(documentListener);
            document = _currentDocument;
            _currentDocument.addDocumentListener(documentListener);
        }
    }

    protected void checkPanelSize() {
        validateTextComponentDocument();
        int _largestRowNumber = document.getDefaultRootElement().getElementCount() + lineNumberOffset;
        int _panelWidth = getFontMetrics(getFont()).stringWidth(Integer.toString(_largestRowNumber)) + paddingLeft + paddingRight;
        if (panelWidth != _panelWidth || largestRowNumber != _largestRowNumber) {
            panelWidth = _panelWidth;
            largestRowNumber = _largestRowNumber;
            updatePanelSize();
        }
    }

    protected void updatePanelSize() {
        Container parent = getParent();
        if (parent != null) {
            parent.doLayout();
            scrollPane.doLayout();
            parent.repaint();
        }
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
    }

    @Override
    public void setForeground(Color foreground) {
        super.setForeground(foreground);
    }

    @Override
    public void setBackground(Color background) {
        super.setBackground(background);
    }

    @Override
    public Dimension getPreferredSize() {
        textComponentHeight = textComponent.getPreferredSize().height;
        return new Dimension(panelWidth, textComponentHeight);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // check whether the height of this panel matches the height of the text component or not
        Dimension textComponentPreferredSize = textComponent.getPreferredSize();
        if (textComponentHeight != textComponentPreferredSize.height) {
            textComponentHeight = textComponentPreferredSize.height;
            updatePanelSize();
        }

        JViewport viewport = scrollPane.getViewport();
        Point viewPosition = viewport.getViewPosition();
        Dimension viewportSize = viewport.getSize();

        validateTextComponentDocument();
        Element defaultRootElement = document.getDefaultRootElement();


        // maybe able to get the value when font changed and cache them
        // however i'm not sure if there is any condition which will make the java.awt.FontMetrics get by getFontMetrics() from java.awt.Graphics is different from getFontMetrics() from java.awt.Component
        FontMetrics fontMetrics = g.getFontMetrics(getFont());
        int fontHeight = fontMetrics.getHeight();
        int fontAscent = fontMetrics.getAscent();
        int fontLeading = fontMetrics.getLeading();

        FontMetrics textPaneFontMetrics = g.getFontMetrics(textComponent.getFont());
        int textPaneFontHeight = textPaneFontMetrics.getHeight();


        // get the location of the document of the left top and right bottom point of the visible part
        int documentOffsetStart = textComponent.viewToModel(viewPosition);
        int documentOffsetEnd = textComponent.viewToModel(new Point(viewPosition.x + viewportSize.width, viewPosition.y + viewportSize.height));

        // convert the location of the document to the line number
        int startLine = defaultRootElement.getElementIndex(documentOffsetStart) + 1 + lineNumberOffset;
        int endLine = defaultRootElement.getElementIndex(documentOffsetEnd) + 1 + lineNumberOffset;


        // draw right border
        g.setColor(borderColor);
        g.fillRect(panelWidth - borderWidth, viewPosition.y, borderWidth, viewportSize.height);

        // draw line number
        int startY = -1, baselineOffset = -1;
        try {
            startY = textComponent.modelToView(documentOffsetStart).y;
            baselineOffset = (textPaneFontHeight / 2) + fontAscent - (fontHeight / 2) + fontLeading;
        } catch (BadLocationException ex) {
            Logger.getLogger(JTextComponentRowHeader.class.getName()).log(Level.WARNING, null, ex);
            return;
        }

        // text anti-aliasing
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, textAntiAliasing);
        // preserve the foreground color (for recover the color after highlighing the line)
        Color foregroundColor = getForeground();

        g.setColor(foregroundColor);
        g.setFont(getFont());

        for (int i = startLine, y = startY + baselineOffset; i <= endLine; y += textPaneFontHeight, i++) {
            boolean highlighted = false;
            if (highlightedLineList.indexOf((Integer) i) != -1) {
                // highlight this line
                g.setColor(borderColor);
                g.fillRect(0, y - baselineOffset, panelWidth - borderWidth, textPaneFontHeight);
                g.setColor(highlightedColor);
                highlighted = true;
            }

            // draw the line number
            String lineNumberString = Integer.toString(i);
            int lineNumberStringWidth = fontMetrics.stringWidth(lineNumberString);
            g.drawString(lineNumberString, panelWidth - lineNumberStringWidth - paddingRight, y);

            // restore the line number text color
            if (highlighted) {
                g.setColor(foregroundColor);
            }
        }
    }

    public Object getTextAntiAliasing() {
        return textAntiAliasing;
    }

    public void setTextAntiAliasing(Object textAntiAliasing) {
        this.textAntiAliasing = textAntiAliasing;
        repaint();
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }

    public Color getHighlightedColor() {
        return highlightedColor;
    }

    public void setHighlightedColor(Color highlightedColor) {
        this.highlightedColor = highlightedColor;
        repaint();
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
        checkPanelSize();
    }

    public int getPaddingRight() {
        return paddingRight;
    }

    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
        checkPanelSize();
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        repaint();
    }

    public int getLineNumberOffset() {
        return lineNumberOffset;
    }

    public void setLineNumberOffset(int offset) {
        lineNumberOffset = Math.max(lineNumberOffset, offset);
        checkPanelSize();
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

    public boolean isListenToDocumentUpdate() {
        return listenToDocumentUpdate;
    }

    public void setListenToDocumentUpdate(boolean listenToDocumentUpdate) {
        this.listenToDocumentUpdate = listenToDocumentUpdate;
    }
}
