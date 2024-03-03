package com.ecommerce.EcommerceBackend.controller;

import com.ecommerce.EcommerceBackend.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    CacheService cacheService;

    @GetMapping
    public String clearCache(){
        return cacheService.clearAllCaches();
    }
}
