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
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class Theme {

    private Color background;
    private Color gutterText;
    private Color gutterBorderColor;
    private int gutterBorderWidth;
    private Font gutterTextFont;
    private int gutterTextPaddingLeft;
    private int gutterTextPaddingRight;
    private Font font;
    private Style plain;
    private Style comments;
    private Style string;
    private Style keyword;
    private Style preprocessor;
    private Style variable;
    private Style value;
    private Style functions;
    private Style constants;
    private Style script;
    private Style scriptBackground;
    private Style color1;
    private Style color2;
    private Style color3;

    public Theme() {
        background = Color.white;
        gutterText = Color.black;
        gutterBorderColor = new Color(184, 184, 184);
        gutterBorderWidth = 3;
        gutterTextFont = new Font("Consolas", Font.PLAIN, 12);
        gutterTextPaddingLeft = 7;
        gutterTextPaddingRight = 7;
        font = gutterTextFont;
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

    public void setTheme(JTextComponent textComponent) {
        textComponent.setBackground(background);
        textComponent.setFont(font);
    }

    public void setTheme(JTextComponentRowHeader rowHeader) {
        rowHeader.setBackground(background);
        rowHeader.setForeground(gutterText);
        rowHeader.setBorderColor(gutterBorderColor);
        rowHeader.setBorderWidth(gutterBorderWidth);
        rowHeader.setLineNumberFont(gutterTextFont);
        rowHeader.setPaddingLeft(gutterTextPaddingLeft);
        rowHeader.setPaddingRight(gutterTextPaddingRight);
    }

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

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Color getGutterText() {
        return gutterText;
    }

    public void setGutterText(Color gutterText) {
        this.gutterText = gutterText;
    }

    public Color getGutterBorderColor() {
        return gutterBorderColor;
    }

    public void setGutterBorderColor(Color gutterBorderColor) {
        this.gutterBorderColor = gutterBorderColor;
    }

    public int getGutterBorderWidth() {
        return gutterBorderWidth;
    }

    public void setGutterBorderWidth(int gutterBorderWidth) {
        this.gutterBorderWidth = gutterBorderWidth;
    }

    public Font getGutterTextFont() {
        return gutterTextFont;
    }

    public void setGutterTextFont(Font gutterTextFont) {
        this.gutterTextFont = gutterTextFont;
    }

    public int getGutterTextPaddingLeft() {
        return gutterTextPaddingLeft;
    }

    public void setGutterTextPaddingLeft(int gutterTextPaddingLeft) {
        this.gutterTextPaddingLeft = gutterTextPaddingLeft;
    }

    public int getGutterTextPaddingRight() {
        return gutterTextPaddingRight;
    }

    public void setGutterTextPaddingRight(int gutterTextPaddingRight) {
        this.gutterTextPaddingRight = gutterTextPaddingRight;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Style getColor1() {
        return color1;
    }

    public void setColor1(Style color1) {
        this.color1 = color1;
    }

    public Style getColor2() {
        return color2;
    }

    public void setColor2(Style color2) {
        this.color2 = color2;
    }

    public Style getColor3() {
        return color3;
    }

    public void setColor3(Style color3) {
        this.color3 = color3;
    }

    public Style getComments() {
        return comments;
    }

    public void setComments(Style comments) {
        this.comments = comments;
    }

    public Style getConstants() {
        return constants;
    }

    public void setConstants(Style constants) {
        this.constants = constants;
    }

    public Style getFunctions() {
        return functions;
    }

    public void setFunctions(Style functions) {
        this.functions = functions;
    }

    public Style getKeyword() {
        return keyword;
    }

    public void setKeyword(Style keyword) {
        this.keyword = keyword;
    }

    public Style getPlain() {
        return plain;
    }

    public void setPlain(Style plain) {
        this.plain = plain;
    }

    public Style getPreprocessor() {
        return preprocessor;
    }

    public void setPreprocessor(Style preprocessor) {
        this.preprocessor = preprocessor;
    }

    public Style getScript() {
        return script;
    }

    public void setScript(Style script) {
        this.script = script;
    }

    public Style getScriptBackground() {
        return scriptBackground;
    }

    public void setScriptBackground(Style scriptBackground) {
        this.scriptBackground = scriptBackground;
    }

    public Style getString() {
        return string;
    }

    public void setString(Style string) {
        this.string = string;
    }

    public Style getValue() {
        return value;
    }

    public void setValue(Style value) {
        this.value = value;
    }

    public Style getVariable() {
        return variable;
    }

    public void setVariable(Style variable) {
        this.variable = variable;
    }

    public static class Style {

        private boolean bold;
        private Color color;
        private Color background;
        private boolean underline;
        private boolean italic;

        public Style() {
            bold = false;
            color = Color.black;
            background = null;
            underline = false;
            italic = false;
        }

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

        public Color getBackground() {
            return background;
        }

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(getClass().getName());
        sb.append(": ");
        sb.append("background: ").append(getBackground());
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
        sb.append("font: ").append(getFont());
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
}
