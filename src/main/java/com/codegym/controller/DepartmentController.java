package com.codegym.controller;

import com.codegym.model.Department;
import com.codegym.model.Employee;
import com.codegym.service.impl.DepartmentService;
import com.codegym.service.impl.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    public DepartmentService departmentService;
    @Autowired
    public EmployeeService employeeService;
    @GetMapping("/")
    public ModelAndView homeDepartment() {
        Iterable<Department> departmentList = departmentService.findAllHaveBusiness();

        ModelAndView modelAndView = new ModelAndView("/departments/home");
        modelAndView.addObject("departments", departmentList);

        return modelAndView;
    }

    @GetMapping("/create-department")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("/departments/create");
        modelAndView.addObject("department", new Department());
        return modelAndView;
    }

    @PostMapping("/save-department")
    public ModelAndView saveProvince(@ModelAttribute("department") Department department) {
        departmentService.saveHaveBusiness(department);
        ModelAndView modelAndView = new ModelAndView("/departments/create");
        modelAndView.addObject("department", new Department());
        modelAndView.addObject("message", "New department created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-department/{id}")
    public ModelAndView showEdit(@PathVariable Long id) {
        Department department = departmentService.findByIdHaveBusiness(id);
        if (department != null) {
            ModelAndView modelAndView = new ModelAndView("/departments/edit");
            modelAndView.addObject("department", department);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-department")
    public ModelAndView updateEdit(@ModelAttribute("department") Department department) {
        departmentService.saveHaveBusiness(department);
        ModelAndView modelAndView = new ModelAndView("/departments/edit");
        modelAndView.addObject("department", department);
        modelAndView.addObject("message", "Province updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        Department department = departmentService.findByIdHaveBusiness(id);
        ModelAndView modelAndView = new ModelAndView("/departments/delete");
        modelAndView.addObject("department", department);
        return modelAndView;
    }

    @PostMapping("/delete-department")
    public ModelAndView deleteDepartment(@ModelAttribute("department") Department department) {
        departmentService.removeHaveBusiness(department.getId());
        ModelAndView modelAndView = new ModelAndView("/departments/delete");
        modelAndView.addObject("department", new Department());
        modelAndView.addObject("message", "Departments delete successfully");
        return modelAndView;
    }
    @GetMapping("/view/{id}")
    public ModelAndView showView(@PathVariable Long id){
        Department department = departmentService.findByIdHaveBusiness(id);
        if(department == null){
            return new ModelAndView("/error.404");
        }

        Iterable<Employee> employees = employeeService.findByDepartment(department);

        ModelAndView modelAndView = new ModelAndView("/departments/view");
        modelAndView.addObject("department", department);
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }
//    @GetMapping("/search")
//    public ModelAndView search(){
//        return null;
//    }
//@GetMapping("/search")
//public ModelAndView search() {
//   // Department department = departmentService.findByIdHaveBusiness(id);
//    ModelAndView modelAndView = new ModelAndView("/departments/search");
//   // modelAndView.addObject("department", department);
//
//    return modelAndView;
//}



}

