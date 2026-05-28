package com.code.lecture.practice.dto;


import tools.jackson.databind.node.DoubleNode;

import java.time.LocalDate;

public class EmployeeDTO {
    private Long id;
    private String name;
    private Double salary;
    private String email;
    private LocalDate doj;

    public EmployeeDTO(Long id, String name, Double salary, String email, LocalDate doj) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.email = email;
        this.doj = doj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDoj() {
        return doj;
    }

    public void setDoj(LocalDate doj) {
        this.doj = doj;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", email='" + email + '\'' +
                ", doj=" + doj +
                '}';
    }
}
