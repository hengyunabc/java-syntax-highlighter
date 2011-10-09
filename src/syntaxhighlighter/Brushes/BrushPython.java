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
 * Python brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class BrushPython extends Brush {

    public BrushPython() {
        super();

        // Contributed by Gheorghe Milas and Ahmad Sherif

        String keywords = "and assert break class continue def del elif else "
                + "except exec finally for from global if import in is "
                + "lambda not or pass print raise return try yield while";
        String funcs = "__import__ abs all any apply basestring bin bool buffer callable "
                + "chr classmethod cmp coerce compile complex delattr dict dir "
                + "divmod enumerate eval execfile file filter float format frozenset "
                + "getattr globals hasattr hash help hex id input int intern "
                + "isinstance issubclass iter len list locals long map max min next "
                + "object oct open ord pow print property range raw_input reduce "
                + "reload repr reversed round set setattr slice sorted staticmethod "
                + "str sum super tuple type type unichr unicode vars xrange zip";
        String special = "None True False self cls class_";

        List<RegExpRule> _regExpRuleList = new ArrayList<RegExpRule>();
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.singleLinePerlComments, "comments"));
        _regExpRuleList.add(new RegExpRule("^\\s*@\\w+", Pattern.MULTILINE, "color2"));
        _regExpRuleList.add(new RegExpRule("(['\\\"]{3})([^['\\\"]{3}])*?['\\\"]{3}", Pattern.MULTILINE, "comments"));
        _regExpRuleList.add(new RegExpRule("\"(?!\")(?:\\.|\\\\\\\"|[^\\\"\"\\n])*\"", Pattern.MULTILINE, "string"));
        _regExpRuleList.add(new RegExpRule("'(?!')(?:\\.|(\\\\\\')|[^\\''\\n])*'", Pattern.MULTILINE, "string"));
        _regExpRuleList.add(new RegExpRule("\\+|\\-|\\*|\\/|\\%|=|==", Pattern.MULTILINE, "keyword"));
        _regExpRuleList.add(new RegExpRule("\\b\\d+\\.?\\w*", "value"));
        _regExpRuleList.add(new RegExpRule(getKeywords(funcs), Pattern.MULTILINE | Pattern.CASE_INSENSITIVE, "functions"));
        _regExpRuleList.add(new RegExpRule(getKeywords(keywords), Pattern.MULTILINE, "keyword"));
        _regExpRuleList.add(new RegExpRule(getKeywords(special), Pattern.MULTILINE, "color1"));
        setRegExpRuleList(_regExpRuleList);

        setHTMLScriptRegExp(HTMLScriptRegExp.aspScriptTags);

        setCommonFileExtensionList(Arrays.asList("py"));
    }
}
