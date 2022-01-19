package com.abdulhalim.repository;

import com.abdulhalim.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findDepartmentsByActiveTrue();
}
