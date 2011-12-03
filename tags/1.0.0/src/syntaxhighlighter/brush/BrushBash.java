/**
 * This is part of the Java SyntaxHighlighter.
 * 
 * It is distributed under MIT license. See the file 'readme.txt' for
 * information on usage and redistribution of this file, and for a
 * DISCLAIMER OF ALL WARRANTIES.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
package syntaxhighlighter.brush;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import syntaxhighlighter.Brush;
import syntaxhighlighter.Brush.RegExpRule;

/**
 * Bash brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class BrushBash extends Brush {

    public BrushBash() {
        super();

        String keywords = "if fi then elif else for do done until while break continue case function return in eq ne ge le";
        String commands = "alias apropos awk basename bash bc bg builtin bzip2 cal cat cd cfdisk chgrp chmod chown chroot"
                + "cksum clear cmp comm command cp cron crontab csplit cut date dc dd ddrescue declare df "
                + "diff diff3 dig dir dircolors dirname dirs du echo egrep eject enable env ethtool eval "
                + "exec exit expand export expr false fdformat fdisk fg fgrep file find fmt fold format "
                + "free fsck ftp gawk getopts grep groups gzip hash head history hostname id ifconfig "
                + "import install join kill less let ln local locate logname logout look lpc lpr lprint "
                + "lprintd lprintq lprm ls lsof make man mkdir mkfifo mkisofs mknod more mount mtools "
                + "mv netstat nice nl nohup nslookup open op passwd paste pathchk ping popd pr printcap "
                + "printenv printf ps pushd pwd quota quotacheck quotactl ram rcp read readonly renice "
                + "remsync rm rmdir rsync screen scp sdiff sed select seq set sftp shift shopt shutdown "
                + "sleep sort source split ssh strace su sudo sum symlink sync tail tar tee test time "
                + "times touch top traceroute trap tr true tsort tty type ulimit umask umount unalias "
                + "uname unexpand uniq units unset unshar useradd usermod users uuencode uudecode v vdir "
                + "vi watch wc whereis which who whoami Wget xargs yes";

        List<RegExpRule> _regExpRuleList = new ArrayList<RegExpRule>();
        RegExpRule _regExpRule = new RegExpRule("^#!.*$", Pattern.MULTILINE, "preprocessor");
        _regExpRule.setBold(true);
        _regExpRuleList.add(_regExpRule);
        _regExpRuleList.add(new RegExpRule("\\/[\\w-\\/]+", Pattern.MULTILINE, "plain"));
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.singleLinePerlComments, "comments")); // one line comments
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.doubleQuotedString, "string")); // double quoted strings
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.singleQuotedString, "string")); // single quoted strings
        _regExpRuleList.add(new RegExpRule(getKeywords(keywords), Pattern.MULTILINE, "keyword")); // keywords
        _regExpRuleList.add(new RegExpRule(getKeywords(commands), Pattern.MULTILINE, "functions")); // commands
        setRegExpRuleList(_regExpRuleList);

        setCommonFileExtensionList(Arrays.asList("sh"));
    }
}
