package com.mrojasdev.tsystemstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ProductDTO implements Serializable {

    private String title;
    private double price;
    private String category;
    private String brand;
    private String color;
    private double weight;
    private double volume;
    private int stock;

}
