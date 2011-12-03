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
