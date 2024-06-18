package com.la.pizzeria.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(length = 50)
    private String email;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean locked;//saber si la cuenta esta bloqueada

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean disabled;//saber si la cuenta esta desbloqueada

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)//EAGER para que al consultar UserEntity salga esta info
    private List<UserRoleEntity> roles;
}
