package com.code.lecture.practice.controller;

import com.code.lecture.practice.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path="/employee")
public class EmployeeController {

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id){
        return new EmployeeDTO(id,"Arc",237373d,"arc12@gamil.com", LocalDate.of(2022,1,21));
    }

    /**
     * use @RequestParam for optional path variables in get mapping
     *
     * http://localhost:8080/employee?inputAge=25&sortBy=Ascending
     */

    @GetMapping
    public String getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                  @RequestParam(required = false) String sortBy){
        return "hi, "+age + sortBy;
    }
}
