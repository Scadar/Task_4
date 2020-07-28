package ru.scadarnull;

import ru.scadarnull.entity.Employee;
import ru.scadarnull.entity.Group;

import java.math.BigDecimal;
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
        Employee e1 = new Employee("Name 1", LocalDate.of(2020, 7, 12), new BigDecimal(100));
        Employee e2 = new Employee("Name 2", LocalDate.of(2020, 7, 15), new BigDecimal(150));
        Employee e3 = new Employee("Name 3", LocalDate.of(2020, 7, 18), new BigDecimal(120));
        Employee e4 = new Employee("Name 4", LocalDate.of(2020, 7, 18), new BigDecimal(80));

        Group group = new Group(e1, e2, e3, e4);
        group.calc();
    }
}
