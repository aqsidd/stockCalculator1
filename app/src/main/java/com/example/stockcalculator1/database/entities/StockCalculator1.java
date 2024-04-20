package com.example.stockcalculator1.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.stockcalculator1.database.StockCalculator1Database;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(tableName = StockCalculator1Database.STOCK_CALCULATOR1_TABLE)
public class StockCalculator1 {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private double principle;
    private int days;
    private double growthRate;
    private LocalDateTime date;
    private int userId;
    private double value;

    public StockCalculator1(double principle, int days, double growthRate, int userId) {
        this.principle = principle;
        this.days = days;
        this.growthRate = growthRate;
        this.userId = userId;
        date = LocalDateTime.now();
        value = principle * Math.pow(1+growthRate/100, days);
    }

    @NonNull
    @Override
    public String toString() {
        return  "principle: " + principle + '\n' +
                "days: " + days + '\n' +
                "growthRate: " + growthRate + '\n' +
                "Value: " + value + '\n' +
                "date: " + date.toString() + '\n' +
                "============\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockCalculator1 that = (StockCalculator1) o;
        return id == that.id && Double.compare(principle, that.principle) == 0 && days == that.days && Double.compare(growthRate, that.growthRate) == 0 && userId == that.userId && Double.compare(value, that.value) == 0 && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, principle, days, growthRate, date, userId, value);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrinciple() {
        return principle;
    }

    public void setPrinciple(double principle) {
        this.principle = principle;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(double growthRate) {
        this.growthRate = growthRate;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
