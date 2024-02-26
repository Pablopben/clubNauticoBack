package com.club.repository;

import com.club.entities.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Long> {

    @Query("select p from Persona p")
    List<Persona> getAllPersonas();

    Persona getPersonaById(Long id);

}
