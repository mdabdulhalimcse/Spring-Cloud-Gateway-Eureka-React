package com.abdulhalim.employeeservice.controller;

import com.abdulhalim.employeeservice.entity.Employee;
import com.abdulhalim.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/")
    public ResponseEntity<Object> saveEmployee(@Valid @RequestBody Employee employee){
        return new ResponseEntity<>(this.employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Object> updateEmployee(@Valid @RequestBody Employee employee){
        return new ResponseEntity<>(this.employeeService.updateEmployee(employee),HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return new ResponseEntity<>(this.employeeService.getAllEmployee(),HttpStatus.OK);
    }

    @GetMapping(value = "/employeeBy/{id}")
    public ResponseEntity<Object> getAllEmployees(@PathVariable Long id){
        return new ResponseEntity(this.employeeService.getEmployeeById(id),HttpStatus.OK);
    }

    @DeleteMapping(value = "/")
    public ResponseEntity<Object> deleteEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(this.employeeService.deleteEmployeeById(employee.getId()),HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.employeeService.deleteEmployeeById(id),HttpStatus.OK);
    }

//    @GetMapping(value = "/list")
//    public ResponseEntity<List<EmployeeDto>> listEmployee(){
//        return new ResponseEntity<>(this.employeeService.findAllEmployee(),HttpStatus.FOUND);
//    }

}
