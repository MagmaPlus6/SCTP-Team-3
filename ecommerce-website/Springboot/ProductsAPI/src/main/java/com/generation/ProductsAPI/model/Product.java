package com.generation.ProductsAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
@Table(name="product")
public class Product {

    @Id
    @SequenceGenerator(name = "yourSequenceGenerator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "yourSequenceGenerator")
    private Integer id;

    @Column
//    @Column(length = 100) restricts VARCHAR in mySQL to 100
    @NotBlank(message = "Please enter the product name.")
    private String name;

    @Column
//    @Column(length = 100) restricts VARCHAR in mySQL to 100
    @NotBlank(message = "Please enter the product brand.")
    private String brand;

    @Column
    @NotNull(message = "Please enter the product price.")
    @DecimalMin(value = "0", inclusive = false, message = "Price must be more than 0.")
    @Digits(integer = 10, fraction = 2, message = "Price must be in 2 decimal place.")
    private BigDecimal price;
    // @DecimalMin @Digits do not support Double

    @Column
 //    @Column(length = 100) restricts VARCHAR in mySQL to 100
    @NotBlank(message = "Please enter the image name.")
    private String image;

    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
//    private Set<OrderDetail> orderDetails = new HashSet<>();
    private List<OrderDetail> orderDetails;


    public Product(String name, String brand, BigDecimal price, String image){
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
