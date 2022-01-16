package com.abdulhalim.employeeservice.dto.response;

import com.abdulhalim.employeeservice.enums.Gender;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data

public class EmployeeResponseDto {

    private Long id;

    private String code;

    private String name;

    private LocalDate dob;

    private Gender gender;

    private String mobile;

}
