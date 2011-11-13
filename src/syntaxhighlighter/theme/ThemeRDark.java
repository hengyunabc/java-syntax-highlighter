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
 * RDark theme.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class ThemeRDark extends Theme {

    public ThemeRDark() {
        super();

        // RDark SyntaxHighlighter theme based on theme by Radu Dineiu
        // http://www.vim.org/scripts/script.php?script_id=1732

        setFont(new Font("Consolas", Font.PLAIN, 12));
        setBackground(Color.decode("0x1b2426"));

        setHighlightedBackground(Color.decode("0x323E41"));

        setGutterText(Color.decode("0xafafaf"));
        setGutterBorderColor(Color.decode("0x435a5f"));
        setGutterBorderWidth(3);
        setGutterTextFont(new Font("Verdana", Font.PLAIN, 11));
        setGutterTextPaddingLeft(7);
        setGutterTextPaddingRight(7);

        Style style = new Style();
        style.setColor(Color.decode("0xb9bdb6"));
        setPlain(style);

        style = new Style();
        style.setColor(Color.decode("0x878a85"));
        setComments(style);

        style = new Style();
        style.setColor(Color.decode("0x5ce638"));
        setString(style);

        style = new Style();
        style.setColor(Color.decode("0x5ba1cf"));
        setKeyword(style);

        style = new Style();
        style.setColor(Color.decode("0x435a5f"));
        setPreprocessor(style);

        style = new Style();
        style.setColor(Color.decode("0xffaa3e"));
        setVariable(style);

        style = new Style();
        style.setColor(Color.decode("0x009900"));
        setValue(style);

        style = new Style();
        style.setColor(Color.decode("0xffaa3e"));
        setFunctions(style);

        style = new Style();
        style.setColor(Color.decode("0xe0e8ff"));
        setConstants(style);

        style = new Style();
        style.setBold(true);
        style.setColor(Color.decode("0x5ba1cf"));
        setScript(style);

        style = new Style();
        setScriptBackground(style);

        style = new Style();
        style.setColor(Color.decode("0xe0e8ff"));
        setColor1(style);

        style = new Style();
        style.setColor(Color.white);
        setColor2(style);

        style = new Style();
        style.setColor(Color.decode("0xffaa3e"));
        setColor3(style);
    }
}
