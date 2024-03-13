package com.example.redis.controller;


import com.example.redis.dto.PersonDTO;
import com.example.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final RedisService valueCache;

    @Autowired
    public PersonController(final RedisService valueCache) {
        this.valueCache = valueCache;
    }

    @PostMapping
    public void cachePerson(@RequestBody final PersonDTO dto) {
        valueCache.cache(dto.getId(), dto.getName());
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable final String id) {
        return (PersonDTO) valueCache.getCachedValue(id);
    }



    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable final String id) {
        valueCache.deleteCachedValue(id);
    }

}
