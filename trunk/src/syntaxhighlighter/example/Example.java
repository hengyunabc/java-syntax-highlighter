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
 * Usage example.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class Example {

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
                    highlighter.setHTMLScriptBrush(Arrays.asList(new BrushCss(), new BrushJScript()));
                    highlighter.addHTMLScriptBrush(new BrushPhp());
                    highlighter.setContent(readFile("/syntaxhighlighter/example/example.html"));
                    highlighter.setFirstLine(10);
                    highlighter.setHighlightedLineList(Arrays.asList(13, 27, 28, 38, 42, 43, 53));

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
