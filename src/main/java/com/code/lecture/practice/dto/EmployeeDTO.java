package com.code.lecture.practice.dto;


import com.code.lecture.practice.annotations.PrimeNumberValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
@Data
public class EmployeeDTO {
    @NotNull
    private Long id;
    //@NotEmpty(message = "Name of employee cannot be empty")
    @NotBlank(message = "Name of employee cannot be blank")
    @Size(min=3, max = 50)
    private String name;
    @Positive
    @Digits(integer=6,fraction = 2)
    private Double salary;
    @Email(message = "Should be valid email")
    private String email;
    @Max(value = 60,message = "Senior Citizens not allowed")
    @Min(value=16,message = "underage")
    @PrimeNumberValidation
    private Integer age;
    @NotBlank
    @Pattern(regexp = "^(ADMIN|USER)$" , message = "role of employee needs to be admin or user")
    private String role;
    @PastOrPresent
    private LocalDate doj;
    @AssertTrue(message = "Only Active employees allowed")
    @JsonProperty("isActive")
    private boolean active;


}
