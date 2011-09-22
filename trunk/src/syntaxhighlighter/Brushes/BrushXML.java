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
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import syntaxhighlighter.Brush;
import syntaxhighlighter.Brush.RegExpRule;

/**
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class BrushXML extends Brush {

    public BrushXML() {
        super();

        regExpRule.add(new RegExpRule("(\\&lt;|<)\\!\\[[\\w\\s]*?\\[(.|\\s)*?\\]\\](\\&gt;|>)", Pattern.MULTILINE, "color2"));
        regExpRule.add(new RegExpRule(Brush.RegExpRule.xmlComments, "comments"));

        RegExpRule _regExpRule = new RegExpRule("((?:&lt;|<)[\\s\\/\\?]*(?:\\w+))(.*?)([\\s\\/\\?]*(?:&gt;|>))", Pattern.DOTALL, "");
        Map<Integer, String> matchesToStyleKey = new HashMap<Integer, String>();
        matchesToStyleKey.put(1, "element");
        matchesToStyleKey.put(2, "attributes");
        matchesToStyleKey.put(3, "element");
        _regExpRule.setMatchesToStyleKey(matchesToStyleKey);
        regExpRule.add(_regExpRule);

        _regExpRule = new RegExpRule("([\\w:\\-\\.]+)"
                + "(\\s*=\\s*)"
                + "(\".*?\"|'.*?'|\\w+)", Pattern.COMMENTS, "");
        matchesToStyleKey = new HashMap<Integer, String>();
        matchesToStyleKey.put(1, "color1");
        matchesToStyleKey.put(2, "plain");
        matchesToStyleKey.put(3, "string");
        _regExpRule.setMatchesToStyleKey(matchesToStyleKey);
        _regExpRule.setAffectedStyleKeyList(Arrays.asList(new String[]{"attributes"}));
        regExpRule.add(_regExpRule);

        _regExpRule = new RegExpRule("((?:&lt;|<)[\\s\\/\\?]*)([:\\w-\\.]+)", Pattern.COMMENTS, "");
        matchesToStyleKey = new HashMap<Integer, String>();
        matchesToStyleKey.put(1, "plain");
        matchesToStyleKey.put(2, "keyword");
        _regExpRule.setMatchesToStyleKey(matchesToStyleKey);
        _regExpRule.setAffectedStyleKeyList(Arrays.asList(new String[]{"element"}));
        regExpRule.add(_regExpRule);

        _regExpRule = new RegExpRule(".*", Pattern.COMMENTS, "plain");
        _regExpRule.setAffectedStyleKeyList(Arrays.asList(new String[]{"element", "attributes"}));
        regExpRule.add(_regExpRule);

        commonFileExtensionList = Arrays.asList(new String[]{"xml", "html", "xhtml", "xslt"});
    }
}