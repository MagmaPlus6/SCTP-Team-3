package com.generation.ProductsAPI.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
@Table(name="order_detail")
public class OrderDetail {

    @Id
    @Column(name = "order_detail_id")
    @SequenceGenerator(name = "orderDetailSequenceGenerator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderDetailSequenceGenerator")
    private Integer id;

    @JsonIgnore
//    By default, @ManyToOne association uses FetchType.EAGER for fetch type, but it is bad for performance
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(length = 100) // restricts VARCHAR in mySQL to 100
    @NotBlank(message = "Please enter the valid size.")
    private String size;

    @Column
    @Positive(message = "Min quantity must be 1")
    private Integer quantity;

    @Column
    @NotNull(message = "Please enter the product price.")
    @DecimalMin(value = "0", inclusive = false, message = "Price must be more than 0.")
    @Digits(integer = 10, fraction = 2, message = "Price must be in 2 decimal place.")
    private BigDecimal price;

    public OrderDetail(String size, Integer quantity, BigDecimal price) {
        this.size = size;
        this.quantity = quantity;
        this.price = price;
    }
}
