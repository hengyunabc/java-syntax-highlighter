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
 * Fade to Grey theme.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class ThemeFadeToGrey extends Theme {

    public ThemeFadeToGrey() {
        super();

        // Fade to Grey SyntaxHighlighter theme based on theme by Brasten Sager
        // :http//www.ibrasten.com/

        setFont(new Font("Consolas", Font.PLAIN, 12));
        setBackground(Color.decode("0x121212"));

        setHighlightedBackground(Color.decode("0x2C2C29"));

        setGutterText(Color.decode("0xafafaf"));
        setGutterBorderColor(Color.decode("0x3185b9"));
        setGutterBorderWidth(3);
        setGutterTextFont(new Font("Verdana", Font.PLAIN, 11));
        setGutterTextPaddingLeft(7);
        setGutterTextPaddingRight(7);

        Style style = new Style();
        style.setColor(Color.white);
        setPlain(style);

        style = new Style();
        style.setColor(Color.decode("0x696854"));
        setComments(style);

        style = new Style();
        style.setColor(Color.decode("0xe3e658"));
        setString(style);

        style = new Style();
        style.setColor(Color.decode("0xd01d33"));
        setKeyword(style);

        style = new Style();
        style.setColor(Color.decode("0x435a5f"));
        setPreprocessor(style);

        style = new Style();
        style.setColor(Color.decode("0x898989"));
        setVariable(style);

        style = new Style();
        style.setColor(Color.decode("0x009900"));
        setValue(style);

        style = new Style();
        style.setBold(true);
        style.setColor(Color.decode("0xaaaaaa"));
        setFunctions(style);

        style = new Style();
        style.setColor(Color.decode("0x96daff"));
        setConstants(style);

        style = new Style();
        style.setBold(true);
        style.setColor(Color.decode("0xd01d33"));
        setScript(style);

        style = new Style();
        setScriptBackground(style);

        style = new Style();
        style.setColor(Color.decode("0xffc074"));
        setColor1(style);

        style = new Style();
        style.setColor(Color.decode("0x4a8cdb"));
        setColor2(style);

        style = new Style();
        style.setColor(Color.decode("0x96daff"));
        setColor3(style);
    }
}
