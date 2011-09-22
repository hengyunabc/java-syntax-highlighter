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
public class BrushVb extends Brush {

    public BrushVb() {
        super();

        regExpRule.add(new RegExpRule("'.*$", Pattern.MULTILINE, "comments")); // one line comments
        regExpRule.add(new RegExpRule(Brush.RegExpRule.doubleQuotedString, "string")); // strings
        regExpRule.add(new RegExpRule("^\\s*#.*$", Pattern.MULTILINE, "preprocessor")); // preprocessor tags like #region and #endregion
        regExpRule.add(new RegExpRule(getKeywords("AddHandler AddressOf AndAlso Alias And Ansi As Assembly Auto "
                + "Boolean ByRef Byte ByVal Call Case Catch CBool CByte CChar CDate "
                + "CDec CDbl Char CInt Class CLng CObj Const CShort CSng CStr CType "
                + "Date Decimal Declare Default Delegate Dim DirectCast Do Double Each "
                + "Else ElseIf End Enum Erase Error Event Exit False Finally For Friend "
                + "Function Get GetType GoSub GoTo Handles If Implements Imports In "
                + "Inherits Integer Interface Is Let Lib Like Long Loop Me Mod Module "
                + "MustInherit MustOverride MyBase MyClass Namespace New Next Not Nothing "
                + "NotInheritable NotOverridable Object On Option Optional Or OrElse "
                + "Overloads Overridable Overrides ParamArray Preserve Private Property "
                + "Protected Public RaiseEvent ReadOnly ReDim REM RemoveHandler Resume "
                + "Return Select Set Shadows Shared Short Single Static Step Stop String "
                + "Structure Sub SyncLock Then Throw To True Try TypeOf Unicode Until "
                + "Variant When While With WithEvents WriteOnly Xor"), Pattern.MULTILINE, "keyword")); // vb keyword

        commonFileExtensionList = Arrays.asList(new String[]{"vb", "vbs"});
    }
}
