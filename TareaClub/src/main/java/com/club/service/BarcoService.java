package com.club.service;

import com.club.dto.BarcoDTO;
import com.club.dto.SalidaDTO;
import com.club.entities.Barco;
import com.club.entities.Salida;
import com.club.entities.Socio;
import com.club.repository.BarcoRepository;
import com.club.repository.SocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BarcoService {

    @Autowired
    BarcoRepository repositorioBarco;

    @Autowired
    SocioRepository socioRepository;

    public List<BarcoDTO> getAllBarcos(){
        List<BarcoDTO> res = new ArrayList<>();
        for (Barco b:this.repositorioBarco.getAllBarcos()){
            res.add(this.transformaDTO(b));
        }

        return res;
    }

    public Barco getBarcoById(Long id){
        return this.repositorioBarco.getBarcoById(id);
    }

    @Transactional
    public void saveBarco(Barco b){
        this.repositorioBarco.save(b);
    }

    @Transactional
    public void deleteBarcoById(String id){
        this.repositorioBarco.deleteById(id);
    }

    public BarcoDTO transformaDTO(Barco b){
        return new BarcoDTO(b.getId(), b.getMatricula(), b.getNombre(), b.getNumeroAmarre(), b.getCuota(), b.getDueño().getId(), b.getDueño().getNombre()+ " " + b.getDueño().getApellidos());
    }

    public Barco transformadeDTO(BarcoDTO bDTo){
        Socio s = this.socioRepository.getSocioById(bDTo.getIdDueño());
        return new Barco(bDTo.getMatricula(), bDTo.getNombre(), bDTo.getNumeroAmarre(), bDTo.getCuota(), s);
    }

    @Transactional
    public void actualizaBarco(Long id, BarcoDTO barcoDTO){
        Barco b = this.repositorioBarco.getBarcoById(id);
        b.setMatricula(barcoDTO.getMatricula());
        b.setCuota(barcoDTO.getCuota());
        b.setNombre(barcoDTO.getNombre());
        b.setNumeroAmarre(barcoDTO.getNumeroAmarre());
        if(barcoDTO.getIdDueño() !=null){
            b.setDueño(this.socioRepository.getSocioById(barcoDTO.getIdDueño()));
        }
        saveBarco(b);
    }




}
