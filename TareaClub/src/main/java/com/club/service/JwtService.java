package com.club.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

    public String getToken(UserDetails user){
        return generateToken(new HashMap<>(), user);
    }

    public String getRefreshToken(UserDetails user){
        return generateRefreshToken(new HashMap<>(), user);
    }

    public String getUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Boolean isTokenValid (String token, UserDetails userDetails){
        final String username = getUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private String generateToken(Map<String,Object> extraClaims, UserDetails user){
        return Jwts
                .builder()
                .claim("sub", user.getUsername())
                .claim("iat", new Date(System.currentTimeMillis()))
                .claim("exp", new Date(System.currentTimeMillis()+1000*60*24))
                .claims(extraClaims)
                .signWith(getKey())
                .compact();
    }

    private String generateRefreshToken(Map<String,Object> extraClaims, UserDetails user){
        return Jwts
                .builder()
                .claim("sub", user.getUsername())
                .claim("iat", new Date(System.currentTimeMillis()))
                .claim("exp", new Date(System.currentTimeMillis()+604800000))
                .claims(extraClaims)
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey(){
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
    }
}
