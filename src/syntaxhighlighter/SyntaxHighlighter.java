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
import javax.swing.JScrollPane;

/**
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class SyntaxHighlighter extends JScrollPane {

    private static final long serialVersionUID = 1L;

    public SyntaxHighlighter(File file, Brush brush, Theme theme) throws IOException {
        this(readFile(file), brush, theme);
    }

    public SyntaxHighlighter(String content, Brush brush, Theme theme) {
        super();

        setBorder(null);

        SyntaxHighlighterPane highlighter = new SyntaxHighlighterPane();
        theme.setTheme(highlighter);
        highlighter.setCode((new Parser(brush, theme)).parse(content));
        setViewportView(highlighter);

        JTextComponentRowHeader _rowHeader = new JTextComponentRowHeader(this, highlighter);
        theme.setTheme(_rowHeader);
        setRowHeaderView(_rowHeader);
    }

    public static String readFile(File file) throws IOException {
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
