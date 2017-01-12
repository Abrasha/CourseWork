package com.aabramov.service.security;

public interface PasswordService {

    String encrypt(String rawPassword);

    boolean matches(String rawPassword, String encoded);

}
