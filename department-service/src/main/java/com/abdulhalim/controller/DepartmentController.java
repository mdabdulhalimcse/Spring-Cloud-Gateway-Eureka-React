package com.abdulhalim.controller;


import com.abdulhalim.entity.Department;
import com.abdulhalim.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService deptService;

    @PostMapping("/")
    public ResponseEntity<Department> saveDepartment(@Valid @RequestBody Department dept){
        return new ResponseEntity<>(this.deptService.saveDepartment(dept), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Object> updateDepartment(@Valid @RequestBody Department dept){
        return new ResponseEntity<>(this.deptService.updateDepartment(dept), HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Department>> getAllDepartment(){
        return new ResponseEntity<>(this.deptService.getAllDepartment(),HttpStatus.OK);
    }

    @GetMapping(value = "/departmentBy/{id}")
    public ResponseEntity<List<Department>> getEmployeesById(@PathVariable Long id){
        return new ResponseEntity(deptService.getDepartmentById(id),HttpStatus.OK);
    }

    @DeleteMapping(value = "/")
    public ResponseEntity<Object> deleteDepartment(@RequestBody Department department){
        return new ResponseEntity<>(this.deptService.deleteDepartmentById(department.getId()),HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteDepartmentById(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.deptService.deleteDepartmentById(id),HttpStatus.OK);
    }
}
