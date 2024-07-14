package com.APIRest.APIRest.Controllers;

import com.APIRest.APIRest.Curso;
import com.APIRest.APIRest.Repository.CursoRepository;
import com.APIRest.APIRest.Repository.TopicoRepository;
import com.APIRest.APIRest.Repository.UsuarioRepository;
import com.APIRest.APIRest.Request.TopicoRequest;
import com.APIRest.APIRest.Response.TopicoResponse;
import com.APIRest.APIRest.Servicio.TopicoService;
import com.APIRest.APIRest.Topico;
import com.APIRest.APIRest.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<Topico> crearTopico(@RequestBody @Valid TopicoRequest topicoRequest, UriComponentsBuilder uriBuilder) {
        if (topicoRepository.existsByTituloAndMensaje(topicoRequest.getTitulo(), topicoRequest.getMensaje())) {
            return ResponseEntity.badRequest().build();
        }

        Usuario autor = usuarioRepository.findById(topicoRequest.getAutorId()).orElseThrow(() -> new IllegalArgumentException("Autor no encontrado"));
        Curso curso = cursoRepository.findById(topicoRequest.getCursoId()).orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        Topico topico = new Topico();
        topico.setTitulo(topicoRequest.getTitulo());
        topico.setMensaje(topicoRequest.getMensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);

        topicoRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(topico);
    }

    @GetMapping
    public Page<TopicoResponse> listarTopicos(
            @RequestParam(required = false) String cursoNombre,
            @RequestParam(required = false) Integer ano,
            @PageableDefault(sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Topico> topicos;

        if (cursoNombre != null && ano != null) {
            topicos = topicoRepository.findByCursoNombreAndAno(cursoNombre, ano, pageable);
        } else if (cursoNombre != null) {
            topicos = topicoRepository.findByCursoNombre(cursoNombre, pageable);
        } else if (ano != null) {
            topicos = topicoRepository.findByAno(ano, pageable);
        } else {
            topicos = topicoRepository.findAll(pageable);
        }

        return topicos.map(TopicoResponse::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> getTopicoById(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoService.findById(id);
        if (topicoOptional.isPresent()) {
            return ResponseEntity.ok(topicoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> updateTopico(@PathVariable Long id, @Valid @RequestBody Topico topicoDetails) {
        Optional<Topico> topicoOptional = topicoService.findById(id);
        if (topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();
            topico.setTitulo(topicoDetails.getTitulo());
            topico.setMensaje(topicoDetails.getMensaje());
            topico.setStatus(topicoDetails.getStatus());
            topico.setAutor(topicoDetails.getAutor());
            topico.setCurso(topicoDetails.getCurso());
            // Otros campos a actualizar...

            Topico updatedTopico = topicoService.save(topico);
            return ResponseEntity.ok(updatedTopico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopico(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoService.findById(id);
        if (topicoOptional.isPresent()) {
            topicoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
