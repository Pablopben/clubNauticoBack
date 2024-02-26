package com.club.service;

import com.club.dto.SalidaDTO;
import com.club.entities.Barco;
import com.club.entities.Persona;
import com.club.entities.Salida;
import com.club.repository.BarcoRepository;
import com.club.repository.PersonaRepository;
import com.club.repository.SalidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalidaService {

    @Autowired
    SalidaRepository salidaRepository;

    @Autowired
    BarcoRepository barcoRepository;

    @Autowired
    PersonaRepository personaRepository;

    public List<SalidaDTO> getAllSalidas(){
        List<SalidaDTO> res = new ArrayList<SalidaDTO>();

        for(Salida s:this.salidaRepository.getAllSalidas()){
            res.add(this.transformaDTO(s));
        }
        return res;
    }

    public SalidaDTO getSalidaById(Long id){
        return(transformaDTO(this.salidaRepository.getSalidaById(id)));
    }

    @Transactional
    public void saveSalida(Salida s){
        this.salidaRepository.save(s);
    }

    @Transactional
    public void deleteSalidaById(Long id){ this.salidaRepository.deleteById(id);}

    @Transactional
    public void actualizaSalida(Long id, SalidaDTO salidaDTO){
        Salida s = this.salidaRepository.getSalidaById(id);
        s.setHora(salidaDTO.getHora());
        s.setFecha(salidaDTO.getFecha());
        s.setDestino(salidaDTO.getDestino());
        saveSalida(s);
    }

    public SalidaDTO transformaDTO(Salida s){
        return new SalidaDTO(s.getId(), s.getDestino(), s.getFecha(), s.getHora(), s.getPatron().getId(),s.getBarco().getId(),
                s.getPatron().getNombre() + " " + s.getPatron().getApellidos(), s.getBarco().getMatricula(), s.getBarco().getNombre());
    }

    public Salida transformadeDTO(SalidaDTO salidaDTO){
        Barco b = this.barcoRepository.getBarcoById(salidaDTO.getIdBarco());
        Persona p = this.personaRepository.getPersonaById(salidaDTO.getIdPatron());
        return new Salida(salidaDTO.getFecha(), salidaDTO.getHora(), salidaDTO.getDestino(), b,p);
    }

}
