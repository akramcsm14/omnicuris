/*
 * Copyright (c)  Omnicuris Healthcare
 * All rights reserved.
 * Created by <akramcsm14@gmail.com>
 */
package com.omnicuris.ecom.dto;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "orders")
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;
    @NotEmpty(message = "Please provide user email")
    @Column(name = "email")
    private String email;
    @Column(name = "item_id")
    private int itemId;
    @NotNull
    @Min(value = 1, message = "Please add at least one item to place an order")
    @Column(name = "no_of_item")
    private int noOfItem;

    public Orders() {
    }

    public Orders(int orderId, @NotEmpty(message = "Please provide user email") String email, int itemId,
                  @NotNull @Min(value = 1, message = "Please add at least one item to place an order") int noOfItem) {
        this.orderId = orderId;
        this.email = email;
        this.itemId = itemId;
        this.noOfItem = noOfItem;
    }

    public Orders(@NotEmpty(message = "Please provide user email") String email, int itemId,
                  @NotNull @Min(value = 1, message = "Please add at least one item to place an order") int noOfItem) {
        this.email = email;
        this.itemId = itemId;
        this.noOfItem = noOfItem;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getNoOfItem() {
        return noOfItem;
    }

    public void setNoOfItem(int noOfItem) {
        this.noOfItem = noOfItem;
    }

    @Override
    public String toString() {
        return "{" +
                "\"orderId\":" + orderId +
                ", \"email\":\"" + email + '\"' +
                ", \"itemId\":" + itemId +
                ", \"noOfItem\":" + noOfItem +
                "}";
    }
}
