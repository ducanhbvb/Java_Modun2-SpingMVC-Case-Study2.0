package com.codegym.service.impl.impl;

import com.codegym.model.Department;
import com.codegym.model.Employee;
import com.codegym.model.EmployeeForm;
import com.codegym.repository.EmployeeRepository;
import com.codegym.service.impl.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Iterable<Employee> findAllHaveBusiness() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findByIdHaveBusiness(long id) {
        return employeeRepository.findOne(id);
    }

    @Override
    public void saveHaveBusiness(Employee employee) {
    employeeRepository.save(employee);
    }
    @Override
    public void removeHaveBusiness(long id) {
    employeeRepository.delete(id);
    }
    @Override
    public void editEmployee( EmployeeForm employeeForm, String avatar){
        Employee employeeDemo =employeeRepository.findOne(employeeForm.getId());
        employeeDemo.setName(employeeForm.getName());
        employeeDemo.setBirthDate(employeeForm.getBirthDate());
        employeeDemo.setAddress(employeeForm.getAddress());
        employeeDemo.setAvatar(avatar);
        employeeDemo.setSalary(employeeForm.getSalary());
        employeeDemo.setDepartment(employeeForm.getDepartment());
        employeeRepository.save(employeeDemo);

    }
    @Override
    public Page<Employee> findAllByFirstNameContaining(String firstname, Pageable pageable) {
        return employeeRepository.findAllByNameContaining(firstname, pageable);
    }
    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Iterable<Employee> findByDepartment(Department department) {

        return employeeRepository.findAllByDepartment(department);
    }

    @Override
    public ArrayList<Employee> sortSalaryEmployee() {
        ArrayList<Employee> arrayList=(ArrayList<Employee>)employeeRepository.findAll();
        Collections.sort(arrayList,new ChairWeightComparator());
        return arrayList;
    }
}

class ChairWeightComparator implements Comparator<Employee> {
    public int compare(Employee employee, Employee employee1) {
        return (int)employee.getSalary() - (int)employee1.getSalary();
    }
}
//    @Override
//    public void Sort() {
//
//        Collections.sort(productList,new ChairWeightComparator());
//    }
//