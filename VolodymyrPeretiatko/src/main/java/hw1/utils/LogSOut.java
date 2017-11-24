package hw1.utils;

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

    private void log(String action, String msg){

        StringBuilder sb = new StringBuilder(new Date(System.currentTimeMillis()).toString());
        sb.append(' ');
        sb.append(action);
        sb.append(' ');
        sb.append(':');
        sb.append(msg);

        System.out.println(sb.toString());

    }

    @Override
    public void info(String msg) {
        log("INFO", msg);
    }

    @Override
    public void error(String msg) {
        log("ERROR", msg);
    }

    @Override
    public void throwing(Exception e) {
        log("EXEPTION", e.getMessage());
    }
}
