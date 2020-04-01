package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


    @RestController
    public class PersonController {

    public PersonRepository repository;

    @Autowired
    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/people")
    public ResponseEntity<Person> createPerson(@RequestBody Person p) {
        return new ResponseEntity<>(repository.save(p), HttpStatus.CREATED);
        //201 created
    }
    @GetMapping("/people")
    public ResponseEntity<Person> getPerson(@PathVariable Integer id) {
        return new ResponseEntity<>(repository.findOne(id), HttpStatus.FOUND);
        //200 ok if found, 404 not found
    }
    @GetMapping("/people")
    public ResponseEntity<Person> getPersonList() {
        return new ResponseEntity<>((Person)repository.findAll(), HttpStatus.FOUND);
        //200 ok
    }
    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person p) {
        return new ResponseEntity<>(repository.save(p), HttpStatus.OK);
        //200 ok if updated/ 201 Created if new created
    }
    @DeleteMapping("/people/{id}")
    public ResponseEntity deletePerson(@PathVariable Integer id) {
        repository.delete(id);
        return new ResponseEntity<Person>(HttpStatus.OK);
        //204 no content
    }


}
