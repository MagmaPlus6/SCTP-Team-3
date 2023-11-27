package com.generation.ProductsAPI.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
@Table(name="product")
public class Product {

    @Id
    @SequenceGenerator(name="yourSequenceGenerator", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="yourSequenceGenerator")
    private Integer id;

    @Column
    private String name;

    @Column
    private String brand;

    @Column
    private Double price;

    @Column
    private String image;

    public Product(String name, String brand, Double price, String image){
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.image = image;
    }


//    @Override
//    public String toString()
//    {
//        return "Product{" + "id=" + id + ", name='" + name + '\'' + ", brand='" + brand + '\'' + ", price='$" + price + '\'' + ", image='"
//                + image + '\'' + '}';
//    }



}
