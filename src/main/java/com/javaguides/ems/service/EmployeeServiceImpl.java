package com.javaguides.ems.service;

import com.javaguides.ems.dto.EmployeeDto;
import com.javaguides.ems.entity.Employee;
import com.javaguides.ems.exception.ResourceNotFoundException;
import com.javaguides.ems.mapper.EmployeeMapper;
import com.javaguides.ems.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    //Inject Dependencies

    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        //Save the employee entity in to database
        Employee savedEmployee = employeeRepository.save(employee);
        //Return the employe to the client => to EmployeeDto
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);

    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
       Employee findEmployee = employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("There is no employee ith this id"));
       return EmployeeMapper.mapToEmployeeDto(findEmployee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> allEmployees = employeeRepository.findAll();
        //Convert List of employess entity to dto using Collect
        return allEmployees.stream().map((employee -> EmployeeMapper.mapToEmployeeDto(employee)))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployeeDto) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->new ResourceNotFoundException("There is no employee with this Id"));
        employee.setEmail(updatedEmployeeDto.getEmail());
        employee.setFirstName(updatedEmployeeDto.getFirstName());
        employee.setLastName(updatedEmployeeDto.getLastName());

        Employee udatedEmploye = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(udatedEmploye);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("There is no employee with this Id"));
        employeeRepository.deleteById(employeeId);
    }
}
