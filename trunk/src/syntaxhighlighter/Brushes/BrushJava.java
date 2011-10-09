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

/**
 * Java brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class BrushJava extends Brush {

    public BrushJava() {
        super();

        String keywords = "abstract assert boolean break byte case catch char class const "
                + "continue default do double else enum extends "
                + "false final finally float for goto if implements import "
                + "instanceof int interface long native new null "
                + "package private protected public return "
                + "short static strictfp super switch synchronized this throw throws true "
                + "transient try void volatile while";

        List<RegExpRule> _regExpRuleList = new ArrayList<RegExpRule>();
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.singleLineCComments, "comments")); // one line comments
        _regExpRuleList.add(new RegExpRule("\\/\\*([^\\*][\\s\\S]*?)?\\*\\/", Pattern.MULTILINE, "comments")); // multiline comments
        _regExpRuleList.add(new RegExpRule("\\/\\*(?!\\*\\/)\\*[\\s\\S]*?\\*\\/", Pattern.MULTILINE, "preprocessor")); // documentation comments
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.doubleQuotedString, "string")); // strings
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.singleQuotedString, "string")); // strings
        _regExpRuleList.add(new RegExpRule("\\b([\\d]+(\\.[\\d]+)?|0x[a-f0-9]+)\\b", Pattern.CASE_INSENSITIVE, "value")); // numbers
        _regExpRuleList.add(new RegExpRule("(?!\\@interface\\b)\\@[\\$\\w]+\\b", "color1")); // annotation @anno
        _regExpRuleList.add(new RegExpRule("\\@interface\\b", "color2")); // @interface keyword
        _regExpRuleList.add(new RegExpRule(getKeywords(keywords), Pattern.MULTILINE, "keyword")); // java keyword
        setRegExpRuleList(_regExpRuleList);

        setHTMLScriptRegExp(new HTMLScriptRegExp("(?:&lt;|<)%[@!=]?", "%(?:&gt;|>)"));

        setCommonFileExtensionList(Arrays.asList("java"));
    }
}
