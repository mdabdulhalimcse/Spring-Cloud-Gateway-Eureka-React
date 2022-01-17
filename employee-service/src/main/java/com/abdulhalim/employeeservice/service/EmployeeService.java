package com.abdulhalim.employeeservice.service;

import com.abdulhalim.employeeservice.dto.request.Department;
import com.abdulhalim.employeeservice.dto.request.EmployeeDetailsDto;
import com.abdulhalim.employeeservice.dto.response.EmployeeResponseDto;
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
    @Autowired
    private ApiCallDepartment apiCallDepartment;


    public Object saveEmployee(Employee employee) {
        Employee employee1 = employeeRepo.findEmployeeByCode(employee.getCode());

        if (employee1 == null){
            return employeeRepo.save(employee);
        }
        HashMap<String,String> dublicateMsg = new HashMap<>();
        dublicateMsg.put("message","Code already exists of the Employee.Please try again...");
        return dublicateMsg;
    }

    public Object updateEmployee(Employee employee) {
        System.out.println(employee);
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
            String codeId = employee.getId().toString();

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

    public List<EmployeeResponseDto> getAllEmployeeDto() {
        List<EmployeeResponseDto> employeeResponseDtoList = new ArrayList<>();
        this.employeeRepo.findAll().parallelStream().forEach((employee -> {
            employeeResponseDtoList.add(convertEmployeeToDto(employee));
        }));
        return employeeResponseDtoList;
    }

    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }

    public Object getEmployeeDetailsById(Long id){
        HashMap<String,String> employeeMsg = new HashMap<>();
        Employee employee = employeeRepo.findById(id).orElse(null);

        try {
            if (employee.equals(null)){}
            Department department = apiCallDepartment.getStudentDetails(employee.getDepartmentId());
            EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto();
            BeanUtils.copyProperties(employee,employeeDetailsDto);
            employeeDetailsDto.setDepartment(department);

            return employeeDetailsDto;
        }catch (NullPointerException e) {
            employeeMsg.put("message","Not Found of the Employee");
            return employeeMsg;
        }catch (IllegalStateException e){
            employeeMsg.put("message","Department Service is taking too long to respond or is down. Please try again later");
            return employeeMsg;
        }
    }

    private EmployeeResponseDto convertEmployeeToDto(Employee employee){
        EmployeeResponseDto dto = new EmployeeResponseDto();
        BeanUtils.copyProperties(employee,dto);
        return dto;
    }
}
