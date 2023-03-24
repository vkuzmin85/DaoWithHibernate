package ru.netology.dao_with_hibernate.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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

    @GetMapping("/read")
    @Secured({"ROLE_READ"})
    public ResponseEntity<List<Persons>> getPersons() {
        List<Persons> persons = repo.readAll();
        return ResponseEntity.ok().body(persons);
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAnyRole('ROLE_WRITE', 'ROLE_DELETE')")
    public ResponseEntity<List<Persons>> deletePersons(@RequestParam("name") String name) {
        repo.deletePersonsByName(name);
        List<Persons> persons = repo.readAll();
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/by-city")
    @RolesAllowed({"ROLE_WRITE"})
    public ResponseEntity<List<Persons>> getPersonsByCity(@RequestParam("city") String city) {
        List<Persons> persons = repo.findPersonsByCity(city);
        return ResponseEntity.ok().body(persons);
    }

    @GetMapping("/byuser")
    @PreAuthorize("#username==authentication.principal.username")
    public String checkUser(String username) {
        return "Hello from secure app " + username;
    }

    //http://localhost:8080/persons/by-name-surname?name=nick&surname=elton
    @GetMapping("/by-name-surname")
    public ResponseEntity<List<Optional<Persons>>> getPersonsByNameAndSurname(@RequestParam("name") String name, @RequestParam("surname") String surname) {
        return ResponseEntity.ok().body(repo.findPersonsByContactNameAndContactSurname(name, surname));
    }
}
