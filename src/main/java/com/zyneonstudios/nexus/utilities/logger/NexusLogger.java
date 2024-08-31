package com.zyneonstudios.nexus.utilities.logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NexusLogger {

    private boolean isLocked = false;
    private boolean sendDebug = false;
    private String prefix;

    public NexusLogger(String name) {
        prefix = "%time% | "+name+"-%type% | ";
    }

    private String getPrefix() {
        return prefix.replaceFirst("%time%",new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").format(Calendar.getInstance().getTime()));
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
        System.out.println(getPrefix().replace( "%type%","LOG")+message);
    }

    public void dbg(String debugMessage) {
        if(sendDebug)
            System.out.println("\u001B[34m"+getPrefix().replace("%type%","DEB")+debugMessage);
    }

    public void deb(String debugMessage) {
        dbg(debugMessage);
    }

    public void err(String errorMessage) {
        System.err.println(getPrefix().replace("%type%","ERR")+errorMessage);
    }
}