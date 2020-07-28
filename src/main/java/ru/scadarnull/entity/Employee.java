package ru.scadarnull.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {

    private String name;

    private LocalDate start;

    private BigDecimal salary;

    public Employee(String name, LocalDate start, BigDecimal salary) {
        this.name = name;
        this.start = start;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStart() {
        return start;
    }

    public BigDecimal getSalary() {
        return salary;
    }
}
