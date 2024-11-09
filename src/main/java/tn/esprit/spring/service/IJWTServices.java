package tn.esprit.spring.service;

import org.springframework.security.core.userdetails.UserDetails;
import tn.esprit.spring.enities.User;

import java.util.HashSet;
import java.util.Map;

public interface IJWTServices {
    String extractUsername(String token);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
    String generateRefreshToken(Map<String,Object> extraClaims, UserDetails userDetails);
}
