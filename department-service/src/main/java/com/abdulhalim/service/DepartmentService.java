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
        System.out.println("message: "+dept.getId()+" Department not found.");
        return message;

    }


    public Object deleteDepartmentById(Long id) {
        HashMap<String,String> deleteMsg = new HashMap<>();
        try {
            deptRepo.delete(deptRepo.findById(id).get());
        }catch (NoSuchElementException e){
            deleteMsg.put("id: "+id,"Not Found");
            return deleteMsg;
        }catch (Exception e){
            deleteMsg.put("id: "+id,"Sorry! can not delete parent row.");
            return deleteMsg;
        }
        deleteMsg.put("id: "+id,"Department has been successfully deleted.");
        return deleteMsg;
    }

    public Object getDepartmentById(Long id) {
        HashMap<String,String> deptMsg = new HashMap<>();
        Optional<Department> dept = deptRepo.findById(id);
        if (!dept.isPresent()){
            deptMsg.put("id: "+id,"Sorry! Not Found!");
            return deptMsg;
        }
        return dept;
    }

    public List<Department> getAllDepartment() {
        List<Department> deptList = deptRepo.findAll();
        return deptList;
    }
}
