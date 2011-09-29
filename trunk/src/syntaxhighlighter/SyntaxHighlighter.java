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
 * The syntax highlighter.
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

    /**
     * Constructor.
     * @param brush the brush for the syntax highlighter
     * @param theme the theme for the syntax highlighter
     */
    public SyntaxHighlighter(Brush brush, Theme theme) {
        this(brush, theme, new SyntaxHighlighterPane());
    }

    /**
     * Constructor. It is used when SyntaxHighlighterPane is extended.
     * @param brush the brush for the syntax highlighter
     * @param theme the theme for the syntax highlighter
     * @param highlighterPane the script text pane of the syntax highlighter
     */
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

    /**
     * Re-render the script text pane. Call it when some setting that affect the rendering was made.
     */
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

    /**
     * Get the SyntaxHighlighterPane.
     * @return the SyntaxHighlighterPane
     */
    public SyntaxHighlighterPane getHighlighter() {
        return highlighter;
    }

    /**
     * Get the JTextComponentRowHeader.
     * @return the JTextComponentRowHeader
     */
    public JTextComponentRowHeader getHighlighterRowHeader() {
        return highlighterRowHeader;
    }

    /**
     * Get the brush
     * @return the brush
     */
    public Brush getBrush() {
        return brush;
    }

    /**
     * Set the brush.
     * @param brush the brush
     */
    public void setBrush(Brush brush) {
        if (!this.brush.equals(brush)) {
            this.brush = brush;
            render();
        }
    }

    /**
     * Get current theme.
     * @return the current theme
     */
    public Theme getTheme() {
        return theme;
    }

    /**
     * Set the theme.
     * @param theme the theme
     */
    public void setTheme(Theme theme) {
        if (!this.theme.equals(theme)) {
            this.theme = theme;
            highlighter.setTheme(theme);
            theme.setTheme(highlighterRowHeader);
        }
    }

    /**
     * Get the list of HTML Script brushes.
     * See also {@link #setHtmlScript(boolean)}.
     * @return a copy of the list
     */
    public List<Brush> getHTMLScriptBrushList() {
        List<Brush> returnList;
        synchronized (htmlScriptBrushList) {
            returnList = new ArrayList<Brush>(htmlScriptBrushList);
        }
        return returnList;
    }

    /**
     * Set HTML Script brushes. Note that this will clear all previous recorded HTML Script brushes.
     * See also {@link #setHtmlScript(boolean)}.
     * @param htmlScriptBrushList the list that contain the brushes
     */
    public void setHTMLScriptBrush(List<Brush> htmlScriptBrushList) {
        synchronized (this.htmlScriptBrushList) {
            this.htmlScriptBrushList.clear();
            this.htmlScriptBrushList.addAll(htmlScriptBrushList);
        }
        render();
    }

    /**
     * Add HTML Script brushes.
     * See also {@link #setHtmlScript(boolean)}.
     * @param brush the brush to add
     */
    public void addHTMLScriptBrush(Brush brush) {
        htmlScriptBrushList.add(brush);
        render();
    }

    /**
     * Get the setting of the HTML Script option.
     * See also {@link #setHTMLScriptBrush(java.util.List)}.
     * @return true if it is turned on, false if it is turned off
     */
    public boolean isHtmlScript() {
        return htmlScript;
    }

    /**
     * Allows you to highlight a mixture of HTML/XML code and a script which is very common in web development.
     * See also {@link #setHTMLScriptBrush(java.util.List)} to set the brushes.
     * Default is turn off.
     * @param htmlScript true to turn on, false to turn off
     */
    public void setHtmlScript(boolean htmlScript) {
        if (this.htmlScript != htmlScript) {
            this.htmlScript = htmlScript;
            render();
        }
    }

    /**
     * Set the line number of the first line. E.g. if set 10, the line number will start from 10 instead of 1.
     * @param firstLine the line number of the first line
     */
    public void setFirstLine(int firstLine) {
        highlighterRowHeader.setLineNumberOffset(firstLine - 1);
        highlighter.setLineNumberOffset(firstLine - 1);
    }

    /**
     * Get the list of highlighted lines.
     * @return a copy of the list
     */
    public List<Integer> getHighlightedLineList() {
        return highlighter.getHighlightedLineList();
    }

    /**
     * Set highlighted lines. Note that this will clear all previous recorded highlighted lines.
     * @param highlightedLineList the list that contain the highlighted lines
     */
    public void setHighlightedLineList(List<Integer> highlightedLineList) {
        highlighterRowHeader.setHighlightedLineList(highlightedLineList);
        highlighter.setHighlightedLineList(highlightedLineList);
    }

    /**
     * Add highlighted line.
     * @param lineNumber the line number to highlight
     */
    public void addHighlightedLine(int lineNumber) {
        highlighterRowHeader.addHighlightedLine(lineNumber);
        highlighter.addHighlightedLine(lineNumber);
    }

    /**
     * Set the visibility of the gutter (line number panel on the left).
     * @param visible true to make visibly, false to hide it
     */
    public void setGutterVisible(boolean visible) {
        if (visible) {
            setRowHeaderView(highlighterRowHeader);
        } else {
            setRowHeaderView(null);
        }
    }

    /**
     * Get the status of the mouse-over-highlight effect.
     * @return true if turned on, false if turned off
     */
    public boolean isHighlightWhenMouseOver() {
        return highlighter.isHighlightWhenMouseOver();
    }

    /**
     * Set turn on the mouse-over-highlight effect or not.
     * If set true, there will be a highlight line effect on the line it point (on the script text panel only, not also the line number panel).
     * @param highlightWhenMouseOver true to turn on, false to turn off
     */
    public void setHighlightWhenMouseOver(boolean highlightWhenMouseOver) {
        highlighter.setHighlightWhenMouseOver(highlightWhenMouseOver);
    }

    /**
     * Set the content of the syntax highlighter. Better set it last after setting all other settings.
     * @param file the file to read 
     */
    public void setContent(File file) throws IOException {
        setContent(readFile(file));
    }

    /**
     * Set the content of the syntax highlighter. Better set it last after setting all other settings.
     * @param content 
     */
    public void setContent(String content) {
        this.content = content;
        highlighter.setContent(content);
        render();
    }

    /**
     * Get the string content of a file.
     * @param file the file to retrieve the content from
     * @return the string content
     * @throws IOException error occured, either it is not a valid file or failed to read the file
     */
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
