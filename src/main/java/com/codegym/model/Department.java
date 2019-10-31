package com.codegym.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "department")
    //@OneToMany(targetEntity = Employee.class)
    private Set<Employee> employee;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public Department(String name, Set<Employee> employees) {
        this.name = name;
        this.employee = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(Set<Employee> employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employees=" + employee +
                '}';
    }

    public void setEmployees(Set<Employee> employees) {
        this.employee = employees;
    }
}
