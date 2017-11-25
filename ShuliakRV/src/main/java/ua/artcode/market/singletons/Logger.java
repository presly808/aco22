package ua.artcode.market.singletons;

import java.util.ArrayList;
import java.util.List;

public class Logger {

    private static Logger instance;

    private List<String> logs;

    private Logger () {
      logs = new ArrayList<>();
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public List<String> getLogs() {
        return logs;
    }

    public void log(String logInfo) {
        logs.add(logInfo);
    }




}
