package main.java.market;

public class BillTime {

    public int year;
    public int month;
    public int day;
    public int hour;
    public int minute;


    public BillTime(int year, int month, int day, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public static void showTime(BillTime time){
        System.out.printf( " %d.%d.%d \t %d:%d. \n",
                time.year, time.month, time.day, time.hour, time.minute);
    }
}
