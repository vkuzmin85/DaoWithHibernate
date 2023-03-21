package ru.netology.dao_with_hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import ru.netology.dao_with_hibernate.entity.Persons;

import java.util.List;

@Repository
public class PersonRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Persons> getPersonsByCity(String city) {
        Query query = entityManager.createQuery("select p from Persons p where lower(p.city)= lower(:city)", Persons.class);
        query.setParameter("city", city);
        return query.getResultList();
    }
}
