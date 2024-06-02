package com.ijse.possystem.security.jwt;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

import java.security.KeyPairGenerator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Configuration
public class JwtUtils {

    /*yr */
    public static PrivateKey loadPrivateKey(String path) throws Exception {
        byte[] keyBytes = Files.readAllBytes(Paths.get(path));
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("EC");
        return kf.generatePrivate(spec);
    }
    /*yr */
    public static PublicKey loadPublicKey(String path) throws Exception {
        byte[] keyBytes = Files.readAllBytes(Paths.get(path));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("EC");
        return kf.generatePublic(spec);
    }
    
    @Value("${app.secret}")
    private String secret;

    //@PostConstruct/*yr annotation */
    private Key key(){
        Key key=Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)); //generate key with secret
        System.out.println("key->"+key);
        return key;
    }

    public String generateJwtToken(Authentication authentication){

        

        UserDetails userDetails=(UserDetails)authentication.getPrincipal();
        return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date(new Date().getTime()+1000*60*60*24))
        .signWith(key(),SignatureAlgorithm.ES256)
        .compact();
    }

    public boolean validateJwtToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
            return true;
        } catch (Exception e) {
           e.printStackTrace();
           return false;
        }
    }

    public String getUsernameFromJwtToken(String token){
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
    }
}
