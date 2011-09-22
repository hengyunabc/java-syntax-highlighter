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
public class ThemeMidnight extends Theme {

    public ThemeMidnight() {
        super();

        // Midnight SyntaxHighlighter theme based on theme by J.D. Myers
        // http://webdesign.lsnjd.com/

        setBackground(Color.decode("0x0f192a"));
        setGutterText(Color.decode("0xafafaf"));
        setGutterBorderColor(Color.decode("0x435a5f"));
        setGutterBorderWidth(3);
        setGutterTextFont(new Font("Verdana", Font.PLAIN, 11));
        setGutterTextPaddingLeft(7);
        setGutterTextPaddingRight(7);

        setFont(new Font("Consolas", Font.PLAIN, 12));

        Style style = new Style();
        style.setColor(Color.decode("0xd1edff"));
        setPlain(style);

        style = new Style();
        style.setColor(Color.decode("0x428bdd"));
        setComments(style);

        style = new Style();
        style.setColor(Color.decode("0x1dc116"));
        setString(style);

        style = new Style();
        style.setColor(Color.decode("0xb43d3d"));
        setKeyword(style);

        style = new Style();
        style.setColor(Color.decode("0x8aa6c1"));
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
        style.setColor(Color.decode("0x006699"));
        setScript(style);

        style = new Style();
        setScriptBackground(style);

        style = new Style();
        style.setColor(Color.decode("0xf8bb00"));
        setColor1(style);

        style = new Style();
        style.setColor(Color.white);
        setColor2(style);

        style = new Style();
        style.setColor(Color.decode("0x0ffaa3e"));
        setColor3(style);
    }
}
