package com.abdulhalim.service;

import com.abdulhalim.entity.Department;
import com.abdulhalim.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DepartmentService {

    private final DepartmentRepository deptRepo;

    public Department saveDepartment(Department dept) {
        return deptRepo.save(dept);
    }

    public Object updateDepartment(Department dept) {
        Optional<Department> optionalDepartment = deptRepo.findById(dept.getId());
        HashMap<String,String> message = new HashMap<>();

        if (optionalDepartment.isPresent()){
            return deptRepo.save(dept);
        }
        message.put("message"," Department id "+dept.getId()+" is not found.");
        return message;
    }

    public Object deleteDepartmentById(Long id) {
        HashMap<String,String> message = new HashMap<>();
        try {
            deptRepo.delete(deptRepo.findById(id).get());
        }catch (NoSuchElementException e){
            message.put("message","Department id "+id +" was not found!");
            return message;
        }catch (Exception e){
            message.put("message","Sorry! can not delete parent row.");
            return message;
        }
        message.put("message","Department id "+id +" has been successfully deleted.");
        return message;
    }

    public Object getDepartmentById(Long id) {
        HashMap<String,String> message = new HashMap<>();
        Optional<Department> dept = deptRepo.findById(id);
        if (!dept.isPresent()){
            message.put("message","Sorry! Department id "+ id +" Not Found!");
            return message;
        }
        return dept;
    }

    public List<Department> getAllDepartment() {
        List<Department> deptList = deptRepo.findAll();
        return deptList;
    }
}
