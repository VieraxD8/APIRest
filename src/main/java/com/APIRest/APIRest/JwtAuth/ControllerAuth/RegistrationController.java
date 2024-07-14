package com.APIRest.APIRest.JwtAuth.ControllerAuth;


import com.APIRest.APIRest.JwtAuth.Service.CustomUserDetailsService;
import com.APIRest.APIRest.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody Usuario user) {
        customUserDetailsService.saveUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
