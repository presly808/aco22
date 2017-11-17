package week1.model;

public class MyDataTime implements Comparable{

    private int second;
    private int minute;
    private int hour;
    private int day;
    private int month;

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

    @Override
    public int compareTo(Object o) {
        String str = ((MyDataTime) o).toString();
        int count = this.toString().compareTo(str);
        if (count != 0){
            count = count / Math.abs(count);
            return count;
        }
        return 0;
    }
}
