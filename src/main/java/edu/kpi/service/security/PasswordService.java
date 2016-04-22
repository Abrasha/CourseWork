package edu.kpi.service.security;

// TODO Proxy Pattern
public interface PasswordService {

    String encrypt(String rawPassword);

    boolean matches(String rawPassword, String encoded);

}
