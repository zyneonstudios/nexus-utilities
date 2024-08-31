package com.zyneonstudios.nexus.utilities.logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NexusLogger {

    private boolean sendDebug = false;
    private final String prefix;

    public NexusLogger(String name) {
        prefix = "%time% | "+name+" | ";
    }

    private String getPrefix() {
        return prefix.replace("%time%",new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").format(Calendar.getInstance().getTime()));
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
        System.out.println(getPrefix()+message);
    }

    public void dbg(String debugMessage) {
        if(sendDebug)
            System.out.println(getPrefix()+"(debug) "+debugMessage);
    }

    public void err(String errorMessage) {
        System.err.println(getPrefix()+"(error) "+errorMessage);
    }
}