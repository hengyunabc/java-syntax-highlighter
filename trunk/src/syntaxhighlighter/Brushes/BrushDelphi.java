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
public class BrushDelphi extends Brush {

    public BrushDelphi() {
        super();

        regExpRule.add(new RegExpRule("\\(\\*[\\s\\S]*?\\*\\)", Pattern.MULTILINE, "comments")); // multiline comments (* *)
        regExpRule.add(new RegExpRule("\\{(?!\\$)[\\s\\S]*?\\}", Pattern.MULTILINE, "comments")); // multiline comments { }
        regExpRule.add(new RegExpRule(Brush.RegExpRule.singleLineCComments, "comments")); // one line
        regExpRule.add(new RegExpRule(Brush.RegExpRule.singleQuotedString, "string")); // strings
        regExpRule.add(new RegExpRule("\\{\\$[a-zA-Z]+ .+\\}", "color1")); // compiler Directives and Region tags
        regExpRule.add(new RegExpRule("\\b[\\d\\.]+\\b", "value")); // numbers 12345
        regExpRule.add(new RegExpRule("\\$[a-zA-Z0-9]+\\b", "value")); // numbers $F5D3
        regExpRule.add(new RegExpRule(getKeywords("abs addr and ansichar ansistring array as asm begin boolean byte cardinal "
                + "case char class comp const constructor currency destructor div do double "
                + "downto else end except exports extended false file finalization finally "
                + "for function goto if implementation in inherited int64 initialization "
                + "integer interface is label library longint longword mod nil not object "
                + "of on or packed pansichar pansistring pchar pcurrency pdatetime pextended "
                + "pint64 pointer private procedure program property pshortstring pstring "
                + "pvariant pwidechar pwidestring protected public published raise real real48 "
                + "record repeat set shl shortint shortstring shr single smallint string then "
                + "threadvar to true try type unit until uses val var varirnt while widechar "
                + "widestring with word write writeln xor"), Pattern.MULTILINE | Pattern.CASE_INSENSITIVE, "keyword")); // keyword

        commonFileExtensionList = Arrays.asList(new String[]{"pas"});
    }
}
