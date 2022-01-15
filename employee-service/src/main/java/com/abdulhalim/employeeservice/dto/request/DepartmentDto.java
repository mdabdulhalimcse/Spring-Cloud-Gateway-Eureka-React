package com.abdulhalim.employeeservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDto {

    private long id;

    private String departmentName;

    private boolean active;
}
