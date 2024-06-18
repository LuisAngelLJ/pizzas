package com.la.pizzeria.service;

import com.la.pizzeria.persistence.entity.OrderEntity;
import com.la.pizzeria.persistence.projection.OrderSummary;
import com.la.pizzeria.persistence.repository.OrderRepository;
import com.la.pizzeria.service.dto.OrderRandomDto;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll() {
        List<OrderEntity> orders = this.orderRepository.findAll();
        //operación para ver el cliente de cada orden
        orders.forEach(order -> System.out.println(order.getCustomer().getName()));
        return orders;
    }

    // saber todas las ordenes que ha tenido la pizzaria el dia de hoy
    public List<OrderEntity> getTodayOrders() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.orderRepository.findAllByDateAfter(today);
    }

    //listado de ordenes que han sido para entrega o recoger
    public List<OrderEntity> getOutsideOrders() {
        //crear lista de string - (D domicilio), (C llevar), (S para comer en el comercio)
        List<String> methods = Arrays.asList("D", "C");
        return this.orderRepository.findAllByMethodIn(methods);
    }

    //ordernes de cliente
    @Secured("ADMIN")
    public List<OrderEntity> getCustomerOrders(String idCustomer) {
        return this.orderRepository.findCustomerOrders(idCustomer);
    }

    //projection, orden detallada
    public OrderSummary getSummary(int orderId) {
        return this.orderRepository.findSummary(orderId);
    }

    @Transactional
    //procedure para el descuento - los parametros los recibo a partir de un dto
    public boolean saveRandomOrder(OrderRandomDto randomOrderDto) {
        return this.orderRepository.saveRandomOrder(randomOrderDto.getIdCustomer(), randomOrderDto.getMethod());
    }
}
