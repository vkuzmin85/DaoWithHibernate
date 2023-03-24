package ru.netology.dao_with_hibernate.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.netology.dao_with_hibernate.entity.Persons;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Persons, Long> {
    @Query("select p from Persons p where p.city=:city")
    List<Persons> findPersonsByCity(String city);

    @Query("select p from Persons p where p.contact.age<:age order by p.contact.age asc")
    List<Persons> findPersonsByContactAgeLessThanOrderByContactAgeAsc(Integer age);

    @Query("select p from Persons p where p.contact.name=:name and p.contact.surname=:surname")
    List<Optional<Persons>> findPersonsByContactNameAndContactSurname(String name, String surname);

    @Query("select p from Persons p")
    List<Persons> readAll();

    @Modifying
    @Transactional
    @Query("delete from Persons p where p.contact.name=:name")
    void deletePersonsByName(String name);
}
