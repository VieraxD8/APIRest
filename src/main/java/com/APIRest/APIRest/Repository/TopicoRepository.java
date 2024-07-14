package com.APIRest.APIRest.Repository;

import com.APIRest.APIRest.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    Page<Topico> findByCursoNombre(String cursoNombre, Pageable pageable);

    @Query("SELECT t FROM Topico t WHERE YEAR(t.fechaCreacion) = :ano")
    Page<Topico> findByAno(@Param("ano") int ano, Pageable pageable);

    @Query("SELECT t FROM Topico t WHERE t.curso.nombre = :cursoNombre AND YEAR(t.fechaCreacion) = :ano")
    Page<Topico> findByCursoNombreAndAno(@Param("cursoNombre") String cursoNombre, @Param("ano") int ano, Pageable pageable);


}
