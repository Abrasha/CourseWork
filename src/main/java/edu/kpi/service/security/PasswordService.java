package edu.kpi.service.security;

public interface PasswordService {

    String encrypt(String rawPassword);

    boolean matches(String rawPassword, String encoded);

}
