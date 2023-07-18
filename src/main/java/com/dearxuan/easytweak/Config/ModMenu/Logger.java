package com.dearxuan.easytweak.Config.ModMenu;

import org.slf4j.LoggerFactory;

public class Logger {

    private final org.slf4j.Logger LOGGER;

    public Logger(String ModName) {
        this.LOGGER = LoggerFactory.getLogger(ModName);
    }

    public void error(Exception e) {
        LOGGER.error(e.toString());
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : stackTrace) {
            LOGGER.error(String.valueOf(element));
        }
    }

    public void debug(Object o){
        if(ModInfo.DEBUG){
            LOGGER.info(String.valueOf(o));
        }
    }
}
