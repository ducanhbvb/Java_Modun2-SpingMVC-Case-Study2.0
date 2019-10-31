package com.codegym.repository;

import com.codegym.model.Department;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface DepartmentRepository extends PagingAndSortingRepository<Department,Long> {

}
