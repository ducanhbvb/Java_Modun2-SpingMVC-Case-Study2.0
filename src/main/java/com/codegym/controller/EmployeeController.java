package com.codegym.controller;

import com.codegym.model.Department;
import com.codegym.model.Employee;
import com.codegym.model.EmployeeForm;
import com.codegym.service.impl.DepartmentService;
import com.codegym.service.impl.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    Environment env;
    @Autowired
    public EmployeeService employeeService;
    @Autowired
    public DepartmentService departmentService;
    @ModelAttribute("departments")
    public Iterable<Department> departments(){

        return departmentService.findAllHaveBusiness();
    }
    @GetMapping("")
    public ModelAndView homeEmployee(@RequestParam("s") Optional<String> s, @PageableDefault(value = 3) Pageable pageable) {

        Page<Employee> employees;
        if (s.isPresent()){
            employees = employeeService.findAllByFirstNameContaining(s.get(),pageable);
        } else {
            employees = employeeService.findAll(pageable);
        }
        //Iterable<Employee> employees = employeeService.findAllHaveBusiness();

        ModelAndView modelAndView = new ModelAndView("/employees/home");
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }
    @GetMapping("/create-employee")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("/employees/create");
        modelAndView.addObject("employeeForm", new EmployeeForm());
        System.out.println(">>>>> ");
        return modelAndView;
    }
    //begin
    @PostMapping("/save-employee")
    public ModelAndView saveProduct( @ModelAttribute("employeeForm") EmployeeForm employeeForm) {
        // thong bao neu xay ra loi
//    if (result.hasErrors()) {
//        ModelAndView modelAndView = new ModelAndView("/product/create");
//        modelAndView.addObject("product", new ProductForm());
//        return modelAndView;
//    }
        // lay ten file
//        String date=employeeForm.getBirthDate();
//        LocalDate dateTime=LocalDate.parse(date,DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        MultipartFile multipartFile = employeeForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();
        // luu file len server
        try {
            //multipartFile.transferTo(imageFile);
            FileCopyUtils.copy(employeeForm.getAvatar().getBytes(), new File(fileUpload + fileName));
            // tham khảo: https://github.com/codegym-vn/spring-static-resources
        } catch (IOException e) {
            e.printStackTrace();
        }
        // tao doi tuong de luu vao db
        Employee employeeObject = new Employee(employeeForm.getName(), employeeForm.getBirthDate(),employeeForm.getAddress(),
                fileName,employeeForm.getSalary(),employeeForm.getDepartment());
        // luu vao db
        //productService.save(productObject);
        employeeService.saveHaveBusiness(employeeObject);
        ModelAndView modelAndView = new ModelAndView("/employees/create");
        modelAndView.addObject("employeeForm", new EmployeeForm());
        modelAndView.addObject("message", "successes!");
        return modelAndView;
    }
//end
    @GetMapping("/edit-employee/{id}")
    public ModelAndView editEmployee(@PathVariable Long id){
        Employee employee=employeeService.findByIdHaveBusiness(id);
       // employee.setDepartment(new Department());
//        System.out.println(">>> employee edit data:"+employee);
        if(employee!=null){
            ModelAndView modelAndView=new ModelAndView("/employees/edit");
            modelAndView.addObject("employeeForm",employee);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/edit-employee")
    public ModelAndView updateCustomer(@ModelAttribute("employeeForm") EmployeeForm employeeForm) {
        MultipartFile multipartFile = employeeForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();
        // luu file len server
        try {
            FileCopyUtils.copy(employeeForm.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        employeeService.editEmployee(employeeForm,fileName);
        // employeeService.saveHaveBusiness(employeeObject);
        ModelAndView modelAndView = new ModelAndView("/employees/edit");
        modelAndView.addObject("employeeForm", new EmployeeForm());
        modelAndView.addObject("message", "successes!");
        return modelAndView;
    }
    @GetMapping("/delete-employee/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        Employee employee=employeeService.findByIdHaveBusiness(id);
        if(employee!=null){
            ModelAndView modelAndView=new ModelAndView("/employees/delete");
            modelAndView.addObject("employee",employee);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/delete-employee")
    public ModelAndView deleteEmployee(@ModelAttribute("employee") Employee employee) {

        employeeService.removeHaveBusiness(employee.getId());
        // employeeService.saveHaveBusiness(employeeObject);
        ModelAndView modelAndView = new ModelAndView("/employees/delete");
        modelAndView.addObject("employee", new Employee());
        modelAndView.addObject("message", " delete successes!");
        return modelAndView;
    }
    @GetMapping("/view-employee/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id){
        Employee employee = employeeService.findByIdHaveBusiness(id);
        if(employee == null){
            return new ModelAndView("/error.404");
        }
        ModelAndView modelAndView = new ModelAndView("/employees/view");
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }
    @GetMapping("/sort")
    public ModelAndView sort(){
        ArrayList<Employee> employees=employeeService.sortSalaryEmployee();
        ModelAndView modelAndView = new ModelAndView("/employees/sort");
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }


}
