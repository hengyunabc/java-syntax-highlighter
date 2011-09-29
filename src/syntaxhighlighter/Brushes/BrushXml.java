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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import syntaxhighlighter.Brush;
import syntaxhighlighter.Brush.RegExpRule;

/**
 * General XML (include HTML) brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class BrushXml extends Brush {

    public BrushXml() {
        super();

        List<RegExpRule> regExpRuleList = new ArrayList<RegExpRule>();

        regExpRuleList.add(new RegExpRule("(\\&lt;|<)\\!\\[[\\w\\s]*?\\[(.|\\s)*?\\]\\](\\&gt;|>)", Pattern.MULTILINE, "color2"));
        regExpRuleList.add(new RegExpRule(Brush.RegExpRule.xmlComments, "comments"));

        // regular expression for highlighting the tag
        RegExpRule tagRegExpRule = new RegExpRule("(?:&lt;|<)[\\s\\/\\?]*([:\\w-\\.]+)", Pattern.COMMENTS, "");
        Map<Integer, Object> tagMatchesToStyleKey = new HashMap<Integer, Object>();
        // highlight the tag only, not including the symbols at the start, 1 means the group 1 of the matched results
        tagMatchesToStyleKey.put(1, "keyword");
        tagRegExpRule.setGroupOperations(tagMatchesToStyleKey);

        // regular expression for highlighting the variable assignment
        RegExpRule valueRegExpRule = new RegExpRule("([\\w:\\-\\.]+)"
                + "\\s*=\\s*"
                + "(\".*?\"|'.*?'|\\w+)", Pattern.COMMENTS, "");
        Map<Integer, Object> valueMatchesToStyleKey = new HashMap<Integer, Object>();
        // highlight the variable name, 1 means the group 1 of the matched results
        valueMatchesToStyleKey.put(1, "color1");
        // highlight the value, 2 means the group 2 of the matched results
        valueMatchesToStyleKey.put(2, "string");
        valueRegExpRule.setGroupOperations(valueMatchesToStyleKey);

        RegExpRule _regExpRule = new RegExpRule("((?:&lt;|<)[\\s\\/\\?]*(?:\\w+))(.*?)[\\s\\/\\?]*(?:&gt;|>)", Pattern.DOTALL, "");
        Map<Integer, Object> matchesToRegExp = new HashMap<Integer, Object>();
        // perform futher operation on the group 1 of the matched results
        matchesToRegExp.put(1, tagRegExpRule);
        // perform futher operation on the group 2 of the matched results
        matchesToRegExp.put(2, valueRegExpRule);
        _regExpRule.setGroupOperations(matchesToRegExp);
        regExpRuleList.add(_regExpRule);

        setRegExpRuleList(regExpRuleList);

        setCommonFileExtensionList(Arrays.asList("xml", "html", "xhtml", "xslt"));
    }
}