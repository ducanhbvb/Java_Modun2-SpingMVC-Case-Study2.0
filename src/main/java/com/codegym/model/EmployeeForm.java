package com.codegym.model;


import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class EmployeeForm {
    private long id;
    private String name;
    private String birthDate ;
    private String address;
    private MultipartFile avatar;
    private double salary;
    private Department department; // String departmentId; select DeparByd

    public EmployeeForm() {
    }


    public EmployeeForm(Long id,String name, String birthDate, String address, MultipartFile avatar, double salary, Department department) {
        this.id=id;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.avatar = avatar;
        this.salary = salary;
        this.department = department;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
