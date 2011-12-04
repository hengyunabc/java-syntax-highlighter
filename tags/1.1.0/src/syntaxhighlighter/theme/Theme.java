// Copyright (c) 2011 Chan Wai Shing
//
// Permission is hereby granted, free of charge, to any person obtaining
// a copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to
// permit persons to whom the Software is furnished to do so, subject to
// the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
package syntaxhighlighter.theme;

import java.awt.Color;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import syntaxhighlighter.gui.JTextComponentRowHeader;

/**
 * Theme for the syntax highlighter.
 * To make a new theme, either extending this class of initiate this class 
 * and set the parameter by the setter. For the default value, please refer to 
 * the constructor.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class Theme {

  private static final Logger LOG = Logger.getLogger(Theme.class.getName());
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
   * The minimum padding from 'the leftmost of the line number text' to 
   * 'the left margin'.
   */
  protected int gutterTextPaddingLeft;
  /**
   * The minimum padding from 'the rightmost of the line number text' to 
   * 'the right margin' (not to the gutter border).
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
   * <li>plain, comments, string, keyword, preprocessor, variable, value, 
   * functions, constants, script, scriptBackground, color1, color2, color3: 
   * no style set</li>
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
   * Get the {@link syntaxhighlighter.theme.Style} by keyword.
   * 
   * @param key the keyword, possible values: plain, comments, string, 
   * keyword, preprocessor, variable, value, functions, constants, script, 
   * scriptBackground, color1, color2 or color3
   * 
   * @return the {@link syntaxhighlighter.theme.Style} related to the 
   * {@code key}; if the style related to the <code>key</code> not exist, 
   * the style of 'plain' will return.
   */
  public Style getStyle(String key) {
    if (key == null) {
      return plain;
    }
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
   * @return the font
   */
  public Font getFont() {
    return font;
  }

  /**
   * The font of the script text.
   * @param font the font
   */
  public void setFont(Font font) {
    if (font == null) {
      throw new NullPointerException("argument 'font' cannot be null");
    }
    this.font = font;
  }

  /**
   * The background color of the script text area.
   * @return the color
   */
  public Color getBackground() {
    return background;
  }

  /**
   * The background color of the script text area.
   * @param background the color
   */
  public void setBackground(Color background) {
    if (background == null) {
      throw new NullPointerException("argument 'background' cannot be null");
    }
    this.background = background;
  }

  /**
   * The background color of the highlighted line of script text.
   * @return the color
   */
  public Color getHighlightedBackground() {
    return highlightedBackground;
  }

  /**
   * The background color of the highlighted line of script text.
   * @param highlightedBackground the color
   */
  public void setHighlightedBackground(Color highlightedBackground) {
    if (highlightedBackground == null) {
      throw new NullPointerException("argument 'highlightedBackground' cannot be null");
    }
    this.highlightedBackground = highlightedBackground;
  }

  /**
   * The color of the gutter text.
   * @return the color
   */
  public Color getGutterText() {
    return gutterText;
  }

  /**
   * The color of the gutter text.
   * @param gutterText the color
   */
  public void setGutterText(Color gutterText) {
    if (gutterText == null) {
      throw new NullPointerException("argument 'gutterText' cannot be null");
    }
    this.gutterText = gutterText;
  }

  /**
   * The color of the border that joint the gutter and the script text panel.
   * @return the color
   */
  public Color getGutterBorderColor() {
    return gutterBorderColor;
  }

  /**
   * The color of the border that joint the gutter and the script text panel.
   * @param gutterBorderColor the color
   */
  public void setGutterBorderColor(Color gutterBorderColor) {
    if (gutterBorderColor == null) {
      throw new NullPointerException("argument 'gutterBorderColor' cannot be null");
    }
    this.gutterBorderColor = gutterBorderColor;
  }

  /**
   * The width of the border that joint the gutter and the script text panel.
   * @return the width in pixel
   */
  public int getGutterBorderWidth() {
    return gutterBorderWidth;
  }

  /**
   * The width of the border that joint the gutter and the script text panel.
   * @param gutterBorderWidth in pixel
   */
  public void setGutterBorderWidth(int gutterBorderWidth) {
    this.gutterBorderWidth = gutterBorderWidth;
  }

  /**
   * The font of the gutter text.
   * @return the font
   */
  public Font getGutterTextFont() {
    return gutterTextFont;
  }

  /**
   * The font of the gutter text.
   * @param gutterTextFont the font
   */
  public void setGutterTextFont(Font gutterTextFont) {
    if (gutterTextFont == null) {
      throw new NullPointerException("argument 'gutterTextFont' cannot be null");
    }
    this.gutterTextFont = gutterTextFont;
  }

  /**
   * The minimum padding from 'the leftmost of the line number text' to 
   * 'the left margin'.
   * @return the padding in pixel
   */
  public int getGutterTextPaddingLeft() {
    return gutterTextPaddingLeft;
  }

  /**
   * The minimum padding from 'the leftmost of the line number text' to 
   * 'the left margin'.
   * @param gutterTextPaddingLeft in pixel
   */
  public void setGutterTextPaddingLeft(int gutterTextPaddingLeft) {
    this.gutterTextPaddingLeft = gutterTextPaddingLeft;
  }

  /**
   * The minimum padding from 'the rightmost of the line number text' to 
   * 'the right margin' (not to the gutter border).
   * @return the padding in pixel
   */
  public int getGutterTextPaddingRight() {
    return gutterTextPaddingRight;
  }

  /**
   * The minimum padding from 'the rightmost of the line number text' to 
   * 'the right margin' (not to the gutter border).
   * @param gutterTextPaddingRight in pixel
   */
  public void setGutterTextPaddingRight(int gutterTextPaddingRight) {
    this.gutterTextPaddingRight = gutterTextPaddingRight;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @return the style
   */
  public Style getPlain() {
    return plain;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @param plain the style
   */
  public void setPlain(Style plain) {
    if (plain == null) {
      throw new NullPointerException("argument 'plain' cannot be null");
    }
    this.plain = plain;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @return the style
   */
  public Style getComments() {
    return comments;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @param comments the style
   */
  public void setComments(Style comments) {
    if (comments == null) {
      throw new NullPointerException("argument 'comments' cannot be null");
    }
    this.comments = comments;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @return the style
   */
  public Style getString() {
    return string;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @param string the style
   */
  public void setString(Style string) {
    if (string == null) {
      throw new NullPointerException("argument 'string' cannot be null");
    }
    this.string = string;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @return the style
   */
  public Style getKeyword() {
    return keyword;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @param keyword the style
   */
  public void setKeyword(Style keyword) {
    if (keyword == null) {
      throw new NullPointerException("argument 'keyword' cannot be null");
    }
    this.keyword = keyword;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @return the style
   */
  public Style getPreprocessor() {
    return preprocessor;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @param preprocessor the style
   */
  public void setPreprocessor(Style preprocessor) {
    if (preprocessor == null) {
      throw new NullPointerException("argument 'preprocessor' cannot be null");
    }
    this.preprocessor = preprocessor;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @return the style
   */
  public Style getVariable() {
    return variable;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @param variable the style
   */
  public void setVariable(Style variable) {
    if (variable == null) {
      throw new NullPointerException("argument 'variable' cannot be null");
    }
    this.variable = variable;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @return the style
   */
  public Style getValue() {
    return value;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @param value the style
   */
  public void setValue(Style value) {
    if (value == null) {
      throw new NullPointerException("argument 'value' cannot be null");
    }
    this.value = value;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @return the style
   */
  public Style getFunctions() {
    return functions;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @param functions the style
   */
  public void setFunctions(Style functions) {
    if (functions == null) {
      throw new NullPointerException("argument 'functions' cannot be null");
    }
    this.functions = functions;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @return the style
   */
  public Style getConstants() {
    return constants;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @param constants the style
   */
  public void setConstants(Style constants) {
    if (constants == null) {
      throw new NullPointerException("argument 'constants' cannot be null");
    }
    this.constants = constants;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @return the style
   */
  public Style getScript() {
    return script;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @param script the style
   */
  public void setScript(Style script) {
    if (script == null) {
      throw new NullPointerException("argument 'script' cannot be null");
    }
    this.script = script;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @return the style
   */
  public Style getScriptBackground() {
    return scriptBackground;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @param scriptBackground the style
   */
  public void setScriptBackground(Style scriptBackground) {
    if (scriptBackground == null) {
      throw new NullPointerException("argument 'scriptBackground' cannot be null");
    }
    this.scriptBackground = scriptBackground;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @return the style
   */
  public Style getColor1() {
    return color1;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @param color1 the style
   */
  public void setColor1(Style color1) {
    if (color1 == null) {
      throw new NullPointerException("argument 'color1' cannot be null");
    }
    this.color1 = color1;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @return the style
   */
  public Style getColor2() {
    return color2;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @param color2 the style
   */
  public void setColor2(Style color2) {
    if (color2 == null) {
      throw new NullPointerException("argument 'color2' cannot be null");
    }
    this.color2 = color2;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @return the style
   */
  public Style getColor3() {
    return color3;
  }

  /**
   * Code style, keywords are the same as those in JavaScript SyntaxHighlighter.
   * @param color3 the style
   */
  public void setColor3(Style color3) {
    if (color3 == null) {
      throw new NullPointerException("argument 'color3' cannot be null");
    }
    this.color3 = color3;
  }

  /**
   * {@inheritDoc}
   */
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
      LOG.log(Level.WARNING, null, ex);
    }
    return object;
  }

  /**
   * {@inheritDoc}
   */
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
}
