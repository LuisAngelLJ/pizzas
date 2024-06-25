package com.la.pizzeria.web.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    private static String SECRET_KEY = "luis_pizz4";
    private static Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    //crear token
    public String create(String username) {
        return JWT.create()
                .withSubject(username)//asunto
                .withIssuer("luis-angel")//quien crea el token
                .withIssuedAt(new Date())//fecha de creacion
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))//en la fecha actual se le suma 15 dias de expiracion
                .sign(ALGORITHM);//firmar el token, recibe algoritmo
    }


    //validar un jwt con outh0
    public boolean isValid(String jwt) {
        try{
            JWT.require(ALGORITHM)
                    .build()
                    .verify(jwt);

            return true;
        } catch(JWTVerificationException e) {
            return false;
        }
    }

    //saber a que usaurio le pertenece el token
    public String getUsername(String jwt) {
        return JWT.require(ALGORITHM)
                .build()
                .verify(jwt)
                .getSubject();
    }
}
