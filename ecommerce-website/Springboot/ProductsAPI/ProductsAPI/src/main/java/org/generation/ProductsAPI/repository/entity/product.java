package org.generation.ProductsAPI.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.MapKey;

@Entity // This tells Hibernate to make a table out of this class
public class product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private String brand;

    private Double price;

    private String image_link;

    public Integer getId()
    {
        return id;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand( String brand )
    {
        this.brand = brand;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice( Double price )
    {
        this.price = price;
    }


    public String getImageLink()
    {
        return image_link;
    }

    public void setImageLink( String image_link )
    {
        this.image_link = image_link;
    }

//    @Override
//    public String toString()
//    {
//        return "Product{" + "id=" + id + ", name='" + name + '\'' + ", brand='" + brand + '\'' + ", price='$" + price + '\'' + ", image_link='"
//                + image_link + '\'' + '}';
//    }

}
