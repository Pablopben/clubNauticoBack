package com.club.controller;

import com.club.dto.PersonaDTO;
import com.club.dto.SalidaDTO;
import com.club.entities.Persona;
import com.club.service.PersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @GetMapping
    List<PersonaDTO> listaSocios() {
        return personaService.getAllPersonas();
    }

    @GetMapping("/{id}")
    PersonaDTO personaById(@PathVariable("id") Long id){
        return this.personaService.getPersonaById(id);
    }

    @DeleteMapping("delete/{id}")
    void deletePersona(@PathVariable("id") Long id){
        this.personaService.deletePersonaById(id);
    }

    @PostMapping
    void creaPersona(@Valid @RequestBody PersonaDTO personaDTO){
        Persona p = this.personaService.transformadeDTO(personaDTO);
        this.personaService.savePersona(p);
    }

    @PutMapping("/update/{id}")
    public void actualizaPersona(@PathVariable ("id") Long id, @Valid @RequestBody PersonaDTO pDTO){
        this.personaService.actualizaPersona(id, pDTO);
    }

}
