package ru.scadarnull.entity;

import java.math.BigDecimal;
import java.util.List;

public class MonthlyStatistics {

    private int numberOfWorkingDays;
    private BigDecimal money;

    public MonthlyStatistics() {
        this.numberOfWorkingDays = 0;
        this.money = new BigDecimal(0);
    }

    public void incDay() {
        numberOfWorkingDays++;
    }

    public void addMoney(BigDecimal salary) {
        money = money.add(salary);
    }

    public int getNumberOfWorkingDays() {
        return numberOfWorkingDays;
    }

    public void setNumberOfWorkingDays(int numberOfWorkingDays) {
        this.numberOfWorkingDays = numberOfWorkingDays;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
