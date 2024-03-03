package com.ecommerce.EcommerceBackend.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
    @CacheEvict(allEntries = true, value = {"products","productDetails","productByCategory"})
    public String clearAllCaches() {
        // This method will evict all entries in the specified caches
        return "Cache cleared";
    }
}
