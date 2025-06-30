package com.aplication.springboot.spring.restfullapi.entities;

import com.aplication.springboot.spring.restfullapi.validations.IsExistsDb;
import com.aplication.springboot.spring.restfullapi.validations.IsRequired;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{NotBlank.product.name}")
    @Size(min = 3, max = 100)
    private String name;

    @NotNull(message = "{NotNull.product.price}")
    @Min(value = 500, message = "{Min.product.price}")
    @Max(1000000)
    private Integer price;

    @IsRequired
    private String description;

    @IsRequired
    @IsExistsDb
    private String sku;
}