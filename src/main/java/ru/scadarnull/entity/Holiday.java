package ru.scadarnull.entity;

public class Holiday {

    private final int day;
    private final int month;

    public Holiday(int month, int day) {
        this.month = month;
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }
}
