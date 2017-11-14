package hw1.utils;

public interface ILogger {

    void info(Class c, String msg);
    void error(Class c, String msg);
    void throwing(Class c, Exception e);

}
