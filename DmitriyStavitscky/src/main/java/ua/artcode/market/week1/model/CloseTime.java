package main.java.ua.artcode.market.week1.model;

public class CloseTime {

    private int hours;
    private int minutes;
    private int seconds;

    public CloseTime(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public void printCloseTime() {
        System.out.printf("%s:%s:%s", hours, minutes, seconds);
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

}
