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
public class BrushJScript extends Brush {

    public BrushJScript() {
        super();

        regExpRule.add(new RegExpRule(Brush.RegExpRule.multiLineDoubleQuotedString, "string")); // double quoted strings
        regExpRule.add(new RegExpRule(Brush.RegExpRule.multiLineSingleQuotedString, "string")); // single quoted strings
        regExpRule.add(new RegExpRule(Brush.RegExpRule.singleLineCComments, "comments")); // one line comments
        regExpRule.add(new RegExpRule(Brush.RegExpRule.multiLineCComments, "comments")); // multiline comments
        regExpRule.add(new RegExpRule("\\s*#.*", Pattern.MULTILINE, "preprocessor")); // preprocessor tags like #region and #endregion
        regExpRule.add(new RegExpRule(getKeywords("break case catch continue "
                + "default delete do else false  "
                + "for function if in instanceof "
                + "new null return super switch "
                + "this throw true try typeof var while with"), Pattern.MULTILINE, "keyword")); // keywords

        commonFileExtensionList = Arrays.asList(new String[]{"js", "es"});
    }
}
