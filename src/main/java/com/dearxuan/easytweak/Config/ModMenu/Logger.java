package com.dearxuan.easytweak.Config.ModMenu;

import org.apache.commons.lang3.StringUtils;
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

    public void debug(Object ... os){
        if(ModInfo.DEBUG){
            if(os.length == 1){
                LOGGER.info(String.valueOf(os[0]));
            }else{
                String[] s = new String[os.length];
                for(int i=0;i<os.length;++i){
                    s[i] = String.valueOf(os[i]);
                }
                String result = StringUtils.join(s, ",");
                LOGGER.info(result);
            }
        }
    }
}
