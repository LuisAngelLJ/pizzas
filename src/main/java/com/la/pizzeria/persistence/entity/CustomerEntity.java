package com.la.pizzeria.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Setter
@Getter
@NoArgsConstructor //constructor sin argumentos
public class CustomerEntity {
    @Id
    //no tiene Generated value porque se creará manualmente
    @Column(name = "id_customer", nullable = false, length = 15)
    private String idCustomer;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(length = 100)
    private String address;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    //relacion
    @OneToOne(mappedBy = "customer")
    private OrderEntity order;
}
