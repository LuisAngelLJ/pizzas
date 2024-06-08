package com.la.pizzeria.persistence.repository;

import com.la.pizzeria.persistence.entity.PizzaEntity;
import com.la.pizzeria.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    //findAll con ListCrudRepository regresa una lista y con CrudRepository regresa un Iterable

    //obtener las pizzas disponibles
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

    //recuperar pizza apartir de su nombre - IgnoreCase es para que no importe si es en mayusculas o minusculas
    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);

    //recuperar pizza por ingrediente - IgnoreCase es para que no importe si es en mayusculas o minusculas
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

    //recuperar pizza que no tengan este ingrediente - IgnoreCase es para que no importe si es en mayusculas o minusculas
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

    //cuantas pizzas veganas están en el menú
    int countByVeganTrue();

    //listar las 3 pizzas más baratas dado un precio
    //buscar los primeros 3 de available = true y el precio sea menor o igual y el resultado ordenarlo de manera asendente
    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);

    //actualizar precio con modifying
    @Query(value = "UPDATE pizza "+
            "SET price = :#{#newPizzaPrice.newPrice} " +
            "WHERE id_pizza = :#{#newPizzaPrice.pizzaId}", nativeQuery = true)
    @Modifying
    void updatePrice(@Param("newPizzaPrice") UpdatePizzaPriceDto newPizzaPrice);
}
