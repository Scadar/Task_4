package ru.scadarnull.entity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Holiday {

    private final int day;
    private final int month;

    public Holiday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public Holiday(int month, int day) {
        this.month = month;
        this.day = day;
    }


    public Date toDate(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    public boolean isWeekend(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toDate(year));
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Holiday)) {
            return false;
        } else {
            Holiday holiday = (Holiday) obj;
            return holiday.month == month && holiday.day == day;
        }
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new int[] { month, day });
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }
}
