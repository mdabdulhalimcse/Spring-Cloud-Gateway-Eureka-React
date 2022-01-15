package com.abdulhalim.employeeservice.controller;

import com.abdulhalim.employeeservice.entity.Employee;
import com.abdulhalim.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return new ResponseEntity<>(this.employeeService.getAllEmployee(),HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getAllEmployees(@PathVariable Long id){
        return new ResponseEntity(this.employeeService.getEmployeeById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveEmployee(@Valid @RequestBody Employee employee){
        return new ResponseEntity<>(this.employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateEmployee(@Valid @RequestBody Employee employee){
        return new ResponseEntity<>(this.employeeService.updateEmployee(employee),HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.employeeService.deleteEmployeeById(id),HttpStatus.OK);
    }
}
