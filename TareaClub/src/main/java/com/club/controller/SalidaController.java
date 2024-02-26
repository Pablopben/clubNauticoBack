package com.club.controller;

import com.club.dto.BarcoDTO;
import com.club.dto.PersonaDTO;
import com.club.dto.SalidaDTO;
import com.club.entities.Salida;
import com.club.service.SalidaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salidas")
public class SalidaController {

    @Autowired
    SalidaService salidaService;

    @GetMapping
    List<SalidaDTO> listaSalidas() {
        return salidaService.getAllSalidas();
    }

    @GetMapping("/{id}")
    SalidaDTO salidaById(@PathVariable("id") Long id){
        return this.salidaService.getSalidaById(id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteUser (@PathVariable("id") Long id){
        salidaService.deleteSalidaById(id);
    }

    @PostMapping
    public void createSalida (@Valid @RequestBody SalidaDTO salidaDTO){
        Salida s = this.salidaService.transformadeDTO(salidaDTO);
        this.salidaService.saveSalida(s);
    }

    @PutMapping("/update/{id}")
    public void actualizaSalida(@PathVariable ("id") Long id, @Valid @RequestBody SalidaDTO sDTO){
        this.salidaService.actualizaSalida(id, sDTO);
    }

}
