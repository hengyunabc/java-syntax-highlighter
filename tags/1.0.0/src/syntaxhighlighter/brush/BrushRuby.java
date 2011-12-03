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
 * Ruby brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class BrushRuby extends Brush {

    public BrushRuby() {
        super();

        // Contributed by Erik Peterson.

        String keywords = "alias and BEGIN begin break case class def define_method defined do each else elsif "
                + "END end ensure false for if in module new next nil not or raise redo rescue retry return "
                + "self super then throw true undef unless until when while yield";
        String builtins = "Array Bignum Binding Class Continuation Dir Exception FalseClass File::Stat File Fixnum Fload "
                + "Hash Integer IO MatchData Method Module NilClass Numeric Object Proc Range Regexp String Struct::TMS Symbol "
                + "ThreadGroup Thread Time TrueClass";

        List<RegExpRule> _regExpRuleList = new ArrayList<RegExpRule>();
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.singleLinePerlComments, "comments")); // one line comments
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.doubleQuotedString, "string")); // double quoted strings
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.singleQuotedString, "string")); // single quoted strings
        _regExpRuleList.add(new RegExpRule("\\b[A-Z0-9_]+\\b", "constants")); // constants
        _regExpRuleList.add(new RegExpRule(":[a-z][A-Za-z0-9_]*", "color2")); // symbols
        RegExpRule _regExpRule = new RegExpRule("(\\$|@@|@)\\w+", "variable");
        _regExpRule.setBold(true);
        _regExpRuleList.add(_regExpRule); // $global, @instance, and @@class variables
        _regExpRuleList.add(new RegExpRule(getKeywords(keywords), Pattern.MULTILINE, "keyword")); // keywords
        _regExpRuleList.add(new RegExpRule(getKeywords(builtins), Pattern.MULTILINE, "color1")); // builtins
        setRegExpRuleList(_regExpRuleList);

        setHTMLScriptRegExp(HTMLScriptRegExp.phpScriptTags);

        setCommonFileExtensionList(Arrays.asList("rb", "rbw"));
    }
}
