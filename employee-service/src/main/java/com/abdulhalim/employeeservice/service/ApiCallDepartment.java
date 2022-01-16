package com.abdulhalim.employeeservice.service;

import com.abdulhalim.employeeservice.dto.request.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiCallDepartment {

    @Autowired
    private RestTemplate restTemplate;

    private static final String departmentServiceBaseUrl = "http://department-service";

    public Department getStudentDetails(Long departmentId){
        Department department = restTemplate.getForObject(departmentServiceBaseUrl + "/department/{departmentId}",Department.class,departmentId);
        return department;
    }
}
