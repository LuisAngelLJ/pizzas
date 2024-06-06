package com.la.pizzeria.persistence.repository;

import com.la.pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

//recibe la entidad con la que va a trabajar y el tipo de dato de la llave primaria
public interface PizzaPagSortRepository extends PagingAndSortingRepository<PizzaEntity, Integer> {
    //retornar una página con pizzas disponibles ordenadar por cualquier atributo - findBy = findAllBy
    Page<PizzaEntity> findByAvailableTrue(Pageable pageable);
}
