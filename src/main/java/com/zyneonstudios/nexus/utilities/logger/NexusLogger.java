package com.zyneonstudios.nexus.utilities.logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NexusLogger {

    private boolean isLocked = false;
    private boolean sendDebug = false;
    private String prefix;

    public NexusLogger(String name) {
        prefix = "[%time%] (%type%) "+name+" | ";
    }

    private String getPrefix() {
        return prefix.replaceFirst("%time%",new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss").format(Calendar.getInstance().getTime()));
    }

    public void setName(String name, boolean lock) {
        if(!isLocked) {
            isLocked = lock;
            prefix = "%time% | "+name+" | ";
        }
    }

    public void enableDebug() {
        sendDebug = true;
    }

    public void disableDebug() {
        sendDebug = false;
    }

    public boolean isDebugging() {
        return sendDebug;
    }

    public void log(String message) {
        System.out.println(getPrefix().replace("%type%","LOG")+message);
    }

    public void dbg(String debugMessage) {
        if(sendDebug)
            System.out.println("\u001B[34m"+getPrefix().replace("%type%","DEB")+debugMessage+"\u001B[0m");
    }

    public void deb(String debugMessage) {
        dbg(debugMessage);
    }

    public void err(String errorMessage) {
        System.out.println("\u001B[31m"+getPrefix().replace("%type%","ERR")+errorMessage+"\u001B[0m");
    }

    public void printErr(String prefix, String type, String message, String reason, StackTraceElement[] cause, String... possibleFixes) {
        if(message!=null) {
            if(prefix == null) {
                prefix = getPrefix();
            }
            if(type == null) {
                type = "ERROR";
            }
            err("===("+prefix+")===============================================================/"+type+"/===");
            err(message);
            if(reason!=null) {
                err("Reason: "+reason);
            }
            if(possibleFixes!=null) {
                String p = "Possible fix(es): ";
                for(String fix:possibleFixes) {
                    err(p+fix);
                    p = "";
                }
            }
            if(cause!=null) {
                err(" ");
                err("Caused by:");
                for(StackTraceElement element:cause) {
                    err(" "+element.toString());
                }
            }
            err("===/"+type+"/===============================================================("+prefix+")===");
        }
    }
}