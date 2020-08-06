package ru.scadarnull.service;

import org.apache.commons.io.IOUtils;
import ru.scadarnull.entity.Holiday;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class HolidaysService {

    private final Set<LocalDate> wwwHolidays;
    private final Set<LocalDate> holidays;
    private List<Holiday> russianHoliday;

    public HolidaysService() {
        this.wwwHolidays = new HashSet<>();
        this.holidays = new HashSet<>();
        this.russianHoliday = new ArrayList<>();
        initWWW();
        init();
    }

    private void init() {
        for (int i = 1; i <= 8; i++) {
            russianHoliday.add(new Holiday(Month.JANUARY.getValue(), i));//Новый год
        }
        russianHoliday.add(new Holiday(Month.FEBRUARY.getValue(), 23));//День защитника отечаства
        russianHoliday.add(new Holiday(Month.MARCH.getValue(), 8));//Международный женский день
        russianHoliday.add(new Holiday(Month.MAY.getValue(), 1));//Праздник весны и труда
        russianHoliday.add(new Holiday(Month.MAY.getValue(), 9));//День победы
        russianHoliday.add(new Holiday(Month.JUNE.getValue(), 12));//День Росии
        russianHoliday.add(new Holiday(Month.NOVEMBER.getValue(), 4));//День народного единства
        //holidays2020();
    }

    private void holidays2020() {
        addHoliday2020(3, 30);
        addHoliday2020(3, 31);
        for(int i = 1; i <=30; ++i){
            addHoliday2020(4,i);
        }
        addHoliday2020(5,1);
        addHoliday2020(5,4);
        addHoliday2020(5,5);
        addHoliday2020(5,6);
        addHoliday2020(5,7);
        addHoliday2020(5,8);
        addHoliday2020(6,12);
        addHoliday2020(6,24);
        addHoliday2020(7,1);
        addHoliday2020(11,4);
    }

    private void addHoliday2020(int month, int day){
        holidays.add(LocalDate.of(2020, month, day));
    }


    public boolean isHolidayLocal(LocalDate date){
        for(Holiday holiday : russianHoliday){
            if(holiday.isEqual(date)){
                if(date.getDayOfWeek() == DayOfWeek.SATURDAY){
                    holidays.add(date.plusDays(2));
                }
                if(date.getDayOfWeek() == DayOfWeek.SUNDAY){
                    holidays.add(date.plusDays(1));
                }
                return true;
            }
        }
        if(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY){
            return true;
        }

        return holidays.contains(date);
    }

    private void initWWW() {
        for(int year = 2020; year <= 2025; ++year){
            String request = "https://isdayoff.ru/api/getdata?year=" + year;
            String result = "";
            try {
                URL yahoo = new URL(request);
                URLConnection yc = yahoo.openConnection();
                result = IOUtils.toString(yc.getInputStream(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
            LocalDate currentDay = LocalDate.of(year, 1, 1);
            for(Character day : result.toCharArray()){
                if(day.equals('1')){
                    wwwHolidays.add(currentDay);
                }
                currentDay = currentDay.plusDays(1);
            }
        }
    }

    public boolean isHolidayWWW(LocalDate date){
        if(date.getYear() > 2025){
            return isHolidayLocal(date);
        }
        return wwwHolidays.contains(date);
    }

}
