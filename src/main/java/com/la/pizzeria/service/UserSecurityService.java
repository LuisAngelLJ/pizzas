package com.la.pizzeria.service;

import com.la.pizzeria.persistence.entity.UserEntity;
import com.la.pizzeria.persistence.entity.UserRoleEntity;
import com.la.pizzeria.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //al usar UserDetailsService debo implementar este método
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //buscar un usurio en la BD
        UserEntity userEntity = this.userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("User " + username + " Not Found"));

        //convertir la lista de roles de UserEntity en string para el metodo roles
        String[] roles = userEntity.getRoles().stream().map(UserRoleEntity::getRole).toArray(String[]::new);
        //si encontramos al usuario
        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(this.grantedAuthorities(roles))
                .accountLocked(userEntity.getLocked())//saber si un usuario esta bloqueado
                .disabled(userEntity.getDisabled())
                .build();//esto lo convierte en un UserDetils a como el método pide el retorno
    }

    //permisos especificos
    private String[] getAuthorities(String role) {
        if ("ADMIN".equals(role) || "CUSTOMER".equals(role)) {
            //permiso individual
            return new String[] {"ramdom_order"};
        }

        //si no es ningun rol anterior retorno
        return new String[] {};
    }

    //recibe los roles y aparte de forma individual asigna authorities
    private List<GrantedAuthority> grantedAuthorities(String[] roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);

        //asignarlos
        for (String role: roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));

            for (String authority: this.getAuthorities(role)) {
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }
        return authorities;
    }
}
