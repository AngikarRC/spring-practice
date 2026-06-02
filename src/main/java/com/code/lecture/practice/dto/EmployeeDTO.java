package com.code.lecture.practice.dto;


import lombok.Data;

import java.time.LocalDate;
@Data
public class EmployeeDTO {
    private Long id;
    private String name;
    private Double salary;
    private String email;
    private LocalDate doj;
    private boolean active;


}
