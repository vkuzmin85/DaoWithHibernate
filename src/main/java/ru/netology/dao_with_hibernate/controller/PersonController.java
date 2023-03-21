package ru.netology.dao_with_hibernate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.dao_with_hibernate.entity.Persons;
import ru.netology.dao_with_hibernate.repository.PersonRepository;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private PersonRepository repo;

    public PersonController(PersonRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/by-city")
    public ResponseEntity<List<Persons>> getPersons(@RequestParam("city") String city) {
        List<Persons> persons = repo.getPersonsByCity(city);
        return ResponseEntity.ok().body(persons);
    }
}
