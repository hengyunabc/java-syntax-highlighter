/**
 * This is part of the Java SyntaxHighlighter.
 * 
 * It is distributed under MIT license. See the file 'readme.txt' for
 * information on usage and redistribution of this file, and for a
 * DISCLAIMER OF ALL WARRANTIES.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
package syntaxhighlighter;

import java.awt.Color;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * Theme for the syntax highlighter.
 * <p>To make a new theme, either extending this class of initiate this class and set the parameter by the setter.
 * For the default value, please refer to the constructor.</p>
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class Theme {

    /**
     * The font of the script text.
     */
    protected Font font;
    /**
     * The background color of the script text area.
     */
    protected Color background;
    /**
     * The background color of the highlighted line of script text.
     */
    protected Color highlightedBackground;
    /**
     * Gutter (line number column on the left)
     */
    /**
     * The color of the gutter text.
     */
    protected Color gutterText;
    /**
     * The color of the border that joint the gutter and the script text panel.
     */
    protected Color gutterBorderColor;
    /**
     * The width of the border that joint the gutter and the script text panel.
     */
    protected int gutterBorderWidth;
    /**
     * The font of the gutter text.
     */
    protected Font gutterTextFont;
    /**
     * The minimum padding from 'the leftmost of the line number text' to 'the left margin'.
     */
    protected int gutterTextPaddingLeft;
    /**
     * The minimum padding from 'the rightmost of the line number text' to 'the right margin' (not to the gutter border).
     */
    protected int gutterTextPaddingRight;
    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    protected Style plain;
    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    protected Style comments;
    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    protected Style string;
    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    protected Style keyword;
    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    protected Style preprocessor;
    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    protected Style variable;
    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    protected Style value;
    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    protected Style functions;
    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    protected Style constants;
    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    protected Style script;
    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    protected Style scriptBackground;
    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    protected Style color1;
    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    protected Style color2;
    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    protected Style color3;

    /**
     * Constructor.<br />
     * <p>
     * <b>Default value:</b><br />
     * <ul>
     * <li>font: Consolas 12pt</li>
     * <li>background: white</li>
     * <li>gutter text: black</li>
     * <li>gutter border: R: 184, G: 184, B: 184</li>
     * <li>gutter border width: 3px</li>
     * <li>gutter text font: Consolas 12pt</li>
     * <li>gutter text padding-left: 7px</li>
     * <li>gutter text padding-right: 7px</li>
     * <li>plain, comments, string, keyword, preprocessor, variable, value, functions, constants, script, scriptBackground, color1, color2, color3: no style set</li>
     * </ul>
     * </p>
     */
    public Theme() {
        font = new Font("Consolas", Font.PLAIN, 12);
        background = Color.white;

        highlightedBackground = Color.gray;

        gutterText = Color.black;
        gutterBorderColor = new Color(184, 184, 184);
        gutterBorderWidth = 3;
        gutterTextFont = new Font("Consolas", Font.PLAIN, 12);
        gutterTextPaddingLeft = 7;
        gutterTextPaddingRight = 7;

        plain = new Style();
        comments = new Style();
        string = new Style();
        keyword = new Style();
        preprocessor = new Style();
        variable = new Style();
        value = new Style();
        functions = new Style();
        constants = new Style();
        script = new Style();
        scriptBackground = new Style();
        color1 = new Style();
        color2 = new Style();
        color3 = new Style();
    }

    /**
     * Apply the theme to the row header panel.
     * @param rowHeader the row header to apply theme on
     */
    public void setTheme(JTextComponentRowHeader rowHeader) {
        rowHeader.setBackground(background);
        rowHeader.setHighlightedColor(background);

        rowHeader.setForeground(gutterText);
        rowHeader.setBorderColor(gutterBorderColor);
        rowHeader.setBorderWidth(gutterBorderWidth);
        rowHeader.setFont(gutterTextFont);
        rowHeader.setPaddingLeft(gutterTextPaddingLeft);
        rowHeader.setPaddingRight(gutterTextPaddingRight);
    }

    /**
     * Get the {@link syntaxhighlighter.Theme.Style} by keyword.
     * @param key the keyword, possible values: plain, comments, string, keyword, preprocessor, variable, value, functions, constants, script, scriptBackground, color1, color2 or color3
     * @return the {@link syntaxhighlighter.Theme.Style} related to the {@code key}; if the style related to the <code>key</code> not exist, the style of 'plain' will return.
     */
    public Style getStyle(String key) {
        if (key.equals("plain")) {
            return plain;
        } else if (key.equals("comments")) {
            return comments;
        } else if (key.equals("string")) {
            return string;
        } else if (key.equals("keyword")) {
            return keyword;
        } else if (key.equals("preprocessor")) {
            return preprocessor;
        } else if (key.equals("variable")) {
            return variable;
        } else if (key.equals("value")) {
            return value;
        } else if (key.equals("functions")) {
            return functions;
        } else if (key.equals("constants")) {
            return constants;
        } else if (key.equals("script")) {
            return script;
        } else if (key.equals("scriptBackground")) {
            return scriptBackground;
        } else if (key.equals("color1")) {
            return color1;
        } else if (key.equals("color2")) {
            return color2;
        } else if (key.equals("color3")) {
            return color3;
        } else {
            // key not exist
            return plain;
        }
    }

    /**
     * The font of the script text.
     */
    public Font getFont() {
        return font;
    }

    /**
     * The font of the script text.
     */
    public void setFont(Font font) {
        this.font = font;
    }

    /**
     * The background color of the script text area.
     */
    public Color getBackground() {
        return background;
    }

    /**
     * The background color of the script text area.
     */
    public void setBackground(Color background) {
        this.background = background;
    }

    /**
     * The background color of the highlighted line of script text.
     */
    public Color getHighlightedBackground() {
        return highlightedBackground;
    }

    /**
     * The background color of the highlighted line of script text.
     */
    public void setHighlightedBackground(Color highlightedBackground) {
        this.highlightedBackground = highlightedBackground;
    }

    /**
     * The color of the gutter text.
     */
    public Color getGutterText() {
        return gutterText;
    }

    /**
     * The color of the gutter text.
     */
    public void setGutterText(Color gutterText) {
        this.gutterText = gutterText;
    }

    /**
     * The color of the border that joint the gutter and the script text panel.
     */
    public Color getGutterBorderColor() {
        return gutterBorderColor;
    }

    /**
     * The color of the border that joint the gutter and the script text panel.
     */
    public void setGutterBorderColor(Color gutterBorderColor) {
        this.gutterBorderColor = gutterBorderColor;
    }

    /**
     * The width of the border that joint the gutter and the script text panel.
     */
    public int getGutterBorderWidth() {
        return gutterBorderWidth;
    }

    /**
     * The width of the border that joint the gutter and the script text panel.
     */
    public void setGutterBorderWidth(int gutterBorderWidth) {
        this.gutterBorderWidth = gutterBorderWidth;
    }

    /**
     * The font of the gutter text.
     */
    public Font getGutterTextFont() {
        return gutterTextFont;
    }

    /**
     * The font of the gutter text.
     */
    public void setGutterTextFont(Font gutterTextFont) {
        this.gutterTextFont = gutterTextFont;
    }

    /**
     * The minimum padding from 'the leftmost of the line number text' to 'the left margin'.
     */
    public int getGutterTextPaddingLeft() {
        return gutterTextPaddingLeft;
    }

    /**
     * The minimum padding from 'the leftmost of the line number text' to 'the left margin'.
     */
    public void setGutterTextPaddingLeft(int gutterTextPaddingLeft) {
        this.gutterTextPaddingLeft = gutterTextPaddingLeft;
    }

    /**
     * The minimum padding from 'the rightmost of the line number text' to 'the right margin' (not to the gutter border).
     */
    public int getGutterTextPaddingRight() {
        return gutterTextPaddingRight;
    }

    /**
     * The minimum padding from 'the rightmost of the line number text' to 'the right margin' (not to the gutter border).
     */
    public void setGutterTextPaddingRight(int gutterTextPaddingRight) {
        this.gutterTextPaddingRight = gutterTextPaddingRight;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public Style getPlain() {
        return plain;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public void setPlain(Style plain) {
        this.plain = plain;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public Style getComments() {
        return comments;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public void setComments(Style comments) {
        this.comments = comments;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public Style getString() {
        return string;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public void setString(Style string) {
        this.string = string;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public Style getKeyword() {
        return keyword;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public void setKeyword(Style keyword) {
        this.keyword = keyword;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public Style getPreprocessor() {
        return preprocessor;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public void setPreprocessor(Style preprocessor) {
        this.preprocessor = preprocessor;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public Style getVariable() {
        return variable;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public void setVariable(Style variable) {
        this.variable = variable;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public Style getValue() {
        return value;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public void setValue(Style value) {
        this.value = value;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public Style getFunctions() {
        return functions;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public void setFunctions(Style functions) {
        this.functions = functions;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public Style getConstants() {
        return constants;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public void setConstants(Style constants) {
        this.constants = constants;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public Style getScript() {
        return script;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public void setScript(Style script) {
        this.script = script;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public Style getScriptBackground() {
        return scriptBackground;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public void setScriptBackground(Style scriptBackground) {
        this.scriptBackground = scriptBackground;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public Style getColor1() {
        return color1;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public void setColor1(Style color1) {
        this.color1 = color1;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public Style getColor2() {
        return color2;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public void setColor2(Style color2) {
        this.color2 = color2;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public Style getColor3() {
        return color3;
    }

    /**
     * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
     */
    public void setColor3(Style color3) {
        this.color3 = color3;
    }

    @Override
    public Theme clone() {
        Theme object = null;
        try {
            object = (Theme) super.clone();
            object.font = font;
            object.background = background;
            object.highlightedBackground = highlightedBackground;
            object.gutterText = gutterText;
            object.gutterBorderColor = gutterBorderColor;
            object.gutterBorderWidth = gutterBorderWidth;
            object.gutterTextFont = gutterTextFont;
            object.gutterTextPaddingLeft = gutterTextPaddingLeft;
            object.gutterTextPaddingRight = gutterTextPaddingRight;
            object.plain = plain.clone();
            object.comments = comments.clone();
            object.string = string.clone();
            object.keyword = keyword.clone();
            object.preprocessor = preprocessor.clone();
            object.variable = variable.clone();
            object.value = value.clone();
            object.functions = functions.clone();
            object.constants = constants.clone();
            object.script = script.clone();
            object.scriptBackground = scriptBackground.clone();
            object.color1 = color1.clone();
            object.color2 = color2.clone();
            object.color3 = color3.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Theme.class.getName()).log(Level.WARNING, null, ex);
        }
        return object;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(getClass().getName());
        sb.append(": ");
        sb.append("font: ").append(getFont());
        sb.append(", ");
        sb.append("background: ").append(getBackground());
        sb.append(", ");
        sb.append("highlightedBackground: ").append(getHighlightedBackground());
        sb.append(", ");
        sb.append("gutterText: ").append(getGutterText());
        sb.append(", ");
        sb.append("gutterBorderColor: ").append(getGutterBorderColor());
        sb.append(", ");
        sb.append("gutterBorderWidth: ").append(getGutterBorderWidth());
        sb.append(", ");
        sb.append("gutterTextFont: ").append(getGutterTextFont());
        sb.append(", ");
        sb.append("gutterTextPaddingLeft: ").append(getGutterTextPaddingLeft());
        sb.append(", ");
        sb.append("gutterTextPaddingRight: ").append(getGutterTextPaddingRight());
        sb.append(", ");
        sb.append("plain: ").append(getPlain());
        sb.append(", ");
        sb.append("comments: ").append(getComments());
        sb.append(", ");
        sb.append("string: ").append(getString());
        sb.append(", ");
        sb.append("keyword: ").append(getKeyword());
        sb.append(", ");
        sb.append("preprocessor: ").append(getPreprocessor());
        sb.append(", ");
        sb.append("variable: ").append(getVariable());
        sb.append(", ");
        sb.append("value: ").append(getValue());
        sb.append(", ");
        sb.append("functions: ").append(getFunctions());
        sb.append(", ");
        sb.append("constants: ").append(getConstants());
        sb.append(", ");
        sb.append("script: ").append(getScript());
        sb.append(", ");
        sb.append("scriptBackground: ").append(getScriptBackground());
        sb.append(", ");
        sb.append("color1: ").append(getColor1());
        sb.append(", ");
        sb.append("color2: ").append(getColor2());
        sb.append(", ");
        sb.append("color3: ").append(getColor3());

        return sb.toString();
    }

    /**
     * The style used by {@link syntaxhighlighter.Theme} as those of CSS styles.
     */
    public static class Style {

        protected boolean bold;
        protected Color color;
        /**
         * The background color, null means no background color is set.
         */
        protected Color background;
        protected boolean underline;
        protected boolean italic;

        /**
         * Constructor.
         * <p>
         * <b>Default values:</b><br />
         * <ul>
         * <li>bold: false;</li>
         * <li>color: black;</li>
         * <li>background: null;</li>
         * <li>underline: false;</li>
         * <li>italic: false;</li>
         * </ul>
         * </p>
         */
        public Style() {
            bold = false;
            color = Color.black;
            background = null;
            underline = false;
            italic = false;
        }

        /**
         * Apply the style to the AttributeSet.
         * Note that the AttributeSet should only be set by this function or some unexpected style may appear.
         * @param attributeSet the AttributeSet to set the style on
         */
        public void setAttributeSet(SimpleAttributeSet attributeSet) {
            StyleConstants.setBold(attributeSet, bold);
            StyleConstants.setForeground(attributeSet, color);
            if (background != null) {
                StyleConstants.setBackground(attributeSet, background);
            } else {
                attributeSet.removeAttribute(StyleConstants.Background);
            }
            StyleConstants.setUnderline(attributeSet, underline);
            StyleConstants.setItalic(attributeSet, italic);
        }

        /**
         * Get the AttributeSet from this style.
         * @return the AttributeSet
         */
        public SimpleAttributeSet getAttributeSet() {
            SimpleAttributeSet attributeSet = new SimpleAttributeSet();
            StyleConstants.setBold(attributeSet, bold);
            StyleConstants.setForeground(attributeSet, color);
            if (background != null) {
                StyleConstants.setBackground(attributeSet, background);
            }
            StyleConstants.setUnderline(attributeSet, underline);
            StyleConstants.setItalic(attributeSet, italic);
            return attributeSet;
        }

        /**
         * Get the background color.
         * @return the background color or null if no color is set
         */
        public Color getBackground() {
            return background;
        }

        /**
         * Set the background color.
         * @param background input null means do not set the background
         */
        public void setBackground(Color background) {
            this.background = background;
        }

        public boolean isBold() {
            return bold;
        }

        public void setBold(boolean bold) {
            this.bold = bold;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public boolean isItalic() {
            return italic;
        }

        public void setItalic(boolean italic) {
            this.italic = italic;
        }

        public boolean isUnderline() {
            return underline;
        }

        public void setUnderline(boolean underline) {
            this.underline = underline;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Style)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            Style _object = (Style) obj;
            return _object.bold == bold && _object.color.equals(color) && _object.background.equals(background)
                    && _object.underline == underline && _object.italic == italic;
        }

        @Override
        public Style clone() {
            Style object = null;
            try {
                object = (Style) super.clone();
                object.bold = bold;
                object.color = color;
                object.background = background;
                object.underline = underline;
                object.italic = italic;
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(Style.class.getName()).log(Level.WARNING, null, ex);
            }
            return object;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            sb.append(getClass().getName());
            sb.append(": ");
            sb.append("bold: ").append(bold);
            sb.append(", ");
            sb.append("color: ").append(color);
            sb.append(", ");
            sb.append("bg: ").append(background);
            sb.append(", ");
            sb.append("underline: ").append(underline);
            sb.append(", ");
            sb.append("italic: ").append(italic);

            return sb.toString();
        }
    }
}
