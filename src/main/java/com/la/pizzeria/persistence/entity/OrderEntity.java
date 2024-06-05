package com.la.pizzeria.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pizza_order")
@Setter
@Getter
@NoArgsConstructor //constructor sin argumentos
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Column(name = "id_customer", nullable = false, length = 15)
    private String idCustomer;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime date;

    @Column(nullable = false, columnDefinition = "DECIMAL(6, 2)")
    private Double total;

    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private Character method;

    @Column(name = "additional_notes", length = 200)//como este si puede ser null no es necesario ponerlo con columnDefinition
    private String additionalNotes;

    //relaciones
    @OneToOne(fetch = FetchType.LAZY)//no cargar la información a menos que se use de momento solo esta referenciado y se quitan los select a customer
    @JoinColumn(name = "id_customer", referencedColumnName = "id_customer", insertable = false, updatable = false)
    @JsonIgnore
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)//cuando tratemos de recuperar un OrderEntity me tariga esta relación
    private List<OrderItemEntity> items;
}
