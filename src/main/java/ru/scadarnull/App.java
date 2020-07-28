package ru.scadarnull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        LocalDate localDate = LocalDate.now();

        System.out.println(localDate.getDayOfWeek().equals(DayOfWeek.SATURDAY));
    }
}
