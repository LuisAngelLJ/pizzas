package com.la.pizzeria.persistence.repository;

import com.la.pizzeria.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
    // saber todas las ordenes que ha tenido la pizzaria el dia de hoy
    List<OrderEntity> findAllByDateAfter(LocalDateTime date);

    //listado de ordenes que han sido para entrega o recoger - con in debo recibir una lista
    List<OrderEntity> findAllByMethodIn(List<String> methods);
}