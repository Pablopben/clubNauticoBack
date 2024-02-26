package com.club.service;

import com.club.dto.PersonaDTO;
import com.club.dto.SalidaDTO;
import com.club.entities.Persona;
import com.club.entities.Salida;
import com.club.entities.Socio;
import com.club.repository.PersonaRepository;
import com.club.repository.SocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    SocioRepository socioRepository;

    public List<PersonaDTO> getAllPersonas(){
        List<PersonaDTO> res = new ArrayList<PersonaDTO>();
        for (Persona p: this.personaRepository.getAllPersonas()){
            res.add(transformaDTO(p));
        }
        return res;
    }

    public PersonaDTO getPersonaById(Long id){
        return transformaDTO(this.personaRepository.getPersonaById(id));
    }

    @Transactional
    public void savePersona(Persona p){
        this.personaRepository.save(p);
    }

    @Transactional
    public void deletePersonaById(Long id){
        this.personaRepository.deleteById(id);
    }

    @Transactional
    public void actualizaPersona(Long id, PersonaDTO personaDTO){
        Persona p = this.personaRepository.getPersonaById(id);
        p.setApellidos(personaDTO.getApellidos());
        p.setNombre(personaDTO.getNombre());
        p.setTelefono(personaDTO.getTelefono());
        p.setCorreo(personaDTO.getCorreo());
        this.savePersona(p);
    }

    public PersonaDTO transformaDTO (Persona p){
        PersonaDTO pDto = new PersonaDTO(p.getId(), p.getNombre(),p.getApellidos(), p.getCorreo(), p.getTelefono());
        if (this.socioRepository.getSocioById(p.getId()) == null){
            pDto.setSocio(false);
        } else {
            pDto.setSocio(true);
        }
        return pDto;
    }

    public Persona transformadeDTO (PersonaDTO personaDTO){

        if(personaDTO.getSocio()){
            return new Socio(personaDTO.getNombre(), personaDTO.getApellidos(), personaDTO.getCorreo(), personaDTO.getTelefono());
        }else{
            return new Persona(personaDTO.getNombre(), personaDTO.getApellidos(), personaDTO.getCorreo(), personaDTO.getTelefono());
        }
    }

}
