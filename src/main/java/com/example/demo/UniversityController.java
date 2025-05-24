package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
public class UniversityController {
    private final UniversityService service;

    public UniversityController(UniversityService service) {
        this.service = service;
    }

    //requests
    @GetMapping("/all")
    public List<University> getAllUni() {
        return service.getAll();
    }

    // get univ by id
    //@RequestMapping(method = RequestMethod.GET , path = "{id}")
    @GetMapping("{id}")
    public University getById(@PathVariable int id) {
        return service.getUnivById(id);
    }

    //add
    @PostMapping
    public void insertUniversity(@RequestBody University university) {
        service.addUniversity(university);
    }

    //update
    @PutMapping("{id}")
    public void update(@RequestBody University newUniversity, @PathVariable int id) {
        service.updateUniversity(newUniversity, id);
    }
    //delete
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        service.deleteUniversity(id);
    }

}
