package com.springframework.spring5webmvcrecipies.repositories;

import com.springframework.spring5webmvcrecipies.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
