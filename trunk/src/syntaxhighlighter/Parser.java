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
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import syntaxhighlighter.Brush.RegExpRule;
import syntaxhighlighter.Theme.Style;

/**
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class Parser {

    private Brush brush;
    private Theme theme;

    public Parser(Brush brush, Theme theme) {
        this.brush = brush;
        this.theme = theme;
    }

    public List<Segment> parse(String content) {
        List<Segment> results = new ArrayList<Segment>();
        results.add(new Segment(content));

        StringBuffer sb;

        List<RegExpRule> regExpRuleList = brush.getRegExpRuleList();
        for (RegExpRule regExpRule : regExpRuleList) {
            Pattern regExpPattern = regExpRule.getPattern();
            List<String> affectedStyleKeyList = regExpRule.getAffectedStyleKeyList();
            Map<Integer, String> matchesToStyleKey = regExpRule.getMatchesToStyleKey();

            for (int i = 0, iEnd = results.size(); i < iEnd; i++) {
                Segment segment = results.get(i);
                if (affectedStyleKeyList.indexOf(segment.getStyleKey()) != -1) {
                    List<Segment> _segmentList = new ArrayList<Segment>();

                    Matcher matcher = regExpPattern.matcher(segment.getContent());
                    while (matcher.find()) {
                        sb = new StringBuffer();
                        matcher.appendReplacement(sb, "");
                        if (sb.length() != 0) {
                            _segmentList.add(new Segment(sb.toString()));
                        }

                        String _styleKey = matchesToStyleKey.get(0);
                        if (matchesToStyleKey.size() == 1 && _styleKey != null) {
                            Style _style = regExpRule.setStyle(theme.getStyle(_styleKey));

                            _segmentList.add(new Segment(matcher.group(0), _styleKey, _style));
                        } else {
                            for (int j = 1, jEnd = matcher.groupCount(); j <= jEnd; j++) {
                                _styleKey = matchesToStyleKey.get(j);
                                Style _style = regExpRule.setStyle(theme.getStyle(_styleKey));

                                String matchedGroup = matcher.group(j);
                                if (matchedGroup != null) {
                                    _segmentList.add(new Segment(matcher.group(j), _styleKey, _style));
                                }
                            }
                        }
                    }
                    sb = new StringBuffer();
                    matcher.appendTail(sb);
                    if (sb.length() != 0) {
                        _segmentList.add(new Segment(sb.toString()));
                    }

                    results.remove(segment);
                    iEnd += _segmentList.size() - 1;
                    for (Segment _segment : _segmentList) {
                        results.add(i, _segment);
                        i++;
                    }
                }
            }
        }

        return results;
    }

    public Brush getBrush() {
        return brush;
    }

    public void setBrush(Brush brush) {
        this.brush = brush;
    }

    public Theme getTheme() {
        return theme;
    }

    public class Segment {

        private String styleKey;
        private Style style;
        private String content;

        public Segment(String content) {
            styleKey = "";
            style = theme.getPlain();
            this.content = content;
        }

        public Segment(String content, String styleKey, Style style) {
            this.styleKey = styleKey;
            this.style = style;
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Style getStyle() {
            return style;
        }

        public void setStyle(Style style) {
            this.style = style;
        }

        public String getStyleKey() {
            return styleKey;
        }

        public void setStyleKey(String styleKey) {
            this.styleKey = styleKey;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            sb.append("<");
            sb.append(styleKey);
            sb.append(", ");
            sb.append(style);
            sb.append(": ");
            sb.append(content);
            sb.append(">");

            return sb.toString();
        }
    }
}
