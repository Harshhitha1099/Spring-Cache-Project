package com.development.springcachedemo.controller;


import java.util.List;

import com.development.springcachedemo.model.Department;
import com.development.springcachedemo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping("/dept/findAll")
    public List<Department> findAll() {
        return service.getAll();
    }

    @GetMapping("/dept/find/{id}")
    public Department findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping("/dept/clearallcache")
    public String clearAllCache () {
        service.clearAllCache();
        return " All Cache Cleared ";
    }

    @GetMapping("/dept/clearDataByid/{id}")
    public String clearDataByid(@PathVariable Integer id) {
        service.clearDataCache(id);
        return "data cleared "+ id;

    }

    @PostMapping("/dept/")
    public Department createDepartment(@RequestBody Department department) {
        return service.createDepartment(department);
    }

    @PutMapping("/dept/{id}")
    public Department updateById(@PathVariable Integer id, @RequestBody Department department) {
        return service.updateById(id, department);
    }

    @DeleteMapping("/dept/{id}")
    public String deleteById(@PathVariable Integer id) {
        service.deleteById(id);
        return "Department deleted with id "+ id;
    }

}
