package com.springframework.spring5webmvcrecipies.repositories;

import com.springframework.spring5webmvcrecipies.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
