package com.club.controller;

import com.club.dto.BarcoDTO;
import com.club.entities.Barco;
import com.club.service.BarcoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/barcos")
public class BarcoController {

    @Autowired
    BarcoService barcoService;

    @GetMapping
    List<BarcoDTO> listaBarcos() {
        List<BarcoDTO> barcos = barcoService.getAllBarcos();
        return barcos;
    }

    @GetMapping("/{id}")
    BarcoDTO barcoById(@PathVariable("id") Long id){
        return this.barcoService.transformaDTO(this.barcoService.getBarcoById(id));
    }

    @DeleteMapping("delete/{id}")
    public void deleteUser (@PathVariable("id") String id){

        barcoService.deleteBarcoById(id);
    }

    @PostMapping
    public void creaBarco(@Valid @RequestBody BarcoDTO bDTO){
        Barco b = this.barcoService.transformadeDTO(bDTO);
        barcoService.saveBarco(b);
    }

    @PutMapping("/update/{id}")
    public void actualizaBarco(@PathVariable ("id") Long id, @Valid @RequestBody BarcoDTO bDTO){
        this.barcoService.actualizaBarco(id, bDTO);
    }

}
