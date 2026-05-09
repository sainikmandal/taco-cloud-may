package com.sainik.tacocloudmay.repository;

import com.sainik.tacocloudmay.models.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
