package main.java.ua.artcode.market.week1.model.ClassroomWork;

public class SellDate {

    private int day;
    private int month;
    private int year;

    public SellDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String toString() {
        return String.format("%d. %d. %d", day, month, year);
    }

}

    /*int day, int month, int year) {
        this.Day = day;
        this.Month = month;
        this.Year = year;*/