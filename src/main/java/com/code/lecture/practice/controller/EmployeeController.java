package com.code.lecture.practice.controller;

import com.code.lecture.practice.dto.EmployeeDTO;
import com.code.lecture.practice.exceptions.ResourceNotFoundException;
import com.code.lecture.practice.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> fetchEmployeeById(@PathVariable(name = "employeeId") Long id){
        Optional<EmployeeDTO> empDTO = employeeService.getEmployeeById(id);
        return empDTO
                .map(employeeDTO -> ResponseEntity.ok(employeeDTO))
                .orElseThrow(()->new ResourceNotFoundException("Employee with id : "+id+ "not found"));
    }

    /**
     * use @RequestParam for optional path variables in get mapping
     *
     * http://localhost:8080/employee?inputAge=25&sortBy=Ascending
     */

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> fetchAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                                  @RequestParam(required = false) String sortBy){
        List<EmployeeDTO> employeeDTOS =  employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeDTOS);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        EmployeeDTO savedEmp =  employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmp, HttpStatus.CREATED);
    }
    @PutMapping("/{employeeid}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid  EmployeeDTO employeeDTO, @PathVariable Long employeeid){
        EmployeeDTO savedEmp =  employeeService.updateEmployeeById(employeeDTO,employeeid);
        return new ResponseEntity<>(savedEmp,HttpStatus.OK);

    }
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        boolean isDeleted = employeeService.deleteEmployeeById(employeeId);
        if(isDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@PathVariable Long employeeId, Map<String,Object>updates){
        EmployeeDTO dto = employeeService.updatePartialEmployeeById(employeeId,updates);
        if(dto==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }
}
