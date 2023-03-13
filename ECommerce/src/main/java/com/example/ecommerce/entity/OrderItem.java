package com.example.ecommerce.entity;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Table(name = "tbl_order_item")
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer quantity;
    private Double price;
    private double gst;
    private Product product;

}
