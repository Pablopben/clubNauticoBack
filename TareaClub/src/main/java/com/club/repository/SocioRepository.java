package com.club.repository;

import com.club.entities.Socio;
import org.springframework.data.repository.CrudRepository;

public interface SocioRepository extends CrudRepository<Socio, Long> {

    Socio getSocioById(Long id);


}
