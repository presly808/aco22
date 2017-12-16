package week3.controller;

import java.util.Date;

public class Log implements ILog {

    private static Log log;

    private Log() {
    }

    public static synchronized Log getLog() {
        if (log == null) {
            log = new Log();
        }
        return log;
    }

    @Override
    public void error(String str) {
        Date date = new Date();
        System.out.println(date + " Error" + str);

    }

    @Override
    public void event(String str) {
        Date date = new Date();
        System.out.println(date + " Event" + str);

    }
}
