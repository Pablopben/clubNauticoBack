package com.club.dto;

import com.club.entities.Socio;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class BarcoDTO implements Serializable {

    private Long id;
    private String matricula;
    private String nombre;
    private Integer numeroAmarre;
    private Integer cuota;
    private Long idDueño;
    private String nombreDueno;

    public BarcoDTO(Long id, String matricula, String nombre, Integer numeroAmarre, Integer cuota, Long idDueño, String nombreDueño) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.numeroAmarre = numeroAmarre;
        this.cuota = cuota;
        this.idDueño = idDueño;
        this.nombreDueno = nombreDueño;
    }
}
