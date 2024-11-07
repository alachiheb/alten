package com.alten.management.product.model;

import com.alten.management.product.util.InventoryStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String code;

    private String name;

    private String description;

    private String image;

    private String category;

    private Long price;

    private Long quantity;

    private String internalReference;

    private Integer shellId;

    private InventoryStatus inventoryStatus;

    private long rating;

    private Date createdAt;

    private Date updatedAt;




}
