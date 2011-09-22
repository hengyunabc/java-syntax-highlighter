/**
 * This is part of the Java SyntaxHighlighter.
 * 
 * It is distributed under MIT license. See the file 'readme.txt' for
 * information on usage and redistribution of this file, and for a
 * DISCLAIMER OF ALL WARRANTIES.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
package syntaxhighlighter.example;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import syntaxhighlighter.Brush;
import syntaxhighlighter.Brushes.BrushXML;
import syntaxhighlighter.JTextComponentRowHeader;
import syntaxhighlighter.Parser;
import syntaxhighlighter.SyntaxHighlighter;
import syntaxhighlighter.SyntaxHighlighterPane;
import syntaxhighlighter.Theme;
import syntaxhighlighter.Themes.ThemeDjango;

/**
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class Example {

    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            Logger.getLogger(SyntaxHighlighter.class.getName()).log(Level.INFO, "Failed to set system look and feel.", ex);
        }

//        Brush brush = new BrushXML();
//        Theme theme = new ThemeDjango();
//
//        JScrollPane scrollPane = new JScrollPane();
//        scrollPane.setBorder(null);
//
//        SyntaxHighlighterPane highlighter = new SyntaxHighlighterPane();
//        theme.setTheme(highlighter);
//        highlighter.setCode((new Parser(brush, theme)).parse(SyntaxHighlighter.readFile(new File("test.html"))));
//        scrollPane.setViewportView(highlighter);
//
//        JTextComponentRowHeader _rowHeader = new JTextComponentRowHeader(scrollPane, highlighter);
//        theme.setTheme(_rowHeader);
//        scrollPane.setRowHeaderView(_rowHeader);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setContentPane(scrollPane);
        frame.setContentPane(new SyntaxHighlighter(new File("test.html"), new BrushXML(), new ThemeDjango()));
        frame.setSize(new Dimension(800, 600));
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
