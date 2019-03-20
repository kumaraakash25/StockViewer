package com.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private BigDecimal price;

    private Date updatedTime;

    public Stock(String name, BigDecimal price, Date updatedTime) {
        this.name = name;
        this.price = price;
        this.updatedTime = updatedTime;
    }
}
