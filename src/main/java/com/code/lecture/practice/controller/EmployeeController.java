package com.code.lecture.practice.controller;

import com.code.lecture.practice.dto.EmployeeDTO;
import com.code.lecture.practice.entity.EmployeeEntity;
import com.code.lecture.practice.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/{employeeId}")
    public EmployeeDTO fetchEmployeeById(@PathVariable(name = "employeeId") Long id){
        return employeeService.getEmployeeById(id);
    }

    /**
     * use @RequestParam for optional path variables in get mapping
     *
     * http://localhost:8080/employee?inputAge=25&sortBy=Ascending
     */

    @GetMapping
    public List<EmployeeDTO> fetchAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                                  @RequestParam(required = false) String sortBy){
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.createEmployee(employeeDTO);
    }
    @PutMapping("/{employeeid}")
    public EmployeeDTO updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeid){
        return employeeService.updateEmployeeById(employeeDTO,employeeid);

    }
}
