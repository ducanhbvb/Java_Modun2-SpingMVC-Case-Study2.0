package com.codegym.service.impl;

import com.codegym.model.Department;
import com.codegym.model.Employee;
import com.codegym.model.EmployeeForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public interface EmployeeService extends GeneralService<Employee> {
     void editEmployee(EmployeeForm employeeForm, String avatar);
     Iterable<Employee> findByDepartment(Department department);
     Page<Employee> findAllByFirstNameContaining(String name, Pageable pageable);
     Page<Employee> findAll(Pageable pageable);
     ArrayList<Employee> sortSalaryEmployee();
}