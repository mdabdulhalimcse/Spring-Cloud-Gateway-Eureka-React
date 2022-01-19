package com.abdulhalim.controller;


import com.abdulhalim.entity.Department;
import com.abdulhalim.repository.DepartmentRepository;
import com.abdulhalim.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/department")
public class DepartmentController {


    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentService deptService;

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartment(){
        return new ResponseEntity<>(this.deptService.getAllDepartment(),HttpStatus.OK);
    }
    @GetMapping(value = "/getActiveDept")
    public ResponseEntity<List<Department>> getAllDepartmentIsActive(){
        return new ResponseEntity<>(this.departmentRepository.findDepartmentsByActiveTrue(),HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Department>> getEmployeesById(@PathVariable Long id){
        return new ResponseEntity(deptService.getDepartmentById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Department> saveDepartment(@Valid @RequestBody Department dept){
        return new ResponseEntity<>(this.deptService.saveDepartment(dept), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateDepartment(@Valid @RequestBody Department dept){
        return new ResponseEntity<>(this.deptService.updateDepartment(dept), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteDepartmentById(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.deptService.deleteDepartmentById(id),HttpStatus.OK);
    }


}
