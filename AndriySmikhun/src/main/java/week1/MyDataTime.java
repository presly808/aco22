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

    public void setSecond(int second) {
        this.second = second;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public String toString(){
        return " " + second + ":" + minute + ":" + hour + " " + day + "/" + month;

    }
}
