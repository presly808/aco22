package hw1.utils;

public interface ILogger {

    void info(String msg);
    void error(String msg);
    void throwing(Exception e);

}
