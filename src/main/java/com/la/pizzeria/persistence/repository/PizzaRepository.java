package com.la.pizzeria.persistence.repository;

import com.la.pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    //findAll con ListCrudRepository regresa una lista y con CrudRepository regresa un Iterable

    //obtener las pizzas disponibles
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

    //recuperar pizza apartir de su nombre - IgnoreCase es para que no importe si es en mayusculas o minusculas
    PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name);

    //recuperar pizza por ingrediente - IgnoreCase es para que no importe si es en mayusculas o minusculas
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

    //recuperar pizza que no tengan este ingrediente - IgnoreCase es para que no importe si es en mayusculas o minusculas
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

    //cuantas pizzas veganas están en el menú
    int countByVeganTrue();
}
