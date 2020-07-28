package ru.scadarnull.entity;

import ru.scadarnull.service.Holidays;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Group {

    private List<Employee> employees;

    private List<BigDecimal> limits;

    public Group(Employee... e) {
        employees = new ArrayList<>();
        limits = new ArrayList<>();
        employees.addAll(Arrays.asList(e));
    }

    public void calc(LocalDate localDate){
        Holidays holidays = new Holidays();
        System.out.println("Старт проекта " + localDate);
        for(int i = 0; i < limits.size(); ++i){
            BigDecimal limit = limits.get(i);
            BigDecimal sum = new BigDecimal(0);
            while (limit.compareTo(sum) > 0){
                if(!holidays.isHoliday(localDate)){
                    for(Employee employee : employees){
                        if(employee.getStart().compareTo(localDate) <= 0){
                            sum = sum.add(employee.getSalary());
                        }
                    }
                }
                localDate = localDate.plusDays(1);
            }
            System.out.println("Лимит " + i + " достигнут! Лимит = " + limit + " потрачено = " + sum +
                    " конец = " + localDate.minusDays(1));
        }
    }

    public void calc(){
        calc(getFirstWorkDay());
    }

    private LocalDate getFirstWorkDay(){
        Optional<Employee> resultEmployee = employees.stream().min(Comparator.comparing(Employee::getStart));
            if(resultEmployee.isPresent()){
            Employee employee = resultEmployee.get();
            return employee.getStart();
        }
        throw new RuntimeException("Сотрудников нет");
    }

    public void setLimits(BigDecimal... l) {
        limits = new ArrayList<>();
        limits.addAll(Arrays.asList(l));
    }
}
