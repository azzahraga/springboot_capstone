// package com.project.capstone.security.jwt;

// import java.security.Key;
// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;

// import org.springframework.security.core.Authentication;
// import org.springframework.stereotype.Component;

// import com.project.capstone.domain.dao.User;
// import com.project.capstone.service.implementations.UserDetailsImpl;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.ExpiredJwtException;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.MalformedJwtException;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.UnsupportedJwtException;
// import io.jsonwebtoken.security.Keys;
// import lombok.extern.log4j.Log4j2;

// @Log4j2
// @Component
// public class JwtProvider {
//     private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

//     private Long expiration = 1000L *60*60;

//     public String generatedToken(Authentication authentication){
//         final User user = (User) authentication.getPrincipal();

//         Date now = new Date(System.currentTimeMillis());
//         Date expiryDate = new Date(now.getTime()+expiration);

//         Map<String, Object> claims = new HashMap<>();
//         claims.put("username", user.getUsername());

//         return Jwts.builder()
//             .setId(user.getId().toString())
//             .setSubject(user.getUsername())
//             .setClaims(claims)
//             .setIssuedAt(now)
//             .setExpiration(expiryDate)
//             .signWith(key)
//             .compact();
//     }

//     // public String generatedToken(Authentication authentication) {
//     //     //final User user = (User) authentication.getPrincipal();
//     //     UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

//     //     Date now = new Date(System.currentTimeMillis());

//     //     // Map<String, Object> claims = new HashMap<>();
//     //     // claims.put("email", user.getUsername());

//     //     return Jwts.builder()
//     //         .setId(userPrincipal.getId().toString())
//     //         //.setSubject(user.getUsername())
//     //         .setSubject((userPrincipal.getUsername()))
//     //         //.setClaims(claims)
//     //         .setIssuedAt(now)
//     //         .signWith(key)
//     //         .compact();
//     // }

//     public boolean validatedToken(String token){

//         try{
//             Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//             return true;
//         }
//         catch(MalformedJwtException ex){
//             log.error("Invalid Jwt Token: {}", ex.getMessage());
//         }catch(UnsupportedJwtException ex){
//             log.error("Unsupported Jwt Token: {}", ex.getMessage());
//         }catch(ExpiredJwtException ex){
//             log.error("Expired Jwt Token: {}", ex.getMessage());
//         }catch(IllegalArgumentException ex){
//             log.error("Jwt claim string is empty: {}", ex.getMessage());
//         }
//         return false;
//     }

//     public String getUsername(String token){
//         Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//         return claims.get("username").toString();
//     }
// }
