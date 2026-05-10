package com.sainik.tacocloudmay.config;

import com.sainik.tacocloudmay.models.Ingredient;
import com.sainik.tacocloudmay.models.Ingredient.Type;
import com.sainik.tacocloudmay.repository.IngredientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(IngredientRepository ingredientRepo) {
        return args -> {
            ingredientRepo.deleteAll();
            ingredientRepo.save(new Ingredient("FLTO", "Flour Tortilla",  Type.WRAP));
            ingredientRepo.save(new Ingredient("COTO", "Corn Tortilla",   Type.WRAP));
            ingredientRepo.save(new Ingredient("GRBF", "Ground Beef",     Type.PROTEIN));
            ingredientRepo.save(new Ingredient("CARN", "Carnitas",        Type.PROTEIN));
            ingredientRepo.save(new Ingredient("TMTO", "Diced Tomatoes",  Type.VEGGIES));
            ingredientRepo.save(new Ingredient("LETC", "Lettuce",         Type.VEGGIES));
            ingredientRepo.save(new Ingredient("CHED", "Cheddar",         Type.CHEESE));
            ingredientRepo.save(new Ingredient("JACK", "Monterrey Jack",  Type.CHEESE));
            ingredientRepo.save(new Ingredient("SLSA", "Salsa",           Type.SAUCE));
            ingredientRepo.save(new Ingredient("SRCR", "Sour Cream",      Type.SAUCE));
        };
    }
}
