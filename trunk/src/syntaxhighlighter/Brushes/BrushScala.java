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
public class BrushScala extends Brush {

    public BrushScala() {
        super();

        // Contributed by Yegor Jbanov and David Bernard.
        regExpRule.add(new RegExpRule(Brush.RegExpRule.singleLineCComments, "comments")); // one line comments
        regExpRule.add(new RegExpRule(Brush.RegExpRule.multiLineCComments, "comments")); // multiline comments
        regExpRule.add(new RegExpRule(Brush.RegExpRule.multiLineSingleQuotedString, "string")); // multi-line strings
        regExpRule.add(new RegExpRule(Brush.RegExpRule.multiLineDoubleQuotedString, "string")); // double-quoted string
        regExpRule.add(new RegExpRule(Brush.RegExpRule.singleQuotedString, "string")); // strings
        regExpRule.add(new RegExpRule("0x[a-f0-9]+|\\d+(\\.\\d+)?", Pattern.CASE_INSENSITIVE, "value")); // numbers
        regExpRule.add(new RegExpRule(getKeywords("val sealed case def true trait implicit forSome import match object null finally super "
                + "override try lazy for var catch throw type extends class while with new final yield abstract "
                + "else do if return protected private this package false"), Pattern.MULTILINE, "keyword")); // keywords
        regExpRule.add(new RegExpRule("[_:=><%#@]+", Pattern.MULTILINE, "keyword")); // scala keyword

        commonFileExtensionList = Arrays.asList(new String[]{"scl", "scala"});
    }
}
