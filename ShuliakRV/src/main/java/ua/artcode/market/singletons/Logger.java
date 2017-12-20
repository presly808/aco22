package ua.artcode.market.singletons;

import ua.artcode.market.databases.AppDB;

import java.util.ArrayList;
import java.util.List;

public class Logger {

    private static Logger instance;

    private static List<String> logs;

    private Logger() {
    }

    public static Logger getInstance(AppDB appDB) {
        if (instance == null) {
            logs = appDB.getLogs();
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
