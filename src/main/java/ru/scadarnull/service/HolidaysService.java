package ru.scadarnull.service;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

public class HolidaysService {
    private final Set<LocalDate> wwwHolidays;

    public HolidaysService() {
        this.wwwHolidays = new HashSet<>();
        initWWW();
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

    public boolean isHoliday(LocalDate date){
        return wwwHolidays.contains(date);
    }

}
