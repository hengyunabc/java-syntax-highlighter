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
public class BrushSass extends Brush {

    public BrushSass() {
        super();

        regExpRule.add(new RegExpRule(Brush.RegExpRule.multiLineCComments, "comments")); // multiline comments
        regExpRule.add(new RegExpRule(Brush.RegExpRule.singleLineCComments, "comments")); // singleline comments
        regExpRule.add(new RegExpRule(Brush.RegExpRule.doubleQuotedString, "string")); // double quoted strings
        regExpRule.add(new RegExpRule(Brush.RegExpRule.singleQuotedString, "string")); // single quoted strings
        regExpRule.add(new RegExpRule("\\#[a-fA-F0-9]{3,6}", "value")); // html colors
        regExpRule.add(new RegExpRule("\\b(-?\\d+)(\\.\\d+)?(px|em|pt|\\:|\\%|)\\b", "value")); // sizes
        regExpRule.add(new RegExpRule("\\$\\w+", "variable")); // variables
        regExpRule.add(new RegExpRule(getKeywords("!important !default"), "color3"));
        regExpRule.add(new RegExpRule(getKeywords("@import @extend @debug @warn @if @for @while @mixin @include"), "preprocessor"));
        regExpRule.add(new RegExpRule(getKeywordsCSS("ascent azimuth background-attachment background-color background-image background-position "
                + "background-repeat background baseline bbox border-collapse border-color border-spacing border-style border-top "
                + "border-right border-bottom border-left border-top-color border-right-color border-bottom-color border-left-color "
                + "border-top-style border-right-style border-bottom-style border-left-style border-top-width border-right-width "
                + "border-bottom-width border-left-width border-width border bottom cap-height caption-side centerline clear clip color "
                + "content counter-increment counter-reset cue-after cue-before cue cursor definition-src descent direction display "
                + "elevation empty-cells float font-size-adjust font-family font-size font-stretch font-style font-variant font-weight font "
                + "height left letter-spacing line-height list-style-image list-style-position list-style-type list-style margin-top "
                + "margin-right margin-bottom margin-left margin marker-offset marks mathline max-height max-width min-height min-width orphans "
                + "outline-color outline-style outline-width outline overflow padding-top padding-right padding-bottom padding-left padding page "
                + "page-break-after page-break-before page-break-inside pause pause-after pause-before pitch pitch-range play-during position "
                + "quotes right richness size slope src speak-header speak-numeral speak-punctuation speak speech-rate stemh stemv stress "
                + "table-layout text-align top text-decoration text-indent text-shadow text-transform unicode-bidi unicode-range units-per-em "
                + "vertical-align visibility voice-family volume white-space widows width widths word-spacing x-height z-index"), Pattern.MULTILINE, "keyword"));
        regExpRule.add(new RegExpRule(getValuesCSS("above absolute all always aqua armenian attr aural auto avoid baseline behind below bidi-override black blink block blue bold bolder "
                + "both bottom braille capitalize caption center center-left center-right circle close-quote code collapse compact condensed "
                + "continuous counter counters crop cross crosshair cursive dashed decimal decimal-leading-zero digits disc dotted double "
                + "embed embossed e-resize expanded extra-condensed extra-expanded fantasy far-left far-right fast faster fixed format fuchsia "
                + "gray green groove handheld hebrew help hidden hide high higher icon inline-table inline inset inside invert italic "
                + "justify landscape large larger left-side left leftwards level lighter lime line-through list-item local loud lower-alpha "
                + "lowercase lower-greek lower-latin lower-roman lower low ltr marker maroon medium message-box middle mix move narrower "
                + "navy ne-resize no-close-quote none no-open-quote no-repeat normal nowrap n-resize nw-resize oblique olive once open-quote outset "
                + "outside overline pointer portrait pre print projection purple red relative repeat repeat-x repeat-y rgb ridge right right-side "
                + "rightwards rtl run-in screen scroll semi-condensed semi-expanded separate se-resize show silent silver slower slow "
                + "small small-caps small-caption smaller soft solid speech spell-out square s-resize static status-bar sub super sw-resize "
                + "table-caption table-cell table-column table-column-group table-footer-group table-header-group table-row table-row-group teal "
                + "text-bottom text-top thick thin top transparent tty tv ultra-condensed ultra-expanded underline upper-alpha uppercase upper-latin "
                + "upper-roman url visible wait white wider w-resize x-fast x-high x-large x-loud x-low x-slow x-small x-soft xx-large xx-small yellow"), "value"));
        regExpRule.add(new RegExpRule(getKeywords("[mM]onospace [tT]ahoma [vV]erdana [aA]rial [hH]elvetica [sS]ans-serif [sS]erif [cC]ourier mono sans serif"), "value"));

        commonFileExtensionList = Arrays.asList(new String[]{"sass", "scss"});
    }

    protected static String getKeywordsCSS(String str) {
        return "\\b([a-z_]|)" + str.replaceAll("\\s", "(?=:)\\\\b|\\\\b([a-z_\\\\*]|\\\\*|)") + "(?=:)\\b";
    }

    protected static String getValuesCSS(String str) {
        return "\\b" + str.replaceAll("\\s", "(?!-)(?!:)\\\\b|\\\\b()") + ":\\b";
    }
}
