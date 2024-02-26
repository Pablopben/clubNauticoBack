package com.club.repository;

import com.club.entities.Salida;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalidaRepository extends CrudRepository<Salida, Long> {

    @Query("select s from Salida s")
    List<Salida> getAllSalidas();

    Salida getSalidaById(Long id);

}
