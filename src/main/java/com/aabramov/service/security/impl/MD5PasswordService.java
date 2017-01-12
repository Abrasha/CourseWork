package com.aabramov.service.security.impl;

import com.aabramov.service.security.PasswordService;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MD5PasswordService implements PasswordService {

    private final MessageDigest md;

    public MD5PasswordService() {
        try {
            this.md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public synchronized String encrypt(String rawPassword) {

        try {
            md.update(rawPassword.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        final byte[] rawHash = md.digest();
        return new String(Base64.getEncoder().encode(rawHash));
    }

    @Override
    public synchronized boolean matches(String rawPassword, String encoded) {
        return encrypt(rawPassword).equals(encoded);
    }
}
