package com.la.pizzeria.persistence.repository;

import com.la.pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    //findAll con ListCrudRepository regresa una lista y con CrudRepository regresa un Iterable
}
