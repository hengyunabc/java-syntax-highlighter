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
 * Diff file brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class BrushDiff extends Brush {

    public BrushDiff() {
        super();

        List<RegExpRule> _regExpRuleList = new ArrayList<RegExpRule>();
        _regExpRuleList.add(new RegExpRule("^\\+\\+\\+\\s.*$", Pattern.MULTILINE, "color2")); // new file
        _regExpRuleList.add(new RegExpRule("^\\-\\-\\-\\s.*$", Pattern.MULTILINE, "color2")); // old file
        _regExpRuleList.add(new RegExpRule("^\\s.*$", Pattern.MULTILINE, "color1")); // unchanged
        _regExpRuleList.add(new RegExpRule("^@@.*@@$", Pattern.MULTILINE, "variable")); // location
        _regExpRuleList.add(new RegExpRule("^\\+.*$", Pattern.MULTILINE, "string")); // additions
        _regExpRuleList.add(new RegExpRule("^\\-.*$", Pattern.MULTILINE, "color3")); // deletions
        setRegExpRuleList(_regExpRuleList);

        setCommonFileExtensionList(Arrays.asList("diff", "patch"));
    }
}
