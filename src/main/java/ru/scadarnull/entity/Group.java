package ru.scadarnull.entity;

import ru.scadarnull.service.HolidaysService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Group {

    private final List<Employee> employees;

    private List<BigDecimal> limits;

    public Group(Employee... e) {
        employees = new ArrayList<>();
        limits = new ArrayList<>();
        employees.addAll(Arrays.asList(e));
    }

    public void calc(LocalDate currentDay){
        BigDecimal remainder = new BigDecimal(0);
        HolidaysService holidays = new HolidaysService();

        System.out.println("Старт проекта " + currentDay.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        for(int i = 0; i < limits.size(); ++i){

            BigDecimal limit = limits.get(i);
            BigDecimal sum = new BigDecimal(String.valueOf(remainder));
            MonthlyStatistics monthlyStatistics = new MonthlyStatistics();

            while (limit.compareTo(sum) > 0){
                if(!holidays.isHolidayLocal(currentDay)){
                    monthlyStatistics.incDay();
                    for(Employee employee : employees){
                        if(employee.getStart().compareTo(currentDay) <= 0){
                            if(employee.getEnd() != null){
                                if(employee.getEnd().compareTo(currentDay) <= 0){
                                    continue;
                                }
                            }
                            sum = sum.add(employee.getSalary());
                            monthlyStatistics.addMoney(employee.getSalary());
                        }
                    }
                }
                currentDay = currentDay.plusDays(1);

                if(currentDay.getDayOfMonth() == 1){
                    printMonthlyStatistic(monthlyStatistics, currentDay);
                }
            }

            remainder  = new BigDecimal(String.valueOf(sum.subtract(limit)));
            printMonthlyStatistic(monthlyStatistics, currentDay.plusMonths(1));
            System.out.println(
                    "Лимит " + (i + 1) + " достигнут! Лимит " + limit +
                    ", перенесено на след. лимит " + remainder +
                    ", конец " + currentDay.minusDays(1).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                    );
        }
        System.out.println(" ");
    }

    private void printMonthlyStatistic(MonthlyStatistics monthlyStatistics, LocalDate currentDay){
        int month = currentDay.getMonthValue() - 1;
        if(month == 0){
            month = 12;
        }
        System.out.println("Месяц " + Month.of(month) + ", год " + currentDay.minusMonths(1).getYear() +
                ", рабочих дней " + monthlyStatistics.getNumberOfWorkingDays() +
                ", потрачено " + monthlyStatistics.getMoney());
        monthlyStatistics.setMoney(new BigDecimal(0));
        monthlyStatistics.setNumberOfWorkingDays(0);
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
