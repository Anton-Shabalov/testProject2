package com.example.testProject.repository;

import com.example.testProject.entity.Person;
import com.example.testProject.model.PersonPage;
import com.example.testProject.model.PersonSeachCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class PersonCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public PersonCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Person> findAllWithFilters(PersonPage personPage,
                                           PersonSeachCriteria personSeachCriteria) {
        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
        Root<Person> personRoot = criteriaQuery.from(Person.class);
        Predicate predicate = getPredicate(personSeachCriteria, personRoot);
        criteriaQuery.where(predicate);
        setOrder(personPage, criteriaQuery, personRoot);

        TypedQuery<Person> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(personPage.getPageNumber() * personPage.getPageSize());
        typedQuery.setMaxResults(personPage.getPageSize());

        return new PageImpl<>(typedQuery.getResultList());
    }

    private Predicate getPredicate(PersonSeachCriteria personSeachCriteria,
                                   Root<Person> personRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(personSeachCriteria.getName())) {
            predicates.add(
                    criteriaBuilder.like(personRoot.get("name"),
                            "%" + personSeachCriteria.getName() + "%")
            );
        }
        if (Objects.nonNull(personSeachCriteria.getSurname())) {
            predicates.add(
                    criteriaBuilder.like(personRoot.get("surname"),
                            "%" + personSeachCriteria.getSurname() + "%")
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(PersonPage personPage,
                          CriteriaQuery<Person> criteriaQuery,
                          Root<Person> personRoot) {
        if (personPage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(personRoot.get(personPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(personRoot.get(personPage.getSortBy())));
        }
    }


}

