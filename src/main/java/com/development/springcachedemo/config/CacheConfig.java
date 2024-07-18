package com.development.springcachedemo.config;

import com.development.springcachedemo.model.Department;
import com.development.springcachedemo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private DepartmentService departmentService;

    @PostConstruct
    public void PreLoadCache() {

        Cache cache = cacheManager.getCache("applicationCache");

        System.out.println("***************Initialising Cache********");

        List<Department> deptList= departmentService.getAll();

        for(Department department:deptList) {
            cache.put(department.getId(),department);
        }

    }

    @Scheduled(fixedRate = 1500000, initialDelay = 15000)
    public void clearCache() {
        System.out.println("*** cache cleared ***");
        cacheManager.getCacheNames().parallelStream().forEach(
                name  -> cacheManager.getCache(name).clear()
        );

    }

}
