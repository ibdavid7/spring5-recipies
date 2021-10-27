package com.springframework.spring5webmvcrecipies.controllers;

import com.springframework.spring5webmvcrecipies.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/recipes")
@Controller
public class RecipeController {
    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String recipeIndex(Model model) {
        model.addAttribute("recipes", recipeRepository.findAll());
        System.out.println("controller works");
        return "/recipes/index";
    }
}
