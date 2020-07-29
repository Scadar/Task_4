package ru.scadarnull.entity;

import ru.scadarnull.service.HolidaysService;

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
        HolidaysService holidays = new HolidaysService();
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
            System.out.println("Лимит " + i + " достигнут! Лимит = " + limit + ", потрачено = " + sum +
                    ", конец = " + localDate.minusDays(1));//Минус день нужен, потому что в цикле прибавляется 1 лишний
        }
        System.out.println(" ");
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

    public void setLimits(List<BigDecimal> limits) {
        this.limits = limits;
    }

    public void add(Employee employee){
        employees.add(employee);
    }
}
