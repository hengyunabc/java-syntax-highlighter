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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;

/**
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class SyntaxHighlighter extends JScrollPane {

    private static final long serialVersionUID = 1L;
    //
    private SyntaxHighlighterPane highlighter;
    private JTextComponentRowHeader highlighterRowHeader;
    //
    private Brush brush;
    private Theme theme;
    private boolean htmlScript;
    private final List<Brush> htmlScriptBrushList;
    //
    protected String content;

    public SyntaxHighlighter(Brush brush, Theme theme) {
        this(brush, theme, new SyntaxHighlighterPane());
    }

    public SyntaxHighlighter(Brush brush, Theme theme, SyntaxHighlighterPane highlighterPane) {
        super();

        this.brush = brush;
        this.theme = theme;
        htmlScript = false;
        htmlScriptBrushList = new ArrayList<Brush>();

        setBorder(null);

        highlighter = highlighterPane;
        highlighter.setTheme(theme);
        setViewportView(highlighter);

        highlighterRowHeader = new JTextComponentRowHeader(this, highlighter);
        theme.setTheme(highlighterRowHeader);
        setRowHeaderView(highlighterRowHeader);
    }

    protected void render() {
        if (content != null) {
            Parser parser = new Parser();
            if (htmlScript) {
                parser.setHTMLScriptBrushList(htmlScriptBrushList);
            }
            highlighterRowHeader.setListenToDocumentUpdate(false);
            highlighter.setStyle(parser.parse(brush, htmlScript, content.toCharArray(), 0, content.length()));
            highlighterRowHeader.setListenToDocumentUpdate(true);
            highlighterRowHeader.checkPanelSize();
        }
    }

    public SyntaxHighlighterPane getHighlighter() {
        return highlighter;
    }

    public JTextComponentRowHeader getHighlighterRowHeader() {
        return highlighterRowHeader;
    }

    public Brush getBrush() {
        return brush;
    }

    public void setBrush(Brush brush) {
        if (!this.brush.equals(brush)) {
            this.brush = brush;
            render();
        }
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        if (!this.theme.equals(theme)) {
            this.theme = theme;
            highlighter.setTheme(theme);
            theme.setTheme(highlighterRowHeader);
        }
    }

    public List<Brush> getHTMLScriptBrushList() {
        List<Brush> returnList;
        synchronized (htmlScriptBrushList) {
            returnList = new ArrayList<Brush>(htmlScriptBrushList);
        }
        return returnList;
    }

    public void setHTMLScriptBrush(List<Brush> htmlScriptBrushList) {
        synchronized (this.htmlScriptBrushList) {
            this.htmlScriptBrushList.clear();
            this.htmlScriptBrushList.addAll(htmlScriptBrushList);
        }
        render();
    }

    public void addHTMLScriptBrush(Brush brush) {
        htmlScriptBrushList.add(brush);
        render();
    }

    public boolean isHtmlScript() {
        return htmlScript;
    }

    public void setHtmlScript(boolean htmlScript) {
        if (this.htmlScript != htmlScript) {
            this.htmlScript = htmlScript;
            render();
        }
    }

    public void setFirstLine(int firstLine) {
        highlighterRowHeader.setLineNumberOffset(firstLine - 1);
        highlighter.setLineNumberOffset(firstLine - 1);
    }

    public List<Integer> getHighlightedLineList() {
        return highlighter.getHighlightedLineList();
    }

    public void setHighlightedLineList(List<Integer> highlightedLineList) {
        highlighterRowHeader.setHighlightedLineList(highlightedLineList);
        highlighter.setHighlightedLineList(highlightedLineList);
    }

    public void addHighlightedLine(int lineNumber) {
        highlighterRowHeader.addHighlightedLine(lineNumber);
        highlighter.addHighlightedLine(lineNumber);
    }

    public void setGutterVisible(boolean visible) {
        if (visible) {
            setRowHeaderView(highlighterRowHeader);
        } else {
            setRowHeaderView(null);
        }
    }

    public boolean isHighlightWhenMouseOver() {
        return highlighter.isHighlightWhenMouseOver();
    }

    public void setHighlightWhenMouseOver(boolean highlightWhenMouseOver) {
        highlighter.setHighlightWhenMouseOver(highlightWhenMouseOver);
    }

    public void setContent(File file) throws IOException {
        setContent(readFile(file));
    }

    public void setContent(String content) {
        this.content = content;
        highlighter.setContent(content);
        render();
    }

    protected static String readFile(File file) throws IOException {
        if (!file.isFile()) {
            throw new IOException();
        }

        byte[] buffer = new byte[(int) file.length()];

        FileInputStream fileIn = new FileInputStream(file);
        fileIn.read(buffer);
        fileIn.close();

        return new String(buffer);
    }
}
