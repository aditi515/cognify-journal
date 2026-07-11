package com.aditi.cognify_journal.auth.jwt;

public interface JwtService {
    String generateToken(String email);

    String extractEmail(String token);

    boolean isTokenValid(String token);
}
