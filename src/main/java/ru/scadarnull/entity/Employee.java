package ru.scadarnull.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {


    private LocalDate start;

    private BigDecimal salary;

    public Employee(LocalDate start, BigDecimal salary) {
        this.start = start;
        this.salary = salary;
    }

    public LocalDate getStart() {
        return start;
    }

    public BigDecimal getSalary() {
        return salary;
    }
}
