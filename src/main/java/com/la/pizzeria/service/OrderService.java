package com.la.pizzeria.service;

import com.la.pizzeria.persistence.entity.OrderEntity;
import com.la.pizzeria.persistence.repository.OrderRepository;
import org.springframework.stereotype.Service;

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
}
