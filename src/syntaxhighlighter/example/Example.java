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

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import syntaxhighlighter.Brushes.BrushCss;
import syntaxhighlighter.Brushes.BrushJScript;
import syntaxhighlighter.Brushes.BrushPhp;
import syntaxhighlighter.Brushes.BrushXml;
import syntaxhighlighter.SyntaxHighlighter;
import syntaxhighlighter.Themes.ThemeRDark;

/**
 * Usage example.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class Example {

    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            Logger.getLogger(SyntaxHighlighter.class.getName()).log(Level.INFO, "Failed to set system look and feel.", ex);
        }

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    JFrame frame = new JFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    long start, end;
                    start = System.currentTimeMillis();

                    SyntaxHighlighter highlighter = new SyntaxHighlighter(new BrushXml(), new ThemeRDark());
                    highlighter.setHtmlScript(true);
                    highlighter.addHTMLScriptBrush(new BrushCss());
                    highlighter.addHTMLScriptBrush(new BrushJScript());
                    highlighter.addHTMLScriptBrush(new BrushPhp());
                    File file = new File(Example.class.getResource("/syntaxhighlighter/example/example.html").toURI());
                    highlighter.setContent(file);
                    highlighter.setFirstLine(10);
                    highlighter.setHighlightedLineList(Arrays.asList(new Integer[]{13, 27, 28, 38, 42, 43, 53}));

                    end = System.currentTimeMillis();
                    System.out.println((end - start));

                    frame.setContentPane(highlighter);
                    frame.pack();
//                    frame.setSize(new Dimension(800, 600));
                    frame.setLocationByPlatform(true);
                    frame.setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
