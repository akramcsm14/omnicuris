package com.omnicuris.ecom.dto;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "item")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "Please provide item name")
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "manufacturer")
    private String manufacturer;
    @NotNull
    @Min(value = 1, message = "You can not add item with 0 available items")
    @Column(name = "no_of_item_available")
    private int noOfItemAvailable;

    public Item() {
    }

    public Item(@NotEmpty(message = "Please provide item name") String itemName, String manufacturer,
                @NotNull @Min(value = 1, message = "You can not add item with 0 available items") int noOfItemAvailable) {
        this.itemName = itemName;
        this.manufacturer = manufacturer;
        this.noOfItemAvailable = noOfItemAvailable;
    }

    public Item(int id, @NotEmpty(message = "Please provide item name") String itemName, String manufacturer,
                @NotNull @Min(value = 1, message = "You can not add item with 0 available items") int noOfItemAvailable) {
        this.id = id;
        this.itemName = itemName;
        this.manufacturer = manufacturer;
        this.noOfItemAvailable = noOfItemAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getNoOfItemAvailable() {
        return noOfItemAvailable;
    }

    public void setNoOfItemAvailable(int noOfItemAvailable) {
        this.noOfItemAvailable = noOfItemAvailable;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"itemName\":\"" + itemName + '\"' +
                ", \"manufacturer\":\"" + manufacturer + '\"' +
                ", \"noOfItemAvailable\":" + noOfItemAvailable +
                "}";
    }
}