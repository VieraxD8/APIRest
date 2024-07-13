package com.APIRest.APIRest.Servicio;

import com.APIRest.APIRest.Repository.TopicoRepository;
import com.APIRest.APIRest.Topico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public Optional<Topico> findById(Long id) {
        return topicoRepository.findById(id);
    }

    public Topico save(Topico topico) {
        return topicoRepository.save(topico);
    }

    public void deleteById(Long id) {
        topicoRepository.deleteById(id);
    }
}
