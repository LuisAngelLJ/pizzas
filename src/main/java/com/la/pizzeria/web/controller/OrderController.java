package com.la.pizzeria.web.controller;

import com.la.pizzeria.persistence.entity.OrderEntity;
import com.la.pizzeria.persistence.projection.OrderSummary;
import com.la.pizzeria.service.OrderService;
import com.la.pizzeria.service.dto.OrderRandomDto;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll() {
        return ResponseEntity.ok(this.orderService.getAll());
    }

    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getTodayOrders() {
        return ResponseEntity.ok(this.orderService.getTodayOrders());
    }

    @GetMapping("/outside")
    public ResponseEntity<List<OrderEntity>> getOutsideOrders() {
        return ResponseEntity.ok(this.orderService.getOutsideOrders());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String id) {
        return ResponseEntity.ok(this.orderService.getCustomerOrders(id));
    }

    //projection, orden detallada
    @GetMapping("/summary/{orderId}")
    public ResponseEntity<OrderSummary> getSummary(@PathVariable int orderId) {
        return ResponseEntity.ok(this.orderService.getSummary(orderId));
    }

    @PostMapping("/random")
    public ResponseEntity<Boolean> randomOrder(@RequestBody OrderRandomDto dto) {
        return ResponseEntity.ok(this.orderService.saveRandomOrder(dto));
    }
}
