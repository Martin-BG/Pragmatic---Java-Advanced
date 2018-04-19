package model;

import lombok.Data;

public @Data
class Employee {
    private int id;
    private String name;
    private int age;
    private double salary;
    private String department;
}
