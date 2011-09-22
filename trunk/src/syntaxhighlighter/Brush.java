/**
 * This is part of the Java SyntaxHighlighter.
 * 
 * It is distributed under MIT license. See the file 'readme.txt' for
 * information on usage and redistribution of this file, and for a
 * DISCLAIMER OF ALL WARRANTIES.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
package syntaxhighlighter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import syntaxhighlighter.Theme.Style;

/**
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class Brush {

    protected List<RegExpRule> regExpRule;
    protected List<String> commonFileExtensionList;

    public Brush() {
        regExpRule = new ArrayList<RegExpRule>();
        commonFileExtensionList = new ArrayList<String>();
    }

    public List<RegExpRule> getRegExpRuleList() {
        return new ArrayList<RegExpRule>(regExpRule);
    }

    public List<String> getCommonFileExtensionList() {
        return new ArrayList<String>(commonFileExtensionList);
    }

    public void setCommonFileExtensionList(List<String> commonFileExtensionList) {
        this.commonFileExtensionList = new ArrayList<String>(commonFileExtensionList);
    }

    protected static String getKeywords(String str) {
        return "\\b(?:" + str.replaceAll("^\\s+|\\s+$", "").replaceAll("\\s+", "|") + ")\\b";
    }

    public static class RegExpRule {

        public static final Pattern multiLineCComments = Pattern.compile("\\/\\*[\\s\\S]*?\\*\\/", Pattern.MULTILINE);
        public static final Pattern singleLineCComments = Pattern.compile("\\/\\/.*$", Pattern.MULTILINE);
        public static final Pattern singleLinePerlComments = Pattern.compile("#.*$", Pattern.MULTILINE);
        public static final Pattern doubleQuotedString = Pattern.compile("\"([^\\\\\"\\n]|\\\\.)*\"");
        public static final Pattern singleQuotedString = Pattern.compile("'([^\\\\'\\n]|\\\\.)*'");
        public static final Pattern multiLineDoubleQuotedString = Pattern.compile("\"([^\\\\\"]|\\\\.)*\"", Pattern.DOTALL);
        public static final Pattern multiLineSingleQuotedString = Pattern.compile("'([^\\\\']|\\\\.)*'", Pattern.DOTALL);
        public static final Pattern xmlComments = Pattern.compile("\\w+:\\/\\/[\\w-.\\/?%&=:@;]*");
        //
        private int regFlags;
        private Pattern pattern;
        private List<String> affectedStyleKeyList;
        private Map<Integer, String> matchesToStyleKey;
        private Boolean bold;

        public RegExpRule(String regExp, String styleKey) {
            this(regExp, 0, styleKey);
        }

        public RegExpRule(String regExp, int regFlags, String styleKey) {
            this(Pattern.compile(regExp, regFlags), styleKey);
            this.regFlags = regFlags;
        }

        public RegExpRule(Pattern pattern, String styleKey) {
            this.pattern = pattern;
            this.matchesToStyleKey = new HashMap<Integer, String>();
            matchesToStyleKey.put(0, styleKey);
            regFlags = 0;
            affectedStyleKeyList = Arrays.asList(new String[]{""});
        }

        public Style setStyle(Style style) {
            if (getBold() != null) {
                style.setBold(getBold());
            }
            return style;
        }

        public int getRegFlags() {
            return regFlags;
        }

        public void setRegFlags(int regFlags) {
            this.regFlags = regFlags;
            pattern = Pattern.compile(pattern.pattern(), regFlags);
        }

        public Pattern getPattern() {
            return pattern;
        }

        public void setPattern(Pattern pattern) {
            this.pattern = pattern;
        }

        public String getRegExp() {
            return pattern.pattern();
        }

        public void setRegExp(String regExp) {
            pattern = Pattern.compile(regExp, regFlags);
        }

        public Map<Integer, String> getMatchesToStyleKey() {
            return new HashMap<Integer, String>(matchesToStyleKey);
        }

        public void setMatchesToStyleKey(Map<Integer, String> matchesToStyleKey) {
            this.matchesToStyleKey = new HashMap<Integer, String>(matchesToStyleKey);
        }

        public List<String> getAffectedStyleKeyList() {
            return new ArrayList<String>(affectedStyleKeyList);
        }

        public void setAffectedStyleKeyList(List<String> affectedStyleKeyList) {
            this.affectedStyleKeyList = new ArrayList<String>(affectedStyleKeyList);
        }

        public Boolean getBold() {
            return bold;
        }

        public void setBold(Boolean bold) {
            this.bold = bold;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            sb.append(getClass().getName());
            sb.append(": ");
            sb.append("regExp: ");
            sb.append(getRegExp());
            sb.append(", ");
            sb.append("regFlags: ");
            sb.append(getRegFlags());
            sb.append(", ");
            sb.append("affectedStyleKeyList: ");
            sb.append(getAffectedStyleKeyList());
            sb.append(", ");
            sb.append("matchesToStyleKey: ");
            sb.append(getMatchesToStyleKey());
            sb.append(", ");
            sb.append("bold: ");
            sb.append(getBold());

            return sb.toString();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(getClass().getName());
        sb.append("\n");
        sb.append("rule count: ");
        sb.append(regExpRule.size());
        for (int i = 0, iEnd = regExpRule.size(); i < iEnd; i++) {
            RegExpRule rule = regExpRule.get(i);
            sb.append("\n");
            sb.append(i);
            sb.append(": ");
            sb.append(rule.toString());
        }

        return sb.toString();
    }
}
