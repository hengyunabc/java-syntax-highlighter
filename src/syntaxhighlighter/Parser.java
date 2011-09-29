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
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.Segment;
import syntaxhighlighter.Brush.RegExpRule;

/**
 * The parser of the syntax highlighter.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class Parser {

    private final List<Brush> htmlScriptBrushList;

    public Parser() {
        htmlScriptBrushList = new ArrayList<Brush>();
    }

    protected void addMatch(Map<Integer, List<MatchResult>> matches, MatchResult match) {
        List<MatchResult> matchList = matches.get(match.getOffset());
        if (matchList == null) {
            matchList = new ArrayList<MatchResult>();
            matches.put(match.getOffset(), matchList);
        }
        matchList.add(match);
    }

    protected void removeMatches(Map<Integer, List<MatchResult>> matches, int start, int end) {
        for (int offset : matches.keySet()) {
            List<MatchResult> offsetMatches = matches.get(offset);
            ListIterator<MatchResult> iterator = offsetMatches.listIterator();
            while (iterator.hasNext()) {
                MatchResult match = iterator.next();
                int _start = match.getOffset(), _end = _start + match.getLength();
                if (_start >= end || _end <= start) {
                    continue;
                }
                if (_start >= start && _end <= end) {
                    iterator.remove();
                } else if (_end <= end) {
                    iterator.set(new MatchResult(_start, start - _start, match.getStyleKey(), match.isBold()));
                } else if (_start >= start) {
                    iterator.set(new MatchResult(end, _end - end, match.getStyleKey(), match.isBold()));
                } else {
                    System.out.println("Parser:removeMatches(): logic error");
                    // error
                }
            }
        }
    }

    protected Map<String, List<MatchResult>> getStyle(Map<Integer, List<MatchResult>> matches) {
        Map<String, List<MatchResult>> returnMap = new HashMap<String, List<MatchResult>>();

        int offsetRecord = 0;
        for (int offset : matches.keySet()) {
            if (offsetRecord > offset) {
                continue;
            }

            List<MatchResult> offsetMatches = matches.get(offset);
            int maxLength = -1;
            MatchResult maxMatch = null;
            for (MatchResult match : offsetMatches) {
                if (match.getLength() > maxLength) {
                    maxLength = match.getLength();
                    maxMatch = match;
                }
            }

            if (maxMatch == null) {
                continue;
            }

            offsetRecord = maxMatch.getOffset() + maxMatch.getLength();

            List<MatchResult> styleList = returnMap.get(maxMatch.getStyleKey());
            if (styleList == null) {
                styleList = new ArrayList<MatchResult>();
                returnMap.put(maxMatch.getStyleKey(), styleList);
            }
            styleList.add(maxMatch);
        }

        return returnMap;
    }

    public Map<String, List<MatchResult>> parse(Brush brush, boolean htmlScript, char[] content, int offset, int length) {
        Map<Integer, List<MatchResult>> matches = new TreeMap<Integer, List<MatchResult>>();
        return parse(matches, brush, htmlScript, content, offset, length);
    }

    protected Map<String, List<MatchResult>> parse(Map<Integer, List<MatchResult>> matches, Brush brush, boolean htmlScript, char[] content, int offset, int length) {
        List<RegExpRule> regExpRuleList = brush.getRegExpRuleList();
        for (RegExpRule regExpRule : regExpRuleList) {
            parse(matches, regExpRule, content, offset, length);
        }

        if (htmlScript) {
            synchronized (htmlScriptBrushList) {
                for (Brush htmlScriptBrush : htmlScriptBrushList) {
                    Pattern _pattern = htmlScriptBrush.getHTMLScriptRegExp().getpattern();
                    Matcher matcher = _pattern.matcher(new Segment(content, offset, length));
                    while (matcher.find()) {
                        removeMatches(matches, matcher.start() + offset, matcher.end() + offset);

                        int start = matcher.start(1) + offset, end = matcher.end(1) + offset;
                        addMatch(matches, new MatchResult(start, end - start, "script", false));

                        start = matcher.start(2) + offset;
                        end = matcher.end(2) + offset;
                        parse(matches, htmlScriptBrush, false, content, start, end - start);

                        start = matcher.start(3) + offset;
                        end = matcher.end(3) + offset;
                        addMatch(matches, new MatchResult(start, end - start, "script", false));
                    }
                }
            }
        }

        return getStyle(matches);
    }

    protected void parse(Map<Integer, List<MatchResult>> matches, RegExpRule regExpRule, char[] content, int offset, int length) {
        Pattern regExpPattern = regExpRule.getPattern();
        Map<Integer, Object> groupOperations = regExpRule.getGroupOperations();

        Matcher matcher = regExpPattern.matcher(new Segment(content, offset, length));
        while (matcher.find()) {
            for (int groupId : groupOperations.keySet()) {
                Object operation = groupOperations.get(groupId);
                if (operation instanceof String) {
                    int start = matcher.start(groupId) + offset, end = matcher.end(groupId) + offset;
                    if (start == -1 || end == -1) {
                        continue;
                    }
                    addMatch(matches, new MatchResult(start, end - start, (String) operation, regExpRule.getBold()));
                } else {
                    int start = matcher.start() + offset, end = matcher.end() + offset;
                    if (start == -1 || end == -1) {
                        continue;
                    }
                    parse(matches, (RegExpRule) operation, content, start, end - start);
                }
            }
        }
    }

    public List<Brush> getHTMLScriptBrushList() {
        List<Brush> returnList;
        synchronized (htmlScriptBrushList) {
            returnList = new ArrayList<Brush>(htmlScriptBrushList);
        }
        return returnList;
    }

    public void setHTMLScriptBrushList(List<Brush> htmlScriptBrushList) {
        synchronized (this.htmlScriptBrushList) {
            this.htmlScriptBrushList.clear();
            this.htmlScriptBrushList.addAll(htmlScriptBrushList);
        }
    }

    public void addHTMLScriptBrush(Brush brush) {
        htmlScriptBrushList.add(brush);
    }

    public static class MatchResult {

        private int offset;
        private int length;
        private String styleKey;
        private Boolean bold;

        public MatchResult(int offset, int length, String styleKey, Boolean bold) {
            this.offset = offset;
            this.length = length;
            this.styleKey = styleKey;
            this.bold = bold;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public String getStyleKey() {
            return styleKey;
        }

        public Boolean isBold() {
            return bold;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            sb.append("[");
            sb.append(offset);
            sb.append(", ");
            sb.append(length);
            sb.append(", ");
            sb.append(styleKey);
            sb.append(", ");
            sb.append(bold);
            sb.append("]");

            return sb.toString();
        }
    }
}
