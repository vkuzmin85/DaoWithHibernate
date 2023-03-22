package ru.netology.dao_with_hibernate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.dao_with_hibernate.entity.Persons;
import ru.netology.dao_with_hibernate.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private PersonRepository repo;

    public PersonController(PersonRepository repository) {
        this.repo = repository;
    }

    @GetMapping("/by-city")
    public ResponseEntity<List<Persons>> getPersonsByCity(@RequestParam("city") String city) {
        List<Persons> persons = repo.findPersonsByCity(city);
        return ResponseEntity.ok().body(persons);
    }

    @GetMapping("/by-age")
    public ResponseEntity<List<Persons>> getPersonsByAge(@RequestParam("age") Integer age) {
        List<Persons> persons = repo.findPersonsByContactAgeLessThanOrderByContactAgeAsc(age);
        return ResponseEntity.ok().body(persons);
    }

    //http://localhost:8080/persons/by-name-surname?name=nick&surname=elton
    @GetMapping("/by-name-surname")
    public ResponseEntity<List<Optional<Persons>>> getPersonsByNameAndSurname(@RequestParam("name") String name, @RequestParam("surname") String surname) {
        return ResponseEntity.ok().body(repo.findPersonsByContactNameAndContactSurname(name, surname));
    }
}
