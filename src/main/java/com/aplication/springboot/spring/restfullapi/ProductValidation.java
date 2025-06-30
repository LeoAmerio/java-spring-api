package com.aplication.springboot.spring.restfullapi;

import com.aplication.springboot.spring.restfullapi.entities.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "El campo nombre es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", null, "El campo description es obligatorio");

        if(product.getDescription() == null || product.getDescription().isBlank()) {
            errors.rejectValue("description", null, "NotEmpty.product.description");
        }

        if (product.getPrice() == null) {
            errors.rejectValue("price", null, "Min.product.price");
        } else if (product.getPrice() < 500) {
            errors.rejectValue("price", null, "Min.product.price");
        }

    }
}
