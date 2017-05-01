package com.example.tipcalculator.tipcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Esther Song on 4/23/17.
 */

public class Person {
    private String name;
    private double cost;
    private double cost_with_tip;

    public Person(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String get_name() {
        return name;
    }

    public double get_cost() {
        return cost;
    }

    //change this name to calculate cost_with_tip
    public void set_cost_with_tip(double percentage) {
        double temp = percentage * .01;
        cost_with_tip = round(cost + (cost * temp), 2);
    }

    public double get_cost_with_tip(){
        return cost_with_tip;
    }

    private double round(double value, int places){
        if(places < 0) { throw new IllegalArgumentException(); }

        BigDecimal big_d = new BigDecimal(value);
        big_d = big_d.setScale(places, RoundingMode.HALF_UP);
        return big_d.doubleValue();
    }
}
