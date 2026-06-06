package com.code.lecture.practice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "employee_dtl")
@Data
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private LocalDate doj;
    private Double salary;
    private String role;
    private boolean active;
}
