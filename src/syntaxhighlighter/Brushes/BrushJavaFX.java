/**
 * This is part of the Java SyntaxHighlighter.
 * 
 * It is distributed under MIT license. See the file 'readme.txt' for
 * information on usage and redistribution of this file, and for a
 * DISCLAIMER OF ALL WARRANTIES.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
package syntaxhighlighter.Brushes;

import java.util.Arrays;
import java.util.regex.Pattern;
import syntaxhighlighter.Brush;
import syntaxhighlighter.Brush.RegExpRule;

/**
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class BrushJavaFX extends Brush {

    public BrushJavaFX() {
        super();

        // Contributed by Patrick Webster
        // http://patrickwebster.blogspot.com/2009/04/javafx-brush-for-syntaxhighlighter.html
        regExpRule.add(new RegExpRule(Brush.RegExpRule.singleLineCComments, "comments"));
        regExpRule.add(new RegExpRule(Brush.RegExpRule.multiLineCComments, "comments"));
        regExpRule.add(new RegExpRule(Brush.RegExpRule.singleQuotedString, "string"));
        regExpRule.add(new RegExpRule(Brush.RegExpRule.doubleQuotedString, "string"));
        regExpRule.add(new RegExpRule("(-?\\.?)(\\b(\\d*\\.?\\d+|\\d+\\.?\\d*)(e[+-]?\\d+)?|0x[a-f\\d]+)\\b\\.?", Pattern.CASE_INSENSITIVE, "color2")); // numbers
        regExpRule.add(new RegExpRule(getKeywords("Boolean Byte Character Double Duration "
                + "Float Integer Long Number Short String Void"), Pattern.MULTILINE, "variable")); // datatypes
        regExpRule.add(new RegExpRule(getKeywords("abstract after and as assert at before bind bound break catch class "
                + "continue def delete else exclusive extends false finally first for from "
                + "function if import in indexof init insert instanceof into inverse last "
                + "lazy mixin mod nativearray new not null on or override package postinit "
                + "protected public public-init public-read replace return reverse sizeof "
                + "step super then this throw true try tween typeof var where while with "
                + "attribute let private readonly static trigger"), Pattern.MULTILINE, "keyword"));

        commonFileExtensionList = Arrays.asList(new String[]{"fx"});
    }
}
