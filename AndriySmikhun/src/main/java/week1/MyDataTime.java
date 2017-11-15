package week1;

public class MyDataTime {

    int second;
    int minute;
    int hour;
    int day;
    int month;

    public MyDataTime(int second, int minute, int hour, int day, int month) {
        this.second = second;
        this.minute = minute;
        this.hour = hour;
        this.day = day;
        this.month = month;
    }

    public MyDataTime() {
    }

    @Override
    public String toString(){
        return " " + second + ":" + minute + ":" + hour + " " + day + "/" + month;

    }
}
