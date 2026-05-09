package com.sainik.tacocloudmay.converter;

import com.sainik.tacocloudmay.models.IngredientRef;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<String, IngredientRef> {

    @Override
    public IngredientRef convert(String id) {
        return new IngredientRef(id);
    }
}
