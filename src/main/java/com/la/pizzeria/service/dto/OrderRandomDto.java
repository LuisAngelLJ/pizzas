package com.la.pizzeria.service.dto;

import lombok.Data;

//esta anotación porque es un data transfert object
@Data
public class OrderRandomDto {
    private String idCustomer;
    private String method;
}
