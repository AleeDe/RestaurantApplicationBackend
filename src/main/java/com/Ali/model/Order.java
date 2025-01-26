package com.Ali.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "order")
public class Order {
    @Id
    private ObjectId id;

    @DBRef(lazy = true)
    private User customer;

    @DBRef(lazy = true)
    private Restaurant restaurant;

    private Long totalAmount;
    private String orderStatus;
    private Date createdAt;

    @DBRef(lazy = true)
    private Address deliveryAddress;

    @DBRef(lazy = true)
    private List<Orderitem> items;

//    private Payment payment;

    private int totalItem;
    private Long totalPrice;


}
