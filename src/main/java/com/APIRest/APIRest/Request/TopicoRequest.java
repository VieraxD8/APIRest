package com.APIRest.APIRest.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TopicoRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    private String mensaje;

    @NotNull
    private Long autorId;

    @NotNull
    private Long cursoId;

    public @NotBlank String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank String titulo) {
        this.titulo = titulo;
    }

    public @NotBlank String getMensaje() {
        return mensaje;
    }

    public void setMensaje(@NotBlank String mensaje) {
        this.mensaje = mensaje;
    }

    public @NotNull Long getAutorId() {
        return autorId;
    }

    public void setAutorId(@NotNull Long autorId) {
        this.autorId = autorId;
    }

    public @NotNull Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(@NotNull Long cursoId) {
        this.cursoId = cursoId;
    }
}
