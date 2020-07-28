package ru.scadarnull;

import ru.scadarnull.entity.Employee;
import ru.scadarnull.entity.Group;

import java.math.BigDecimal;
import java.time.LocalDate;

public class App 
{
    public static void main( String[] args )
    {
        Employee e1 = new Employee("Name 1", LocalDate.of(2020, 1, 2), new BigDecimal(500));
        Employee e2 = new Employee("Name 2", LocalDate.of(2020, 1, 2), new BigDecimal(500));
        Employee e3 = new Employee("Name 3", LocalDate.of(2020, 1, 2), new BigDecimal(500));
        Employee e4 = new Employee("Name 4", LocalDate.of(2020, 1, 2), new BigDecimal(500));

        Group group = new Group(e1, e2, e3, e4);
        group.setLimits(new BigDecimal(600),new BigDecimal(6000),new BigDecimal(60000),new BigDecimal(600000));
        group.calc();
    }
}
