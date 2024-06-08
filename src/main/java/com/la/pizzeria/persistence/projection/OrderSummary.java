package com.la.pizzeria.persistence.projection;

import java.time.LocalDateTime;

public interface OrderSummary {
    //los nomsbre de los métodos pueden ser de cualquier nombre, no es necesario que coincidan con la bd o entity
    Integer getIdOrder();
    String getCustomerName();
    LocalDateTime getOrderDate();
    Double getOrderTotal();
    String getPizzaNames();
}
