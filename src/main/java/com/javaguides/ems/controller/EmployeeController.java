package com.javaguides.ems.controller;

import com.javaguides.ems.dto.EmployeeDto;
import com.javaguides.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    //Injedct the service
    private EmployeeService employeeService;

    //Build add employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee,HttpStatus.CREATED);
    }
    //Build Get employee API REST
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> findEmployeById(@PathVariable("id") Long employeeId){
        EmployeeDto findedEmployee = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(findedEmployee,HttpStatus.OK);
    }
    //RestApi to get all employees
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAllEmployees(){
        List<EmployeeDto> allEmployees = employeeService.getAllEmployees();
        return new ResponseEntity<>(allEmployees,HttpStatus.OK);
    }
    //Build update REST API
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id , @RequestBody EmployeeDto updatedEmployeeDto){
        EmployeeDto updateEmployee = employeeService.updateEmployee(id,updatedEmployeeDto);

        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }
    //Build delete employee rest API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Deleted succeffuly");
    }
}
