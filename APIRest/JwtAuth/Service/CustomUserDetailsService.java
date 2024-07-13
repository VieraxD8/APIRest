package com.APIRest.APIRest.JwtAuth.Service;

import com.APIRest.APIRest.Repository.UsuarioRepository;
import com.APIRest.APIRest.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (!usuario.isPresent()) {
            throw new UsernameNotFoundException("Usuario no encontrado con email: " + email);
        }

        return new org.springframework.security.core.userdetails.User(
                usuario.get().getEmail(),
                usuario.get().getPassword(),
                new ArrayList<>()
        );
    }
}


