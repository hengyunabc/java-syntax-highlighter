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
public class BrushDiff extends Brush {

    public BrushDiff() {
        super();

        regExpRule.add(new RegExpRule("^\\+\\+\\+.*$", Pattern.MULTILINE, "color2"));
        regExpRule.add(new RegExpRule("^\\-\\-\\-.*$", Pattern.MULTILINE, "color2"));
        regExpRule.add(new RegExpRule("^\\s.*$", Pattern.MULTILINE, "color1"));
        regExpRule.add(new RegExpRule("^@@.*@@$", Pattern.MULTILINE, "variable"));
        regExpRule.add(new RegExpRule("^\\+[^\\+]{1}.*$", Pattern.MULTILINE, "string"));
        regExpRule.add(new RegExpRule("^\\-[^\\-]{1}.*$", Pattern.MULTILINE, "comments"));

        commonFileExtensionList = Arrays.asList(new String[]{"diff"});
    }
}
