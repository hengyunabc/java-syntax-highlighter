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
 * SQL brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class BrushSql extends Brush {

    public BrushSql() {
        super();

        List<RegExpRule> _regExpRuleList = new ArrayList<RegExpRule>();
        _regExpRuleList.add(new RegExpRule("--(.*)$", Pattern.MULTILINE, "comments")); // one line and multiline comments
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.multiLineDoubleQuotedString, "string")); // double quoted strings
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.multiLineSingleQuotedString, "string")); // single quoted strings
        _regExpRuleList.add(new RegExpRule(getKeywords("abs avg case cast coalesce convert count current_timestamp "
                + "current_user day isnull left lower month nullif replace right "
                + "session_user space substring sum system_user upper user year"), Pattern.MULTILINE | Pattern.CASE_INSENSITIVE, "color2")); // functions
        _regExpRuleList.add(new RegExpRule(getKeywords("all and any between cross in join like not null or outer some"), Pattern.MULTILINE | Pattern.CASE_INSENSITIVE, "color1")); // operators and such
        _regExpRuleList.add(new RegExpRule(getKeywords("absolute action add after alter as asc at authorization begin bigint "
                + "binary bit by cascade char character check checkpoint close collate "
                + "column commit committed connect connection constraint contains continue "
                + "create cube current current_date current_time cursor database date "
                + "deallocate dec decimal declare default delete desc distinct double drop "
                + "dynamic else end end-exec escape except exec execute false fetch first "
                + "float for force foreign forward free from full function global goto grant "
                + "group grouping having hour ignore index inner insensitive insert instead "
                + "int integer intersect into is isolation key last level load local max min "
                + "minute modify move name national nchar next no numeric of off on only "
                + "open option order out output partial password precision prepare primary "
                + "prior privileges procedure public read real references relative repeatable "
                + "restrict return returns revoke rollback rollup rows rule schema scroll "
                + "second section select sequence serializable set size smallint static "
                + "statistics table temp temporary then time timestamp to top transaction "
                + "translation trigger true truncate uncommitted union unique update values "
                + "varchar varying view when where with work"), Pattern.MULTILINE | Pattern.CASE_INSENSITIVE, "keyword"));// keyword
        setRegExpRuleList(_regExpRuleList);

        setCommonFileExtensionList(Arrays.asList("sql"));
    }
}
