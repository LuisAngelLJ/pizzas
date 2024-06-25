package com.la.pizzeria.persistence.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
//AuditorAware lo que recibirá es un tipo string por que el username es un string
public class AuditUsername implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        //obtener la autenticacion del security context holder del jwtFilter
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        //capturar el usuario
        String username = authentication.getPrincipal().toString();//getPrincipal es un objet
        return Optional.of(username);
    }
}
