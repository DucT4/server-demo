package com.cybersoft.uniclub.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtHelper {


    @Value("${jwt.private-key}")
   private String secretKey;

      private final long expirationMs= 3600*1000; //1 gio
    public String generateToken (String data) {
        //chuyen secretKey tu chuoi base64 thanh SecretKey
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        Date curentDate = new Date();
        Date futureDate = new Date(curentDate.getTime()  + expirationMs);
       return   Jwts.builder().subject(data).signWith(key).expiration(futureDate).compact();

    }
     public String decodeToken (String token) {

         SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
         return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
     }

}
