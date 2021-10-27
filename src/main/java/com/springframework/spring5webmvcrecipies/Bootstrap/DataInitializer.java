package com.springframework.spring5webmvcrecipies.Bootstrap;

import com.springframework.spring5webmvcrecipies.model.*;
import com.springframework.spring5webmvcrecipies.repositories.CategoryRepository;
import com.springframework.spring5webmvcrecipies.repositories.RecipeRepository;
import com.springframework.spring5webmvcrecipies.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent>, Runnable {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataInitializer(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        run();
    }

    @Override
    public void run() {
        // Perfect Guacamole
        Recipe guacamole = new Recipe();
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setServings(3);
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setSource("www.simplyrecipes.com");
        String directions = "Cut the avocado:\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "How to make guacamole - scoring avocado\n" +
                "Mash the avocado flesh:\n" +
                "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "How to make guacamole - smashing avocado with fork\n" +
                "Add remaining ingredients to taste:\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Serve immediately:\n" +
                "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n" +
                "\n" +
                "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.\n" +
                "\n" +
                "Refrigerate leftover guacamole up to 3 days.\n" +
                "\n" +
                "Note: Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole," +
                " add it just before serving.";
        guacamole.setDirections(directions);

        String notes = "Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "NUTRITION FACTS\n" +
                "(PER SERVING)\n" +
                "252\n" +
                "CALORIES\n" +
                "22g\n" +
                "FAT\n" +
                "16g\n" +
                "CARBS\n" +
                "3g\n" +
                "PROTEIN\n" +
                " Show Full Nutrition Label\n" +
                "Nutrition information is calculated using an ingredient database and should be considered an estimate. In cases where multiple ingredient alternatives are given, the first listed is calculated for nutrition. Garnishes and optional ingredients are not included.";

        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes(notes);
        guacamole.setNotes(guacamoleNotes);

        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setRating(4);

        // Set Category
        Optional<Category> mexican = categoryRepository.findCategoryByDescription("Mexican");
        Optional<Category> american = categoryRepository.findCategoryByDescription("American");
        Optional<Category> vegetarian = categoryRepository.findCategoryByDescription("Vegetarian");

        guacamole.getCategories().add(mexican.get());
        guacamole.getCategories().add(american.get());
        guacamole.getCategories().add(vegetarian.get());

        // New Category
        Category healthy = new Category();
        healthy.setDescription("Healthy");
        healthy.getRecipes().add(guacamole);
        categoryRepository.save(healthy);
        guacamole.getCategories().add(healthy);

        // New Category
        Category light = new Category();
        light.setDescription("Light");
        light.getRecipes().add(guacamole);
        categoryRepository.save(light);
        guacamole.getCategories().add(light);

        // Get UOMs
        UnitOfMeasure teaspoonUOM = unitOfMeasureRepository.findByUnitOfMeasure("Teaspoon").get();
        UnitOfMeasure tablespoonUOM = unitOfMeasureRepository.findByUnitOfMeasure("Tablespoon").get();
        UnitOfMeasure cupUOM = unitOfMeasureRepository.findByUnitOfMeasure("Cup").get();
        UnitOfMeasure pinchUOM = unitOfMeasureRepository.findByUnitOfMeasure("Pinch").get();
        UnitOfMeasure ounceUOM = unitOfMeasureRepository.findByUnitOfMeasure("Ounce").get();
        UnitOfMeasure eachUOM = unitOfMeasureRepository.findByUnitOfMeasure("Each").get();


        // Set Ingredients
        guacamole.addIngredient(new Ingredient("ripe avocados", BigDecimal.valueOf(2), eachUOM));
        guacamole.addIngredient(new Ingredient("fresh lime or lemon juice", BigDecimal.valueOf(1),
                tablespoonUOM));
        guacamole.addIngredient(new Ingredient("minced red onion or thinly sliced green onion",
                BigDecimal.valueOf(3),
                teaspoonUOM));
        guacamole.addIngredient(new Ingredient("serrano(or jalapeño) chilis, stems and seeds " +
                "removed, minced", BigDecimal.valueOf(1),
                eachUOM));
        guacamole.addIngredient(new Ingredient("salt, plus more to taste", BigDecimal.valueOf(0.25),
                teaspoonUOM));
        guacamole.addIngredient(new Ingredient("cilantro (leaves and tender stems),finely chopped"
                , BigDecimal.valueOf(2),
                tablespoonUOM));
        guacamole.addIngredient(new Ingredient("freshly ground black pepper", BigDecimal.valueOf(15),
                pinchUOM));
        guacamole.addIngredient(new Ingredient("ripe tomato, chopped (optional)",
                BigDecimal.valueOf(1),
                eachUOM));
        guacamole.addIngredient(new Ingredient("Red radish or jicama slices for garnish(optional)",
                BigDecimal.valueOf(1),
                eachUOM));
        guacamole.addIngredient(new Ingredient("Tortilla chips, to serve", BigDecimal.valueOf(1),
                eachUOM));

        // Save Recipe
        recipeRepository.save(guacamole);


        // Spicy Grilled Chicken Tacos
        Recipe chicken = new Recipe();
        chicken.setDescription("Spicy Grilled Chicken Tacos");
        chicken.setPrepTime(20);
        chicken.setCookTime(15);
        chicken.setServings(5);
        chicken.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        chicken.setSource("www.simplyrecipes.com");
        String directions2 = "Prepare a gas or charcoal grill for medium-high, direct heat\n" +
                "Make the marinade and coat the chicken:\n" +
                "In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "Grill the chicken:\n" +
                "Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "Warm the tortillas:\n" +
                "Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "Assemble the tacos:\n" +
                "Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.";
        chicken.setDirections(directions2);

        String notes2 = "We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "\n" +
                "FEATURED VIDEO\n" +
                "How to Make Spanish Rice\n" +
                "Today's tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!";

        Notes chickenNotes = new Notes();
        chickenNotes.setRecipeNotes(notes2);
        chicken.setNotes(chickenNotes);

        chicken.setDifficulty(Difficulty.KIND_OF_HARD);
        chicken.setRating(4);

        // Set Category
        guacamole.getCategories().add(mexican.get());
        guacamole.getCategories().add(american.get());
        guacamole.getCategories().add(vegetarian.get());

        // Set Ingredients
        chicken.getIngredients().add(new Ingredient(chicken, "ancho chili powder", BigDecimal.valueOf(2), tablespoonUOM));
        chicken.getIngredients().add(new Ingredient(chicken, "dried oregano", BigDecimal.valueOf(1),
                tablespoonUOM));
        chicken.getIngredients().add(new Ingredient(chicken, "dried cumin",
                BigDecimal.valueOf(1),
                teaspoonUOM));
        chicken.getIngredients().add(new Ingredient(chicken, "sugar", BigDecimal.valueOf(1),
                teaspoonUOM));
        chicken.getIngredients().add(new Ingredient(chicken, "salt", BigDecimal.valueOf(1),
                teaspoonUOM));
        chicken.getIngredients().add(new Ingredient(chicken, "clove garlic, finely chopped"
                , BigDecimal.valueOf(1),
                eachUOM));
        chicken.getIngredients().add(new Ingredient(chicken, "finely grated orange zest", BigDecimal.valueOf(1),
                tablespoonUOM));
        chicken.getIngredients().add(new Ingredient(chicken, "fresh-squeezed orange juice",
                BigDecimal.valueOf(2),
                tablespoonUOM));
        chicken.getIngredients().add(new Ingredient(chicken, "olive oil",
                BigDecimal.valueOf(2),
                tablespoonUOM));
        chicken.getIngredients().add(new Ingredient(chicken, "skinless, boneless chicken thighs (1 1/4 pounds)",
                BigDecimal.valueOf(5),
                eachUOM));

        // Save Recipe
        recipeRepository.save(chicken);
    }
}
