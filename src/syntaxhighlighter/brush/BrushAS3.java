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
 * Action Script brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class BrushAS3 extends Brush {

    public BrushAS3() {
        super();

        // Created by Peter Atoria @ http://iAtoria.com

        String inits = "class interface function package";
        String keywords = "-Infinity ...rest Array as AS3 Boolean break case catch const continue Date decodeURI "
                + "decodeURIComponent default delete do dynamic each else encodeURI encodeURIComponent escape "
                + "extends false final finally flash_proxy for get if implements import in include Infinity "
                + "instanceof int internal is isFinite isNaN isXMLName label namespace NaN native new null "
                + "Null Number Object object_proxy override parseFloat parseInt private protected public "
                + "return set static String super switch this throw true try typeof uint undefined unescape "
                + "use void while with";

        List<RegExpRule> _regExpRuleList = new ArrayList<RegExpRule>();
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.singleLineCComments, "comments")); // one line comments
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.multiLineCComments, "comments")); // multiline comments
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.doubleQuotedString, "string")); // double quoted strings
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.singleQuotedString, "string")); // single quoted strings
        _regExpRuleList.add(new RegExpRule("\\b([\\d]+(\\.[\\d]+)?|0x[a-f0-9]+)\\b", Pattern.CASE_INSENSITIVE, "value")); // numbers
        _regExpRuleList.add(new RegExpRule(getKeywords(inits), Pattern.MULTILINE, "color3")); // initializations
        _regExpRuleList.add(new RegExpRule(getKeywords(keywords), Pattern.MULTILINE, "keyword")); // keywords
        _regExpRuleList.add(new RegExpRule("var", Pattern.MULTILINE, "variable")); // variable
        _regExpRuleList.add(new RegExpRule("trace", Pattern.MULTILINE, "color1")); // trace
        setRegExpRuleList(_regExpRuleList);

        setHTMLScriptRegExp(HTMLScriptRegExp.scriptScriptTags);

        setCommonFileExtensionList(Arrays.asList("as"));
    }
}
