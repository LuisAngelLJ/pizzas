package com.la.pizzeria.persistence.repository;

import com.la.pizzeria.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

//recibe la entidad con la que va a trabajar y el tipo de dato de la llave primaria
public interface CustomerRepository extends ListCrudRepository<CustomerEntity, String> {
    //c alias del resultado, :phone es un parametro que recibe
    @Query(value = "SELECT c FROM CustomerEntity c WHERE c.phoneNumber = :phone")
    CustomerEntity findByPhone(@Param("phone") String phone);
}
