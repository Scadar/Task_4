package ru.scadarnull.service;

import ru.scadarnull.entity.Holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class Holidays {
    private Set<Holiday> holidays;

    public Holidays() {
        holidays = new HashSet<>();
        init();
    }

    private void init(){
        addFewDays(Calendar.JANUARY + 1, 1, 8);//Новый год
        holidays.add(new Holiday(Calendar.FEBRUARY + 1, 23));//День защитника отечаства
        holidays.add(new Holiday(Calendar.MARCH + 1, 8));//Международный женский день
        holidays.add(new Holiday(Calendar.MAY + 1, 1));//Праздник весны и труда
        holidays.add(new Holiday(Calendar.MAY + 1, 9));//День победы
        holidays.add(new Holiday(Calendar.JUNE + 1, 12));//День Росии
        holidays.add(new Holiday(Calendar.NOVEMBER + 1, 4));//День народного единства
    }

    private void addFewDays(int month, int from, int to) {
        for(int i = from; i <= to; ++i){
            holidays.add(new Holiday(month, i));
        }
    }

    public boolean isHoliday(LocalDate date){
        if(date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            return true;
        }
        for(Holiday h : holidays){
            if(date.getMonth().getValue() == h.getMonth() && date.getDayOfMonth() == h.getDay()){
                return true;
            }
        }
        return false;
    }
}
