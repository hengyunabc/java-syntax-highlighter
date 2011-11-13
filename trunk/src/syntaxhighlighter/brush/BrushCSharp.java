/**
 * This is part of the Java SyntaxHighlighter.
 * 
 * It is distributed under MIT license. See the file 'readme.txt' for
 * information on usage and redistribution of this file, and for a
 * DISCLAIMER OF ALL WARRANTIES.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
package syntaxhighlighter.brush;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import syntaxhighlighter.Brush;
import syntaxhighlighter.Brush.RegExpRule;

/**
 * C# brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class BrushCSharp extends Brush {

    public BrushCSharp() {
        super();

        String keywords = "abstract as base bool break byte case catch char checked class const "
                + "continue decimal default delegate do double else enum event explicit "
                + "extern false finally fixed float for foreach get goto if implicit in int "
                + "interface internal is lock long namespace new null object operator out "
                + "override params private protected public readonly ref return sbyte sealed set "
                + "short sizeof stackalloc static string struct switch this throw true try "
                + "typeof uint ulong unchecked unsafe ushort using virtual void while var";

        List<RegExpRule> _regExpRuleList = new ArrayList<RegExpRule>();
        _regExpRuleList.add(new RegExpRule("\\/\\/\\/.*$", Pattern.MULTILINE, "color1")); // documents
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.singleLineCComments, "comments")); // one line comments
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.multiLineCComments, "comments")); // multiline comments
        _regExpRuleList.add(new RegExpRule("@\"(?:[^\"]|\"\")*\"", "string")); // @-quoted strings
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.doubleQuotedString, "string")); // strings
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.singleQuotedString, "string")); // strings
        _regExpRuleList.add(new RegExpRule("^\\s*#.*", Pattern.MULTILINE, "preprocessor")); // preprocessor tags like #region and #endregion
        _regExpRuleList.add(new RegExpRule(getKeywords(keywords), Pattern.MULTILINE, "keyword")); // c# keyword
        _regExpRuleList.add(new RegExpRule("\\bpartial(?=\\s+(?:class|interface|struct)\\b)", "keyword")); // contextual keyword: 'partial'
        _regExpRuleList.add(new RegExpRule("\\byield(?=\\s+(?:return|break)\\b)", "keyword")); // contextual keyword: 'yield'
        setRegExpRuleList(_regExpRuleList);

        setHTMLScriptRegExp(HTMLScriptRegExp.aspScriptTags);

        setCommonFileExtensionList(Arrays.asList("cs"));
    }
}
