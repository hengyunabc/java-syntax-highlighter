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
 * MD Ultra theme.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class ThemeMDUltra extends Theme {

    public ThemeMDUltra() {
        super();

        // MDUltra SyntaxHighlighter theme based on Midnight Theme
        // http://www.mddev.co.uk/

        setFont(new Font("Consolas", Font.PLAIN, 12));
        setBackground(Color.decode("0x222222"));

        setHighlightedBackground(Color.decode("0x253e5a"));

        setGutterText(Color.decode("0x38566f"));
        setGutterBorderColor(Color.decode("0x435a5f"));
        setGutterBorderWidth(3);
        setGutterTextFont(new Font("Verdana", Font.PLAIN, 11));
        setGutterTextPaddingLeft(7);
        setGutterTextPaddingRight(7);

        Style style = new Style();
        style.setColor(Color.decode("0x00ff00"));
        setPlain(style);

        style = new Style();
        style.setColor(Color.decode("0x428bdd"));
        setComments(style);

        style = new Style();
        style.setColor(Color.decode("0x00ff00"));
        setString(style);

        style = new Style();
        style.setColor(Color.decode("0xaaaaff"));
        setKeyword(style);

        style = new Style();
        style.setColor(Color.decode("0x8aa6c1"));
        setPreprocessor(style);

        style = new Style();
        style.setColor(Color.decode("0x00ffff"));
        setVariable(style);

        style = new Style();
        style.setColor(Color.decode("0xf7e741"));
        setValue(style);

        style = new Style();
        style.setColor(Color.decode("0xff8000"));
        setFunctions(style);

        style = new Style();
        style.setColor(Color.yellow);
        setConstants(style);

        style = new Style();
        style.setBold(true);
        style.setColor(Color.decode("0xaaaaff"));
        setScript(style);

        style = new Style();
        setScriptBackground(style);

        style = new Style();
        style.setColor(Color.red);
        setColor1(style);

        style = new Style();
        style.setColor(Color.yellow);
        setColor2(style);

        style = new Style();
        style.setColor(Color.decode("0xffaa3e"));
        setColor3(style);
    }
}
