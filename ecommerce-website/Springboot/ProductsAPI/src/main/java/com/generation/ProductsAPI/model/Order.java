package com.generation.ProductsAPI.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
@Table(name="orders")
public class Order {

    @Id
    @Column(name = "order_id")
    @SequenceGenerator(name = "orderSequenceGenerator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderSequenceGenerator")
    private Integer id;

    @Column(length = 100) // restricts VARCHAR in mySQL to 100
    @NotBlank(message = "Please enter the username.")
    private String username;

    @Column
    @NotNull(message = "Please enter subtotal.")
    @DecimalMin(value = "0", inclusive = false, message = "Subtotal must be more than 0.")
    @Digits(integer = 10, fraction = 2, message = "Subtotal must be in 2 decimal place.")
    private BigDecimal subtotal;

    @Column(name = "shipping_fee")
    @NotNull(message = "Please enter shipping fee.")
    @DecimalMin(value = "0", inclusive = false, message = "Shipping fee must be more than 0.")
    @Digits(integer = 10, fraction = 2, message = "Shipping fee must be in 2 decimal place.")
    private BigDecimal shippingFee;

    @Column
    @NotNull(message = "Please enter total.")
    @DecimalMin(value = "0", inclusive = false, message = "Total must be more than 0.")
    @Digits(integer = 10, fraction = 2, message = "Total must be in 2 decimal place.")
    private BigDecimal total;

//    @Column(name = "order_date", updatable = false)
//    @PastOrPresent
//    private LocalDateTime order_date;

    @Column(name = "order_date", updatable = false)
    @CreationTimestamp(source = SourceType.DB)
    private Instant orderDate;

//    Can consider adding this
//    @Column(updatable = false)
//    @UpdateTimestamp(source = SourceType.DB)
//    private Instant lastUpdatedOn;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    private Set<OrderDetail> orderDetails = new HashSet<>();
    private List<OrderDetail> orderDetails;

    public Order(String username, BigDecimal subtotal, BigDecimal shippingFee, BigDecimal total, Instant orderDate) {
        this.username = username;
        this.subtotal = subtotal;
        this.shippingFee = shippingFee;
        this.total = total;
        this.orderDate= orderDate;
    }
}
