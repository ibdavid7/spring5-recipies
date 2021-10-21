package com.springframework.spring5webmvcrecipies.repositories;

import com.springframework.spring5webmvcrecipies.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
