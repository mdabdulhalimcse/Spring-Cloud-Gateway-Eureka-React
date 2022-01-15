package com.abdulhalim.employeeservice.service;

import com.abdulhalim.employeeservice.entity.Employee;
import com.abdulhalim.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepo;


    public Object saveEmployee(Employee employee) {
        Employee employee1 = employeeRepo.findEmployeeByCode(employee.getCode());

        if (employee1 == null){
            return employeeRepo.save(employee);
        }
        HashMap<String,String> dublicateMsg = new HashMap<>();
        dublicateMsg.put("code","Already exists of the code.Please try again...");
        return dublicateMsg;

    }


    public Object updateEmployee(Employee employee) {
        Employee employee1 = employeeRepo.findEmployeeByCode(employee.getCode());
        Optional<Employee> employee2 = employeeRepo.findById(employee.getId());
        HashMap<String,String> message = new HashMap<>();

        if (employee2.isPresent()){
            if (employee1 == null){
                employeeRepo.save(employee);
                message.put("message","Employee has been successfully saved.");
                return message;
            }

            String codeExistId = employee1.getId().toString();
            System.out.println("codeExistId"+codeExistId);
            String codeId = employee.getId().toString();
            System.out.println("codeId: "+ codeId);
            if (codeExistId.equals(codeId)){
              employeeRepo.save(employee);
                message.put("message","Employee has been successfully saved.");
                return message;
            }

            message.put("message","Code already exists of the Employee.Please try again...");
            return message;
        }

        message.put("message","Employee is not Found.");
        return message;
    }


    public Object deleteEmployeeById(Long id) {
        HashMap<String,String> deleteMsg = new HashMap<>();
        try {
            Employee employee = employeeRepo.findById(id).get();
        }catch (NoSuchElementException e){
            deleteMsg.put("id: "+id,"Not Found");
            return deleteMsg;
        }
        employeeRepo.delete(employeeRepo.findById(id).get());
        deleteMsg.put("id: "+id,"Employee has been successfully deleted.");
        return deleteMsg;
    }


    public Object getEmployeeById(Long id) {
        HashMap<String,String> employeeMsg = new HashMap<>();
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if (!optionalEmployee.isPresent()){
            employeeMsg.put("id: "+id,"Not Found");
            return employeeMsg;
        }
        return optionalEmployee;
    }


    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = employeeRepo.findAll();
        return employeeList;
    }

//    @Override
//    public List<EmployeeDto> findAllEmployee() {
//        List<EmployeeDto> dtos = new ArrayList<>();
//        this.employeeRepo.findAll().parallelStream().forEach((e)->{
//            dtos.add(convertEntityToDto(e));
//        });
//        return dtos;
//    }
//
//    @Override
//    public EmployeeDto convertEntityToDto(Employee employee) {
//        EmployeeDto dto = new EmployeeDto();
//        BeanUtils.copyProperties(employee,dto);
//        dto.setDepartmentName(employee.getDepartment().getDepartmentName());
//        return dto;
//    }
}
