/**
 * This is part of the Java SyntaxHighlighter.
 * 
 * It is distributed under MIT license. See the file 'readme.txt' for
 * information on usage and redistribution of this file, and for a
 * DISCLAIMER OF ALL WARRANTIES.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
package syntaxhighlighter.Themes;

import java.awt.Color;
import java.awt.Font;
import syntaxhighlighter.Theme;

/**
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class ThemeEmacs extends Theme {

    public ThemeEmacs() {
        super();

        // Emacs SyntaxHighlighter theme based on theme by Joshua Emmons
        // http://www.skia.net/

        setBackground(Color.black);
        setGutterText(Color.decode("0xd3d3d3"));
        setGutterBorderColor(Color.decode("0x990000"));
        setGutterBorderWidth(3);
        setGutterTextFont(new Font("Verdana", Font.PLAIN, 11));
        setGutterTextPaddingLeft(7);
        setGutterTextPaddingRight(7);

        setFont(new Font("Consolas", Font.PLAIN, 12));

        Style style = new Style();
        style.setColor(Color.decode("0xd3d3d3"));
        setPlain(style);

        style = new Style();
        style.setColor(Color.decode("0xff7d27"));
        setComments(style);

        style = new Style();
        style.setColor(Color.decode("0xff9e7b"));
        setString(style);

        style = new Style();
        style.setColor(Color.decode("0x00ffff"));
        setKeyword(style);

        style = new Style();
        style.setColor(Color.decode("0xaec4de"));
        setPreprocessor(style);

        style = new Style();
        style.setColor(Color.decode("0xffaa3e"));
        setVariable(style);

        style = new Style();
        style.setColor(Color.decode("0x009900"));
        setValue(style);

        style = new Style();
        style.setColor(Color.decode("0x81cef9"));
        setFunctions(style);

        style = new Style();
        style.setColor(Color.decode("0xff9e7b"));
        setConstants(style);

        style = new Style();
        style.setBold(true);
        style.setColor(Color.decode("0x006699"));
        setScript(style);

        style = new Style();
        setScriptBackground(style);

        style = new Style();
        style.setColor(Color.decode("0xebdb8d"));
        setColor1(style);

        style = new Style();
        style.setColor(Color.decode("0xff7d27"));
        setColor2(style);

        style = new Style();
        style.setColor(Color.decode("0xaec4de"));
        setColor3(style);
    }
}
