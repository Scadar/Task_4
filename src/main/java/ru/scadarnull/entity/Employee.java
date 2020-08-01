package ru.scadarnull.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {

    private final String name;
    private final BigDecimal salary;
    private final LocalDate start;
    private final LocalDate end;

    public Employee(String name, BigDecimal salary, LocalDate start) {
        this.name = name;
        this.start = start;
        this.salary = salary;
        this.end = null;
    }

    public Employee(String name, BigDecimal salary, LocalDate start, LocalDate end) {
        this.name = name;
        this.start = start;
        this.salary = salary;
        this.end = end;
    }

    public String getName() { return name; }
    public BigDecimal getSalary() {
        return salary;
    }
    public LocalDate getStart() {
        return start;
    }
    public LocalDate getEnd() { return end; }
}
