package com.code.lecture.practice.service;

import com.code.lecture.practice.dto.EmployeeDTO;
import com.code.lecture.practice.entity.EmployeeEntity;
import com.code.lecture.practice.exceptions.ResourceNotFoundException;
import com.code.lecture.practice.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.util.ReflectionUtils;

import javax.management.relation.RelationServiceNotRegisteredException;

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

    public Optional<EmployeeDTO> getEmployeeById(Long id){
        /*
        Optional<EmployeeEntity> employeeEntity =  employeeRepository.findById(id);
        //ModelMapper modelMapper = new ModelMapper(); -->no need as bean injected
        return employeeEntity.map(employeeEntity1->modelMapper.map(employeeEntity,EmployeeDTO.class));
         */
        return employeeRepository
                .findById(id)
                .map(employeeEntity ->
                        modelMapper.map(employeeEntity,EmployeeDTO.class));
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity newEmployeeDTO = modelMapper.map(employeeDTO,EmployeeEntity.class);
        EmployeeEntity savedEntity =  employeeRepository.save(newEmployeeDTO);
        return modelMapper.map(savedEntity,EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO, Long employeeId) {
        existsEmployeeById(employeeId);
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO,EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(employeeEntity,EmployeeDTO.class);
    }

    public boolean existsEmployeeById(Long employeeId){

        boolean exists =  employeeRepository.existsById(employeeId);
        if(!exists) throw new ResourceNotFoundException("Employee not found with id : "+employeeId);
        return true;
    }

    public boolean deleteEmployeeById(Long employeeId) {
        existsEmployeeById(employeeId);
        employeeRepository.deleteById(employeeId);
        return true;
    }


    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        existsEmployeeById(employeeId);
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field,value)->{
                    Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class,field);
                    fieldToBeUpdated.setAccessible(true);
                    ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
                }
        );
        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(employeeEntity,EmployeeDTO.class);

    }
}
