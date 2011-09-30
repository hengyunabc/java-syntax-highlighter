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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import syntaxhighlighter.Brush;
import syntaxhighlighter.Brush.RegExpRule;

/**
 * Scala brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class BrushScala extends Brush {

    public BrushScala() {
        super();

        // Contributed by Yegor Jbanov and David Bernard.
        List<RegExpRule> _regExpRuleList = new ArrayList<RegExpRule>();
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.singleLineCComments, "comments")); // one line comments
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.multiLineCComments, "comments")); // multiline comments
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.multiLineSingleQuotedString, "string")); // multi-line strings
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.multiLineDoubleQuotedString, "string")); // double-quoted string
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.singleQuotedString, "string")); // strings
        _regExpRuleList.add(new RegExpRule("0x[a-f0-9]+|\\d+(\\.\\d+)?", Pattern.CASE_INSENSITIVE, "value")); // numbers
        _regExpRuleList.add(new RegExpRule(getKeywords("val sealed case def true trait implicit forSome import match object null finally super "
                + "override try lazy for var catch throw type extends class while with new final yield abstract "
                + "else do if return protected private this package false"), Pattern.MULTILINE, "keyword")); // keywords
        _regExpRuleList.add(new RegExpRule("[_:=><%#@]+", Pattern.MULTILINE, "keyword")); // scala keyword
        setRegExpRuleList(_regExpRuleList);

        setCommonFileExtensionList(Arrays.asList("scl", "scala"));
    }
}
