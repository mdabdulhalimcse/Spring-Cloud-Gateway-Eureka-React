package com.abdulhalim.employeeservice.dto.request;

import com.abdulhalim.employeeservice.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDetailsDto {

    private Long id;

    @Size(max = 4, min = 4, message = "Code must be 4 character")
    @Column(unique = true, nullable = false)
    private String code;

    private String name;


    @Column(name = "date_of_birth")
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String mobile;

    private DepartmentDto department;
}
