package com.development.springcachedemo.service;

import java.util.List;
import com.development.springcachedemo.model.Department;
import com.development.springcachedemo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;


    public List<Department> getAll() {
        return repository.findAll();
    }

    @Cacheable(value="applicationCache", key="#deptId")
	public Department findById(Integer deptId) {
		return repository.findById(deptId).get();
	}

    @CacheEvict(value="applicationCache", allEntries = true)
    public void clearAllCache() {
        System.out.println("****All Cache evicted****");
    }

    @CacheEvict(value="applicationCache", key="#deptId")
    public void clearDataCache(Integer deptId) {
        System.out.println("Data removed from Cache " +deptId);
    }


    @CachePut(value="applicationCache", key="#deptId")
    public Department updateById(Integer deptId, Department department) {
         Department dept = repository.findById(deptId).get();
         if( dept!=null) {
             repository.save(department);
         }
         return department;
    }

    @CacheEvict(value="applicationCache",key="#deptId")
    public void deleteById(Integer deptId) {
        repository.deleteById(deptId);
    }

    public Department createDepartment(Department department) {
        return repository.save(department);
    }


}