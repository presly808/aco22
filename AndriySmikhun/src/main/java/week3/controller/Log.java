package week3.controller;

public class Log {
    private static Log log = new Log();

    public void eroor(String show){
        System.out.println("Error : " + show);
    }

    public void info(String show){
        System.out.println("Event : " + show);
    }
}
