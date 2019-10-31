package com.codegym.service.impl.impl;

import com.codegym.model.Department;
import com.codegym.repository.DepartmentRepository;
import com.codegym.service.impl.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
@Autowired
   private DepartmentRepository departmentRepository;
    @Override
    public Iterable<Department> findAllHaveBusiness() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findByIdHaveBusiness(long id) {
        return departmentRepository.findOne(id);
    }

    @Override
    public void saveHaveBusiness(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void removeHaveBusiness(long id) {
        departmentRepository.delete(id);
    }
}
