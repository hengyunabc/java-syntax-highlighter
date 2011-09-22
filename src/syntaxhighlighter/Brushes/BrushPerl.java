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
public class BrushPerl extends Brush {

    public BrushPerl() {
        super();

        // Contributed by David Simmons-Duffin and Marty Kube
        regExpRule.add(new RegExpRule("#[^!].*$", Pattern.MULTILINE, "comments"));
        regExpRule.add(new RegExpRule("^\\s*#!.*$", Pattern.MULTILINE, "preprocessor")); // shebang
        regExpRule.add(new RegExpRule(Brush.RegExpRule.doubleQuotedString, "string"));
        regExpRule.add(new RegExpRule(Brush.RegExpRule.singleQuotedString, "string"));
        regExpRule.add(new RegExpRule("(\\$|@|%)\\w+", "variable"));
        regExpRule.add(new RegExpRule(getKeywords("abs accept alarm atan2 bind binmode chdir chmod chomp chop chown chr "
                + "chroot close closedir connect cos crypt defined delete each endgrent "
                + "endhostent endnetent endprotoent endpwent endservent eof exec exists "
                + "exp fcntl fileno flock fork format formline getc getgrent getgrgid "
                + "getgrnam gethostbyaddr gethostbyname gethostent getlogin getnetbyaddr "
                + "getnetbyname getnetent getpeername getpgrp getppid getpriority "
                + "getprotobyname getprotobynumber getprotoent getpwent getpwnam getpwuid "
                + "getservbyname getservbyport getservent getsockname getsockopt glob "
                + "gmtime grep hex index int ioctl join keys kill lc lcfirst length link "
                + "listen localtime lock log lstat map mkdir msgctl msgget msgrcv msgsnd "
                + "oct open opendir ord pack pipe pop pos print printf prototype push "
                + "quotemeta rand read readdir readline readlink readpipe recv rename "
                + "reset reverse rewinddir rindex rmdir scalar seek seekdir select semctl "
                + "semget semop send setgrent sethostent setnetent setpgrp setpriority "
                + "setprotoent setpwent setservent setsockopt shift shmctl shmget shmread "
                + "shmwrite shutdown sin sleep socket socketpair sort splice split sprintf "
                + "sqrt srand stat study substr symlink syscall sysopen sysread sysseek "
                + "system syswrite tell telldir time times tr truncate uc ucfirst umask "
                + "undef unlink unpack unshift utime values vec wait waitpid warn write"), Pattern.MULTILINE | Pattern.CASE_INSENSITIVE, "functions"));
        regExpRule.add(new RegExpRule(getKeywords("bless caller continue dbmclose dbmopen die do dump else elsif eval exit "
                + "for foreach goto if import last local my next no our package redo ref "
                + "require return sub tie tied unless untie until use wantarray while"), Pattern.MULTILINE, "keyword"));

        commonFileExtensionList = Arrays.asList(new String[]{"pl", "pm", "t"});
    }
}
