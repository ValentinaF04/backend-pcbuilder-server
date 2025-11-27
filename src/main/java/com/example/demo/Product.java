package com.example.demo; // Asegúrate que tu paquete sea el correcto (com.example.pcbuilder.model o similar)

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ¡IMPORTANTE: Usar Long (Objeto), no int (primitivo)!

    private String name;

    @Column(length = 1000) // Permite descripciones largas
    private String description;

    private Double price;

    private Integer stock;

    @Column(length = 2000) // ¡CRUCIAL! Las URLs de Amazon son largas y rompen la base de datos si no pones esto
    private String imageUrl;

    // Constructores
    public Product() {}

    public Product(String name, String description, Double price, Integer stock, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
    }

    // Getters y Setters OBLIGATORIOS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}