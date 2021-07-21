package com.fsoft.ez.common.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60 * 10000;

    @Value("${jwt.secret}")
    private String secret;

    /**
     * retrieve username from jwt token
     *
     * @param token token of user
     * @return username
     */
    public String getEmailFromToken(String token) {
        return this.getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * retrieve expiration date from jwt token
     *
     * @param token token of user
     * @return expiration date
     */
    public Date getExpirationDateFromToken(String token) {
        return this.getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * retrieve infomation of user from token
     *
     * @param <T> T
     * @param token token
     * @param claimsResolver claimsResolver
     * @return claims of user
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * get claims of user
     *
     * @param token token
     * @return claims of user
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
    }

    /**
     * check if the token has expired
     *
     * @param token token of user
     * @return true if token is valid, otherwise false
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * generate token for user
     *
     * @param email email of user
     * @return token
     */
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return this.doGenerateToken(claims, email);
    }

    /**
     * generate token
     *
     * @param claims claims of user
     * @param email email of user
     * @return token
     */
    private String doGenerateToken(Map<String, Object> claims, String email) {
        return Jwts.builder().setClaims(claims).setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()
                        + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, this.secret).compact();
    }

    /**
     * validate token
     *
     * @param token token
     * @return true if token is valid, otherwise false
     */
    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return !this.isTokenExpired(token);
        } catch (SignatureException ex) {
            throw ex;
        } catch (MalformedJwtException ex) {
            throw ex;
        } catch (ExpiredJwtException ex) {
            throw ex;
        } catch (UnsupportedJwtException ex) {
            throw ex;
        } catch (IllegalArgumentException ex) {
            throw ex;
        }
    }

}