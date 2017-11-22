package hw1.utils;

import hw1.controller.ProxyLoggerTerminal;

import java.util.Date;

public class LogSOut implements ILogger {

    private static LogSOut uniqueInstance;

    public static synchronized LogSOut getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new LogSOut();
        }
        return uniqueInstance;
    }

    private LogSOut(){}

    private void log(String action, Class c, String msg){

        StringBuilder sb = new StringBuilder(new Date(System.currentTimeMillis()).toString());
        sb.append(' ');
        sb.append(action);
        sb.append(' ');
        sb.append(c);
        sb.append(':');
        sb.append(msg);

        System.out.println(sb.toString());

    }

    @Override
    public void info(Class c, String msg) {
        log("INFO", c, msg);
    }

    @Override
    public void error(Class c, String msg) {
        log("ERROR", c, msg);
    }

    @Override
    public void throwing(Class c, Exception e) {
        log("EXEPTION", c, e.getMessage());
    }
}
