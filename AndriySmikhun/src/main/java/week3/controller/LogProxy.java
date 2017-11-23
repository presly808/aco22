package week3.controller;

public class LogProxy implements ILog {
    Log log = new Log();

    @Override
    public void error(String str) {
        System.out.println("Error : "  + str);
        log.error(str);
    }

    @Override
    public void event(String str) {
        System.out.println("Event : " + str);
        log.event(str);
    }
}
