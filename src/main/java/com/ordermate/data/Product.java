package com.ordermate.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Created by edwin on 19/05/17.
 */
public class Product {

    private Long id;
    private String name;
    private BigDecimal price;
    private String imageName="default.jpg";

    public Product() {
        price=new BigDecimal(0);
    }

    public Product(String name,BigDecimal price,String imageName) {
        this.name=name;
        this.price=price.setScale(2, RoundingMode.CEILING);
        this.imageName=imageName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getImageName() {
        return imageName;
    }

    public boolean equals(Object o) {
        if(this==o) {
            return true;
        }
        if(o==null) {
            return false;
        }
        if(!(o instanceof Product)) {
            return false;
        }
        Product p=(Product)o;
        return Objects.equals(name,p.getName())
                && Objects.equals(price,p.getPrice())
                && Objects.equals(imageName,p.getImageName());
    }

}
