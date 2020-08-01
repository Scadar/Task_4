package ru.scadarnull.entity;

import java.time.LocalDate;

public class Holiday {
    private int month;
    private int day;

    public Holiday(int month, int day) {
        this.month = month;
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isEqual(LocalDate date){
        return date.getDayOfMonth() == day && date.getMonthValue() == month;
    }
}
