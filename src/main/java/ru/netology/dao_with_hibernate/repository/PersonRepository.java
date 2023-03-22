package ru.netology.dao_with_hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netology.dao_with_hibernate.entity.Persons;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Persons, Long> {
    List<Persons> findPersonsByCity(String city);

    List<Persons> findPersonsByContactAgeLessThanOrderByContactAgeAsc(Integer age);

    List<Optional<Persons>> findPersonsByContactNameAndContactSurname(String name, String surname);
}
