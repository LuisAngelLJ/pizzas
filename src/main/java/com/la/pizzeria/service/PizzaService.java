package com.la.pizzeria.service;

import com.la.pizzeria.PizzeriaApplication;
import com.la.pizzeria.persistence.entity.PizzaEntity;
import com.la.pizzeria.persistence.repository.PizzaPagSortRepository;
import com.la.pizzeria.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;//al poner final me pide ponerlo en un constructor
    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    public Page<PizzaEntity> getAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);//findAll de tipo pegeable
    }

    public PizzaEntity get(int idPizza) {
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza) {
        return this.pizzaRepository.save(pizza);
    }

    //saber si una pizza existe
    public boolean exist(int idPizza) {
        return this.pizzaRepository.existsById(idPizza);
    }

    public void delete(int idPizza) {
        this.pizzaRepository.deleteById(idPizza);
    }

    //obtener las pizzas disponibles
    public List<PizzaEntity> getAvailable() {
        //saber las pizzas veganas que hay en el menu
        System.out.println(this.pizzaRepository.countByVeganTrue());
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    //recuperar pizza apartir de su nombre
    public PizzaEntity getByName(String name) {
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElseThrow(() -> new RuntimeException("La pizza no existe"));
    }

    //recuperar pizza por ingrediente
    public List<PizzaEntity> getWith(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    //recuperar pizza que no tenga este ingrediente
    public List<PizzaEntity> getWithout(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    //listar las 3 pizzas más baratas dado un precio
    public List<PizzaEntity> getCheapest(double price) {
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }
}
