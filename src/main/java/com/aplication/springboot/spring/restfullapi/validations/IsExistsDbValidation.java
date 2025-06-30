package com.aplication.springboot.spring.restfullapi.validations;

import com.aplication.springboot.spring.restfullapi.services.IProductService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IsExistsDbValidation implements ConstraintValidator<IsExistsDb, String> {
    @Autowired
    private IProductService productService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return !productService.existsBySku(value);
    }
}
