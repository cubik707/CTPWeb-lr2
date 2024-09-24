package com.domain;

import com.core.ExcludeField;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class Staff {

    private String name;
    private int age;
    @ExcludeField
    private String[] position;
    private List<String> skills;
    private Map<String, BigDecimal> salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getPosition() {
        return position;
    }

    public void setPosition(String[] position) {
        this.position = position;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public Map<String, BigDecimal> getSalary() {
        return salary;
    }

    public void setSalary(Map<String, BigDecimal> salary) {
        this.salary = salary;
    }
}

