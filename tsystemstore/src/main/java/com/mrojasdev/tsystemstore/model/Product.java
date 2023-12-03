package com.mrojasdev.tsystemstore.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Products")
@Getter
@Setter
@NoArgsConstructor
public class Product {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;

   @Column(name = "title")
   private String title;

   @Column(name = "price")
   private double price;

   @Column(name = "category")
   private String category;

   @Column(name = "brand")
   private String brand;

   @Column(name = "color")
   private String color;

   @Column(name = "weight")
   private double weight;

   @Column(name = "volume")
   private double volume;

   @Column(name = "stock")
   private int stock;

   @OneToMany(mappedBy = "product")
   private List<OrderProduct> orderProducts;



}
