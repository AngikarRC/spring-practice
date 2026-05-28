package com.code.lecture.practice.service;

import com.code.lecture.practice.config.ModelMapperConfig;
import com.code.lecture.practice.dto.EmployeeDTO;
import com.code.lecture.practice.entity.EmployeeEntity;
import com.code.lecture.practice.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public List<EmployeeDTO> getAllEmployees(){
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        //ModelMapper modelMapper = new ModelMapper(); //no need to instantiate everytime -->make it a config bean -
        // let spring inject this object everytime
        return employeeEntities.stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .toList();
    }

    public EmployeeDTO getEmployeeById(Long id){
        EmployeeEntity employeeEntity =  employeeRepository.findById(id).orElse(null);
        //ModelMapper modelMapper = new ModelMapper(); -->no need as bean injected
        return modelMapper.map(employeeEntity,EmployeeDTO.class);
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity newEmployeeDTO = modelMapper.map(employeeDTO,EmployeeEntity.class);
        EmployeeEntity savedEntity =  employeeRepository.save(newEmployeeDTO);
        return modelMapper.map(savedEntity,EmployeeDTO.class);
    }
}
