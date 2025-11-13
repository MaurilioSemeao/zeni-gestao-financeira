package com.msdev.backend.security.jwt;

import com.msdev.backend.security.exception.JwtValidationException;
import com.msdev.backend.security.service.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    private static  final Logger logger =  LoggerFactory.getLogger(JwtUtils.class);


    @Value("${bezkoder.app.jwtSecret}")
    private String jwtSecret;

    @Value("${bezkoder.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(UserDetailsImpl userDetailsImpl){
        return Jwts.builder()
                .subject((userDetailsImpl.getUsername()))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigninKey())
                .compact();
    }


    public Key getSigninKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUserNameFromJwtToken(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser()
                    .verifyWith((SecretKey) getSigninKey())
                    .build()
                    .parseSignedClaims(authToken);
            return true;
        }
        catch (MalformedJwtException e){
            logger.error("Invalid JWT token: {} ", e.getMessage());
            throw new JwtValidationException("Token JWT com assinatura inválida: ");
        }
        catch (SignatureException e){
            logger.error("Invalid JWT signature: {} ", e.getMessage());
            throw new JwtValidationException("Token JWT com assinatura inválida: ");
        }
        catch (ExpiredJwtException e ){
            logger.error("JWT token is expired: {} ", e.getMessage());
            throw new JwtValidationException("Token JWT expirado. ");
        }
        catch (IllegalArgumentException e){
            logger.error("JWT claims string is empty: {}", e.getMessage());
            throw new JwtValidationException("Payload do token JWT está vazio.");
        }
        catch (UnsupportedJwtException e){
            logger.error("JWT token is unsupported: {}", e.getMessage());
            throw new JwtValidationException("Token JWT não suportado");
        }

    }


}
