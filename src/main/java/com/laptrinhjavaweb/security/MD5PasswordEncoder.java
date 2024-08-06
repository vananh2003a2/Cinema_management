package com.laptrinhjavaweb.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MD5PasswordEncoder implements PasswordEncoder{

	@Override
	public String encodePassword(String rawPass, Object salt) {
		try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(rawPass.toString().getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		 return encodePassword(rawPass, salt).equals(encPass);
	}

}
