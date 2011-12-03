/**
 * This is part of the Java SyntaxHighlighter.
 * 
 * It is distributed under MIT license. See the file 'readme.txt' for
 * information on usage and redistribution of this file, and for a
 * DISCLAIMER OF ALL WARRANTIES.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
package syntaxhighlighter.theme;

import java.awt.Color;
import java.awt.Font;
import syntaxhighlighter.Theme;

/**
 * Default theme.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class ThemeDefault extends Theme {

    public ThemeDefault() {
        super();

        setFont(new Font("Consolas", Font.PLAIN, 12));
        setBackground(Color.white);

        setHighlightedBackground(Color.decode("0xe0e0e0"));

        setGutterText(Color.decode("0xafafaf"));
        setGutterBorderColor(Color.decode("0x6ce26c"));
        setGutterBorderWidth(3);
        setGutterTextFont(new Font("Verdana", Font.PLAIN, 11));
        setGutterTextPaddingLeft(7);
        setGutterTextPaddingRight(7);

        Style style = new Style();
        style.setColor(Color.black);
        setPlain(style);

        style = new Style();
        style.setColor(Color.decode("0x008200"));
        setComments(style);

        style = new Style();
        style.setColor(Color.blue);
        setString(style);

        style = new Style();
        style.setBold(true);
        style.setColor(Color.decode("0x006699"));
        setKeyword(style);

        style = new Style();
        style.setColor(Color.gray);
        setPreprocessor(style);

        style = new Style();
        style.setColor(Color.decode("0xaa7700"));
        setVariable(style);

        style = new Style();
        style.setColor(Color.decode("0x009900"));
        setValue(style);

        style = new Style();
        style.setColor(Color.decode("0xff1493"));
        setFunctions(style);

        style = new Style();
        style.setColor(Color.decode("0x0066cc"));
        setConstants(style);

        style = new Style();
        style.setBold(true);
        style.setColor(Color.decode("0x006699"));
        setScript(style);

        style = new Style();
        setScriptBackground(style);

        style = new Style();
        style.setColor(Color.gray);
        setColor1(style);

        style = new Style();
        style.setColor(Color.decode("0xff1493"));
        setColor2(style);

        style = new Style();
        style.setColor(Color.red);
        setColor3(style);
    }
}
