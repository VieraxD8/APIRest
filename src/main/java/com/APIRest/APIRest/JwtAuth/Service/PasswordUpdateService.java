package com.APIRest.APIRest.JwtAuth.Service;

import com.APIRest.APIRest.Repository.UsuarioRepository;
import com.APIRest.APIRest.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUpdateService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void updatePasswords() {
        Iterable<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario usuario : usuarios) {
            String encodedPassword = passwordEncoder.encode(usuario.getPassword());
            usuario.setPassword(encodedPassword);
            usuarioRepository.save(usuario);
        }
    }
}