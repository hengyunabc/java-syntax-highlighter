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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
 * Usage example. This will just cover some of the functions. For more functions available, see the JavaDoc of SyntaxHighlighter.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class Example {

    /**
     * Read the file from the resource.
     * @param path the path to the resource
     * @return the content of the resource in string
     * @throws IOException error occured
     */
    public static String readFile(String path) throws IOException {
        int byteRead = 0;
        byte[] b = new byte[256];
        InputStream in = Example.class.getResourceAsStream(path);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        while ((byteRead = in.read(b)) > 0) {
            bout.write(b, 0, byteRead);
        }
        return new String(bout.toByteArray());
    }

    public static void main(String[] args) {
        // set look & feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            Logger.getLogger(SyntaxHighlighter.class.getName()).log(Level.INFO, "Failed to set system look and feel.", ex);
        }

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    // timer start
                    long start, end;
                    start = System.currentTimeMillis();

                    // use XML (for HTML) brush and RDark theme
                    SyntaxHighlighter highlighter = new SyntaxHighlighter(new BrushXml(), new ThemeRDark());
                    // turn HTML script on
                    highlighter.setHtmlScript(true);
                    // set HTML Script brushes
                    highlighter.setHTMLScriptBrush(Arrays.asList(new BrushCss(), new BrushJScript()));
                    // besides set, you can also adds
                    highlighter.addHTMLScriptBrush(new BrushPhp());
                    // set the line number count from 10 instead of 1
                    highlighter.setFirstLine(10);
                    // set to highlight line 13, 27, 28, 38, 42, 43 and 53
                    highlighter.setHighlightedLineList(Arrays.asList(13, 27, 28, 38, 42, 43, 53));
                    // set the content of the script, the example.html is located in the package path: /syntaxhighlighter/example/example.html
                    highlighter.setContent(readFile("/syntaxhighlighter/example/example.html"));

                    // timer end
                    end = System.currentTimeMillis();
                    System.out.println("time elapsed: " + (end - start) + "ms");

                    // initiate a frame and display
                    JFrame frame = new JFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    // SyntaxHighlighter is actually a JScrollPane
                    frame.setContentPane(highlighter);
                    frame.setLocationByPlatform(true);
                    frame.pack();
                    frame.setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
