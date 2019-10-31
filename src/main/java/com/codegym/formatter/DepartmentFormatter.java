//package com.codegym.formatter;
//
//import com.codegym.model.Department;
//import com.codegym.service.impl.DepartmentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.Formatter;
//import org.springframework.stereotype.Component;
//
//import java.text.ParseException;
//import java.util.Locale;
//
//@Component
//public class DepartmentFormatter implements Formatter<Department> {
//    @Autowired
//    public  DepartmentService departmentService;
//    @Autowired
//    public DepartmentFormatter(DepartmentService departmentService){
//        this.departmentService=departmentService;
//    }
//    @Override
//    public Department parse(String text, Locale locale) throws ParseException {
//        return departmentService.findByIdHaveBusiness(Long.parseLong(text));
//    }
//
//    @Override
//    public String print(Department department, Locale locale) {
//        return "["+department.getId()+","+department.getName()+"]" ;
//    }
//}
