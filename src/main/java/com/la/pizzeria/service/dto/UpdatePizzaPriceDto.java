package com.la.pizzeria.service.dto;

import lombok.Data;

//esta anotación porque es un data transfert object
@Data
public class UpdatePizzaPriceDto {
    private int pizzaId;
    private double newPrice;
}
