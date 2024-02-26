package com.club.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class SalidaDTO implements Serializable {

    private Long id;
    private String destino;
    private String fecha;
    private String hora;
    private Long idPatron;
    private Long idBarco;
    private String nombrePatron;
    private String matriculaBarco;
    private String nombreBarco;

    public SalidaDTO(Long id, String destino, String fecha, String hora, Long idPatron, Long idBarco, String nombrePatron, String matriculaBarco, String nombreBarco) {
        this.id = id;
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
        this.idPatron = idPatron;
        this.idBarco = idBarco;
        this.nombrePatron = nombrePatron;
        this.matriculaBarco = matriculaBarco;
        this.nombreBarco = nombreBarco;
    }




}
