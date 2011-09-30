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
 * Brush for syntax highlighter.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class Brush {

    /**
     * Regular expression rule list. It will be executed in sequence.
     */
    protected List<RegExpRule> regExpRuleList;
    /**
     * The list of common file extension for this language.
     */
    protected List<String> commonFileExtensionList;
    /**
     * HTML script RegExp. null means no HTML script RegExp for this brush.
     */
    protected HTMLScriptRegExp htmlScriptRegExp;

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
        this.regExpRuleList = new ArrayList<RegExpRule>(regExpRuleList);
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
     * The regular expression to determine which part of the HTML script is using this programming language.
     */
    public static class HTMLScriptRegExp {

        /**
         * Common HTML script RegExp.
         */
        public static final HTMLScriptRegExp phpScriptTags = new HTMLScriptRegExp("(?:&lt;|<)\\?=?", "\\?(?:&gt;|>)");
        /**
         * Common HTML script RegExp.
         */
        public static final HTMLScriptRegExp aspScriptTags = new HTMLScriptRegExp("(?:&lt;|<)%=?", "%(?:&gt;|>)");
        /**
         * Common HTML script RegExp.
         */
        public static final HTMLScriptRegExp scriptScriptTags = new HTMLScriptRegExp("(?:&lt;|<)\\s*script.*?(?:&gt;|>)", "(?:&lt;|<)\\/\\s*script\\s*(?:&gt;|>)");
        /**
         * The regular expression of the left tag.
         */
        protected String left;
        /**
         * The regular expression of the right tag.
         */
        protected String right;

        /**
         * Constructor.
         * @param left the regular expression of the left tag
         * @param right the regular expression of the right tag
         */
        public HTMLScriptRegExp(String left, String right) {
            this.left = left;
            this.right = right;
        }

        /**
         * Get the regular expression of the left tag.
         * @return the RegExp
         */
        public String getLeft() {
            return left;
        }

        /**
         * Set the regular expression of the left tag.
         * @param left the RegExp
         */
        public void setLeft(String left) {
            this.left = left;
        }

        /**
         * Get the regular expression of the right tag.
         * @return the RegExp
         */
        public String getRight() {
            return right;
        }

        /**
         * Set the regular expression of the right tag.
         * @param right the RegExp
         */
        public void setRight(String right) {
            this.right = right;
        }

        /**
         * Get the pattern of this HTML script RegExp.
         * Group 1 is the left tag, group 2 is the inner content, group 3 is the right tag.
         * @return the pattern, flags: CASE_INSENSITIVE and DOTALL
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
        /**
         * Common regular expression rule.
         */
        public static final Pattern singleLineCComments = Pattern.compile("\\/\\/.*$", Pattern.MULTILINE);
        /**
         * Common regular expression rule.
         */
        public static final Pattern singleLinePerlComments = Pattern.compile("#.*$", Pattern.MULTILINE);
        /**
         * Common regular expression rule.
         */
        public static final Pattern doubleQuotedString = Pattern.compile("\"([^\\\\\"\\n]|\\\\.)*\"");
        /**
         * Common regular expression rule.
         */
        public static final Pattern singleQuotedString = Pattern.compile("'([^\\\\'\\n]|\\\\.)*'");
        /**
         * Common regular expression rule.
         */
        public static final Pattern multiLineDoubleQuotedString = Pattern.compile("\"([^\\\\\"]|\\\\.)*\"", Pattern.DOTALL);
        /**
         * Common regular expression rule.
         */
        public static final Pattern multiLineSingleQuotedString = Pattern.compile("'([^\\\\']|\\\\.)*'", Pattern.DOTALL);
        /**
         * Common regular expression rule.
         */
        public static final Pattern xmlComments = Pattern.compile("\\w+:\\/\\/[\\w-.\\/?%&=:@;]*");
        /**
         * The compiled pattern.
         */
        protected Pattern pattern;
        /**
         * The key is the group number of the matched result.
         * <p>
         * The value can either be a string or a RegExpRule.
         * <ul>
         * <li>If it is a string, it should be one of the style key from {@link syntaxhighlighter.Theme}.<br />
         * The style will be applied to the 'strip of string related to the group number'.</li>
         * <li>If it is a RegExpRule, the 'strip of string related to the group number' will be applied to this RegExpRule for further operations/matching.</li>
         * </ul>
         * </p>
         */
        protected Map<Integer, Object> groupOperations;
        protected Boolean bold;

        /**
         * Constructor.
         * @param regExp the regular expression for this rule
         * @param styleKey the style key, the style to apply to the matched result
         */
        public RegExpRule(String regExp, String styleKey) {
            this(regExp, 0, styleKey);
        }

        /**
         * Constructor.
         * @param regExp the regular expression for this rule
         * @param regFlags the flags for the regular expression, see the flags in {@link java.util.regex.Pattern}
         * @param styleKey the style key, the style to apply to the matched result
         */
        public RegExpRule(String regExp, int regFlags, String styleKey) {
            this(Pattern.compile(regExp, regFlags), styleKey);
        }

        /**
         * Constructor.
         * @param pattern the compiled regular expression
         * @param styleKey the style key, the style to apply to the matched result
         */
        public RegExpRule(Pattern pattern, String styleKey) {
            this.pattern = pattern;
            this.groupOperations = new HashMap<Integer, Object>();
            groupOperations.put(0, styleKey);
        }

        /**
         * Get the compiled pattern
         * @return the pattern
         */
        public Pattern getPattern() {
            return pattern;
        }

        /**
         * Set the compiled pattern.
         * @param pattern the pattern
         */
        public void setPattern(Pattern pattern) {
            this.pattern = pattern;
        }

        /**
         * Get the string of the regular expression.
         * @return the string of the regular expression
         */
        public String getRegExp() {
            return pattern.pattern();
        }

        /**
         * Set the string of the regular expression.
         * @param regExp the string of the regular expression
         */
        public void setRegExp(String regExp) {
            pattern = Pattern.compile(regExp, pattern.flags());
        }

        /**
         * Get the flags of the regular expression.
         * @return the flags of the regular expression
         */
        public int getRegExpFlags() {
            return pattern.flags();
        }

        /**
         * Set the flags of the regular expression.
         * @param flags the flags, see the flags in {@link java.util.regex.Pattern}
         */
        public void setRegExpFlags(int flags) {
            pattern = Pattern.compile(pattern.pattern(), flags);
        }

        /**
         * Get the map of group operations. For more details, see {@link #groupOperations}.
         * @return a copy of the group operations map
         */
        public Map<Integer, Object> getGroupOperations() {
            return new HashMap<Integer, Object>(groupOperations);
        }

        /**
         * Set the map of group operations. For more details, see {@link #groupOperations}.
         * @param GroupOperations the group operations map
         */
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
