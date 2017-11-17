package ua.artcode.market.models;

import ua.artcode.market.utils.TimeUtils;

public class Time implements Comparable<Time> {

    private int hours;
    private int minutes;
    private int seconds;

    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return String.format("%s:%s:%s", hours, minutes, seconds);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Time other = (Time) obj;

        return hours == other.hours &&
                minutes == other.minutes &&
                seconds == other.seconds;
    }

    @Override
    public int compareTo(Time o) {

        return TimeUtils.sumOfSeconds(this) -
                TimeUtils.sumOfSeconds(o);
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
