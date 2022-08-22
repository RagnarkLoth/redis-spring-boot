package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/redis")
public class CacheRestController {

    @Autowired
    private CacheManager cacheManager;

    @PatchMapping("/{name}")
    public void eliminarCaache(@PathVariable String name){

        cacheManager.getCache(name).clear();

    }
}
