package com.springframework.spring5webmvcrecipies.repositories;

import com.springframework.spring5webmvcrecipies.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findCategoryByDescription(String description);
}
