package com.abdulhalim.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "DEPARTMENT")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Name can not be empty!")
    @Size(max = 35,message = "Maximum 35 Character")
    @Column(name = "department_name", nullable = false)
    private String departmentName;

    private boolean active;


}
