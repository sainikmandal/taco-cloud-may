package com.sainik.tacocloudmay.models;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Table("Taco_Ingredients")
@Data
public class IngredientRef {
    private final String ingredientId;
}
