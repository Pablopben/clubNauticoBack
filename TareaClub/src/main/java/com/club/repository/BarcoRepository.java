package com.club.repository;

import com.club.entities.Barco;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarcoRepository extends CrudRepository<Barco, String> {

    @Query("select b from Barco b")
    List<Barco> getAllBarcos();

    Barco getBarcoById(Long id);

}
