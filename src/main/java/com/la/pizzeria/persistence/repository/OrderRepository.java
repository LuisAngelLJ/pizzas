package com.la.pizzeria.persistence.repository;

import com.la.pizzeria.persistence.entity.OrderEntity;
import com.la.pizzeria.persistence.projection.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

//recibe la entidad con la que va a trabajar y el tipo de dato de la llave primaria
public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
    // saber todas las ordenes que ha tenido la pizzaria el dia de hoy
    List<OrderEntity> findAllByDateAfter(LocalDateTime date);

    //listado de ordenes que han sido para entrega o recoger - con in debo recibir una lista
    List<OrderEntity> findAllByMethodIn(List<String> methods);

    @Query(value = "SELECT * FROM pizza_order WHERE id_customer = :id", nativeQuery = true)
    List<OrderEntity> findCustomerOrders(@Param("id") String idCustomer);

    //utilizo la interface OrderSummary
    @Query(value = "SELECT  po.id_order AS idOrder, cu.name AS customerName, po.date AS orderDate," +
            "        po.total AS orderTotal, GROUP_CONCAT(pi.name) AS pizzaNames " +
            "FROM   pizza_order po  " +
            "   INNER JOIN customer cu ON po.id_customer = cu.id_customer  " +
            "   INNER JOIN order_item oi ON po.id_order = oi.id_order  " +
            "   INNER JOIN pizza pi ON oi.id_pizza = pi.id_pizza  " +
            "WHERE  po.id_order = :orderId " +
            "GROUP BY po.id_order, cu.name, po.date, po.total", nativeQuery = true)
    OrderSummary findSummary(@Param("orderId") int orderId);
}