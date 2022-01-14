package com.abdulhalim.employeeservice.entity;

import com.abdulhalim.employeeservice.enums.Gender;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 4, min = 4, message = "Code must be 4 character")
    @Column(unique = true, nullable = false)
    private String code;

    @NotEmpty(message = "Name can not be empty!")
    @Size(min = 3, message = "At least 3 character required")
    @Size(max = 35, message = "Maximum 35 Character")
    private String name;


    @Column(name = "date_of_birth")
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotEmpty(message = "Mobile number can not be empty!")
    @Size(max = 35, message = "Maximum 14 digits")
    private String mobile;

    private Long departmentId;
}
