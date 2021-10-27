package com.springframework.spring5webmvcrecipies.controllers;

import com.springframework.spring5webmvcrecipies.model.Category;
import com.springframework.spring5webmvcrecipies.model.UnitOfMeasure;
import com.springframework.spring5webmvcrecipies.repositories.CategoryRepository;
import com.springframework.spring5webmvcrecipies.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndex() {
        System.out.println("Some message to say...12345");

        Optional<Category> category = categoryRepository.findCategoryByDescription("American");

        category.ifPresent(cat -> {
            System.out.println("Category ID: " + cat.getId() + "\n" + "Category Name: " + cat.getDescription());
        });

        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByUnitOfMeasure("Ounce");
        unitOfMeasure.ifPresent(uom -> {
            System.out.println("Category ID: " + uom.getId() + "\n" + "Category Name: " + uom.getUnitOfMeasure());
        });

        return "index";
    }

}
