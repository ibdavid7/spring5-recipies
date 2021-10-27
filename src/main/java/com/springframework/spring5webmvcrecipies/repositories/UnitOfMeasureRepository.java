package com.springframework.spring5webmvcrecipies.repositories;

import com.springframework.spring5webmvcrecipies.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    Optional<UnitOfMeasure> findByUnitOfMeasure(String uom);
}

