package ru.scadarnull.service;

import ru.scadarnull.entity.Holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class HolidaysService {
    private final List<Holiday> holidays;

    public HolidaysService() {
        holidays = new ArrayList<>();
        init();
    }

    private void init(){
        addFewDays(Month.JANUARY.getValue(), 1, 8);//Новый год
        holidays.add(new Holiday(Month.FEBRUARY.getValue(), 23));//День защитника отечаства
        holidays.add(new Holiday(Month.MAY.getValue(), 8));//Международный женский день
        holidays.add(new Holiday(Month.MAY.getValue(), 1));//Праздник весны и труда
        holidays.add(new Holiday(Month.MAY.getValue(), 9));//День победы
        holidays.add(new Holiday(Month.JUNE.getValue(), 12));//День Росии
        holidays.add(new Holiday(Month.NOVEMBER.getValue(), 4));//День народного единства
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
