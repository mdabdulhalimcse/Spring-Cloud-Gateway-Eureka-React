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

    //    @Override
    public Department saveDepartment(Department dept) {
        return deptRepo.save(dept);
    }

    //    @Override
    public Department updateDepartment(Department dept) {
        return deptRepo.save(dept);
    }

    //    @Override
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

    //    @Override
    public Object getDepartmentById(Long id) {
        HashMap<String,String> deptMsg = new HashMap<>();
        Optional<Department> dept = deptRepo.findById(id);
        if (!dept.isPresent()){
            deptMsg.put("id: "+id,"Sorry! Not Found!");
            return deptMsg;
        }
        return dept;
    }

    //    @Override
    public List<Department> getAllDepartment() {
        List<Department> deptList = deptRepo.findAll();
        return deptList;
    }
}
