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
 * Django theme.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class ThemeDjango extends Theme {

    public ThemeDjango() {
        super();

        setFont(new Font("Consolas", Font.PLAIN, 12));
        setBackground(Color.decode("0x0a2b1d"));

        setHighlightedBackground(Color.decode("0x233729"));

        setGutterText(Color.decode("0x497958"));
        setGutterBorderColor(Color.decode("0x41a83e"));
        setGutterBorderWidth(3);
        setGutterTextFont(new Font("Verdana", Font.PLAIN, 11));
        setGutterTextPaddingLeft(7);
        setGutterTextPaddingRight(7);

        Style style = new Style();
        style.setColor(Color.decode("0xf8f8f8"));
        setPlain(style);

        style = new Style();
        style.setItalic(true);
        style.setColor(Color.decode("0x336442"));
        setComments(style);

        style = new Style();
        style.setColor(Color.decode("0x9df39f"));
        setString(style);

        style = new Style();
        style.setBold(true);
        style.setColor(Color.decode("0x96dd3b"));
        setKeyword(style);

        style = new Style();
        style.setColor(Color.decode("0x91bb9e"));
        setPreprocessor(style);

        style = new Style();
        style.setColor(Color.decode("0xffaa3e"));
        setVariable(style);

        style = new Style();
        style.setColor(Color.decode("0xf7e741"));
        setValue(style);

        style = new Style();
        style.setColor(Color.decode("0xffaa3e"));
        setFunctions(style);

        style = new Style();
        style.setColor(Color.decode("0xe0e8ff"));
        setConstants(style);

        style = new Style();
        style.setBold(true);
        style.setColor(Color.decode("0x96dd3b"));
        setScript(style);

        style = new Style();
        setScriptBackground(style);

        style = new Style();
        style.setColor(Color.decode("0xeb939a"));
        setColor1(style);

        style = new Style();
        style.setColor(Color.decode("0x91bb9e"));
        setColor2(style);

        style = new Style();
        style.setColor(Color.decode("0xedef7d"));
        setColor3(style);
    }
}
