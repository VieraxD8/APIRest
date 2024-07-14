package com.APIRest.APIRest.JwtAuth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderExample {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "tu_contrasena";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println(encodedPassword); // Esto imprimirá la contraseña encriptada
    }
}
