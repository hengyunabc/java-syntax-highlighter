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
