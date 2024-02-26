package com.club.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "Salida")
public class Salida implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "salidaGenerator")
    @SequenceGenerator(name="salidaGenerator", initialValue = 5)
    @Column(name = "SALIDA_ID")
    private Long id;

    @Column(name = "FECHA")
    @NotBlank
    //@Pattern(regexp = "(0[1-9]|[12][0-9]|3[01])\\/(0[1-9]|1[1,2])\\/20\\d{2}", message = "La fecha debe de estar en el formato dd/mm/YYYY")
    private String fecha;

    @Column(name = "HORA")
    @NotNull
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$", message = "La hora debe de estar en el formato hh:mm:ss")
    private String hora;

    @Column(name = "DESTINO")
    @NotBlank(message = "La salida debe de tener un destino")
    private String destino;

    @ManyToOne
    @JoinColumn(name = "BARCO_ID", nullable = false)
    @NotNull
    private Barco barco;

    @ManyToOne
    @JoinColumn(name = "PERSONA_ID", nullable = false)
    @NotNull
    private Persona patron;

    public Salida() {
    }

    public Salida(String fecha, String hora, String destino, Barco barco, Persona patron) {
        this.fecha = fecha;
        this.hora = hora;
        this.destino = destino;
        this.barco = barco;
        this.patron = patron;
    }

    @Override
    public String toString() {
        return "Salida{" +
                "destino='" + destino + '\'' +
                ", barco=" + barco +
                ", patron=" + patron +
                '}';
    }
}
