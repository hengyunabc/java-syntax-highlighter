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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Brush for SyntaxHighlighter.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class Brush {

    /**
     * Regular expression rule list.
     */
    private List<RegExpRule> regExpRuleList;
    /**
     * Common file extension list.
     */
    private List<String> commonFileExtensionList;
    /**
     * HTML script RegExp. null means no HTML script RegExp for this brush.
     */
    private HTMLScriptRegExp htmlScriptRegExp;

    /**
     * Constructor.
     */
    public Brush() {
        regExpRuleList = new ArrayList<RegExpRule>();
        commonFileExtensionList = new ArrayList<String>();
        htmlScriptRegExp = null;
    }

    /**
     * Get the regular expression rule list.
     * @return a copy of the list
     */
    public List<RegExpRule> getRegExpRuleList() {
        return new ArrayList<RegExpRule>(regExpRuleList);
    }

    /**
     * Set the regular expression rule list.
     * @param regExpRuleList the list
     */
    public void setRegExpRuleList(List<RegExpRule> regExpRuleList) {
        this.regExpRuleList = regExpRuleList;
    }

    /**
     * Get the HTML script RegExp.
     * @return the HTML script RegExp, null means not defined
     */
    public HTMLScriptRegExp getHTMLScriptRegExp() {
        return htmlScriptRegExp;
    }

    /**
     * Set the HTML script RegExp.
     * @param htmlScriptRegExp the RegExp
     */
    public void setHTMLScriptRegExp(HTMLScriptRegExp htmlScriptRegExp) {
        this.htmlScriptRegExp = htmlScriptRegExp;
    }

    /**
     * Get the common file extension list.
     * @return a copy of the list
     */
    public List<String> getCommonFileExtensionList() {
        return new ArrayList<String>(commonFileExtensionList);
    }

    /**
     * Set the common file extension list.
     * @param commonFileExtensionList the list
     */
    public void setCommonFileExtensionList(List<String> commonFileExtensionList) {
        this.commonFileExtensionList = new ArrayList<String>(commonFileExtensionList);
    }

    /**
     * Similar function in SyntaxHighlighter for making string of keywords into regular expression.
     */
    protected static String getKeywords(String str) {
        return "\\b(?:" + str.replaceAll("^\\s+|\\s+$", "").replaceAll("\\s+", "|") + ")\\b";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(getClass().getName());
        sb.append("\n");
        sb.append("rule count: ");
        sb.append(regExpRuleList.size());
        for (int i = 0, iEnd = regExpRuleList.size(); i < iEnd; i++) {
            RegExpRule rule = regExpRuleList.get(i);
            sb.append("\n");
            sb.append(i);
            sb.append(": ");
            sb.append(rule.toString());
        }
        sb.append("\n");
        sb.append("common file extension list: ");
        sb.append(commonFileExtensionList);
        sb.append("\n");
        sb.append("HTML Script RegExp: ");
        sb.append(htmlScriptRegExp);

        return sb.toString();
    }

    /**
     * The regular expression of the HTML script.
     */
    public static class HTMLScriptRegExp {

        /**
         * Common HTML script RegExp.
         */
        public static final HTMLScriptRegExp phpScriptTags = new HTMLScriptRegExp("(?:&lt;|<)\\?=?", "\\?(?:&gt;|>)");
        public static final HTMLScriptRegExp aspScriptTags = new HTMLScriptRegExp("(?:&lt;|<)%=?", "%(?:&gt;|>)");
        public static final HTMLScriptRegExp scriptScriptTags = new HTMLScriptRegExp("(?:&lt;|<)\\s*script.*?(?:&gt;|>)", "(?:&lt;|<)\\/\\s*script\\s*(?:&gt;|>)");
        /**
         * The regular expression of the left tag.
         */
        private String left;
        /**
         * The regular expression of the right tag.
         */
        private String right;

        /**
         * Constructor
         * @param left the regular expression of the left tag
         * @param right the regular expression of the right tag
         */
        public HTMLScriptRegExp(String left, String right) {
            this.left = left;
            this.right = right;
        }

        /**
         * Get the regular expression of the left tag
         * @return the RegExp
         */
        public String getLeft() {
            return left;
        }

        /**
         * Set the regular expression of the left tag
         * @param left the RegExp
         */
        public void setLeft(String left) {
            this.left = left;
        }

        /**
         * Get the regular expression of the right tag
         * @return the RegExp
         */
        public String getRight() {
            return right;
        }

        /**
         * Set the regular expression of the right tag
         * @param right the RegExp
         */
        public void setRight(String right) {
            this.right = right;
        }

        /**
         * Get the pattern of this HTML script RegExp.
         * Group 1 is the left tag, group 2 is the inner content, group 3 is the right tag.
         * @return the pattern
         */
        public Pattern getpattern() {
            return Pattern.compile("(" + left + ")(.*?)(" + right + ")", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            sb.append(getClass().getName());
            sb.append(":[");
            sb.append("left: ");
            sb.append(left);
            sb.append("right: ");
            sb.append(right);
            sb.append("]");

            return sb.toString();
        }
    }

    /**
     * The regular expression rule.
     */
    public static class RegExpRule {

        /**
         * Common regular expression rule.
         */
        public static final Pattern multiLineCComments = Pattern.compile("\\/\\*[\\s\\S]*?\\*\\/", Pattern.MULTILINE);
        public static final Pattern singleLineCComments = Pattern.compile("\\/\\/.*$", Pattern.MULTILINE);
        public static final Pattern singleLinePerlComments = Pattern.compile("#.*$", Pattern.MULTILINE);
        public static final Pattern doubleQuotedString = Pattern.compile("\"([^\\\\\"\\n]|\\\\.)*\"");
        public static final Pattern singleQuotedString = Pattern.compile("'([^\\\\'\\n]|\\\\.)*'");
        public static final Pattern multiLineDoubleQuotedString = Pattern.compile("\"([^\\\\\"]|\\\\.)*\"", Pattern.DOTALL);
        public static final Pattern multiLineSingleQuotedString = Pattern.compile("'([^\\\\']|\\\\.)*'", Pattern.DOTALL);
        public static final Pattern xmlComments = Pattern.compile("\\w+:\\/\\/[\\w-.\\/?%&=:@;]*");
        //
        private Pattern pattern;
        private Map<Integer, Object> groupOperations;
        private Boolean bold;

        public RegExpRule(String regExp, String styleKey) {
            this(regExp, 0, styleKey);
        }

        public RegExpRule(String regExp, int regFlags, String styleKey) {
            this(Pattern.compile(regExp, regFlags), styleKey);
        }

        public RegExpRule(Pattern pattern, String styleKey) {
            this.pattern = pattern;
            this.groupOperations = new HashMap<Integer, Object>();
            groupOperations.put(0, styleKey);
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
            pattern = Pattern.compile(regExp, pattern.flags());
        }

        public int getRegExpFlags() {
            return pattern.flags();
        }

        public void setRegExpFlags(int flags) {
            pattern = Pattern.compile(pattern.pattern(), flags);
        }

        public Map<Integer, Object> getGroupOperations() {
            return new HashMap<Integer, Object>(groupOperations);
        }

        public void setGroupOperations(Map<Integer, Object> GroupOperations) {
            this.groupOperations = new HashMap<Integer, Object>(GroupOperations);
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
            sb.append(getRegExpFlags());
            sb.append(", ");
            sb.append("getGroupOperations: ");
            sb.append(getGroupOperations());
            sb.append(", ");
            sb.append("bold: ");
            sb.append(getBold());

            return sb.toString();
        }
    }
}
