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
