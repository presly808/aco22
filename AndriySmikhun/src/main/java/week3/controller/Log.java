package week3.controller;

import java.util.ArrayList;
import java.util.List;

public class Log implements ILog{
    private List<String> eventlog = new ArrayList<>();
    private static Log log = new Log();

    public Log() {
        this.eventlog = eventlog;
    }

    public Log getLog(){
        return log;
    }

    @Override
    public void error(String str){
        eventlog.add("Error : " + str);
    }

    @Override
    public void event(String str){
        eventlog.add("Event : " + str);
    }
}
