package ru.scadarnull.entity;

import ru.scadarnull.service.Holidays;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Group {

    private List<Employee> employees;

    private List<BigDecimal> limit;

    public Group(Employee... e) {
        employees.addAll(Arrays.asList(e));
    }

    public void calc(LocalDate localDate){
        Holidays holidays = new Holidays();

    }

    public void calc(){
        calc(getFirstWorkDay());
    }
    private LocalDate getFirstWorkDay(){
        Optional<Employee> resultEmployee = employees.stream().min(Comparator.comparing(Employee::getStart));
            if(resultEmployee.isPresent()){
            Employee employee = resultEmployee.get();
            return LocalDate.parse(employee.getStart().toString());
        }
        throw new RuntimeException("Сотрудников нет");
    }
}
