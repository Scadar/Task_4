package ru.scadarnull.entity;

import java.util.Arrays;

public class Holiday {

    private final int day;
    private final int month;

    public Holiday(int month, int day) {
        this.month = month;
        this.day = day;
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
