/*
 * Copyright (c)  Omnicuris Healthcare
 * All rights reserved.
 * Created by <akramcsm14@gmail.com>
 */

package com.omnicuris.ecom.controller;

import com.omnicuris.ecom.dto.Item;
import com.omnicuris.ecom.dto.Orders;
import com.omnicuris.ecom.repository.ItemRepository;
import com.omnicuris.ecom.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/features")
public class Features {

    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    ItemRepository itemRepository;

    /**
     * Get All items
     *
     * @return
     */
    @GetMapping(value = "/allItems", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllItems() {
        List<Item> allItems = itemRepository.findAll();
        return ResponseEntity.ok().body(allItems);
    }

    /**
     * Add new item (Single or Bulk)
     *
     * @param items
     * @return
     */
    @PostMapping(value = "/addNewItem", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addItem(@RequestBody @Valid Item[] items) {
        List<Item> itemList = new ArrayList<>();
        for (Item item : items) {
            item = itemRepository.save(item);
            itemList.add(item);
        }
        return ResponseEntity.ok().body(itemList);
    }

    /**
     * Add more stock in an item
     *
     * @param id
     * @param count
     * @return
     */
    @PostMapping(value = "/addItemStock", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addItem(@RequestParam int id, @RequestParam int count) {
        Item item = itemRepository.findById(id);
        item.setNoOfItemAvailable(item.getNoOfItemAvailable() + count);
        item = itemRepository.save(item);
        return ResponseEntity.ok().body(item);
    }

    /**
     * Change information of an item
     *
     * @param item
     * @return
     */
    @PutMapping(value = "/updateItem", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateItem(@RequestBody @Valid Item item) {
        if (item.getId() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Provide Existing Item Id");
        }
        item = itemRepository.save(item);
        return ResponseEntity.ok().body(item);
    }

    /**
     * List all orders placed
     *
     * @return
     */
    @GetMapping(value = "/allOrders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllOrders() {
        List<Orders> allOrders = ordersRepository.findAll();
        return ResponseEntity.ok().body(allOrders);
    }

    /**
     * See the orders based on email
     *
     * @param email
     * @return
     */
    @GetMapping(value = "/ordersByEmail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ordersByEmail(@RequestParam String email) {
        if (email == null || email.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please provide Email");
        }
        List<Orders> allOrders = ordersRepository.findByEmail(email);
        return ResponseEntity.ok().body(allOrders);
    }

    /**
     * Place orders, single or Bulk
     *
     * @param orders
     * @return
     */
    @PostMapping(value = "/placeOrder", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> placeOrder(@RequestBody @Valid Orders[] orders) {
        List<Orders> orderList = new ArrayList<>();
        for (Orders order : orders) {
            Item item = itemRepository.findById(order.getItemId());
            if (item.getNoOfItemAvailable() < order.getNoOfItem()) {
                return ResponseEntity.ok().body("{\"message\": \"Order Can not be placed as those many items not available\"," +
                        " \"noOfItemAvailable\": " + item.getNoOfItemAvailable() + "}");
            } else {
                item.setNoOfItemAvailable(item.getNoOfItemAvailable() - order.getNoOfItem());
                itemRepository.save(item);
            }
            order = ordersRepository.save(order);
            orderList.add(order);
        }
        return ResponseEntity.ok().body("{\"message\": \"Order Placed\"," +
                " \"orderDetails\": " + orderList + "}");
    }

    /**
     * Update a single order
     *
     * @param order
     * @return
     */
    @PutMapping(value = "/updateOrder", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateOrder(@RequestBody @Valid Orders order) {
        if (order.getOrderId() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Provide Existing Order Id");
        }
        Item item = itemRepository.findById(order.getItemId());
        int itemToBeUpdated;
        Orders existingOrder = ordersRepository.findByOrderId(order.getItemId());
        if (item.getNoOfItemAvailable() < order.getNoOfItem()) {
            return ResponseEntity.ok().body("{\"message\": \"Order Can not be placed as those many items not available\"," +
                    " \"noOfItemAvailable\": " + item.getNoOfItemAvailable() + "}");
        } else {
            if (existingOrder.getNoOfItem() > order.getNoOfItem()) {
                itemToBeUpdated = existingOrder.getNoOfItem() - order.getNoOfItem();
                item.setNoOfItemAvailable(item.getNoOfItemAvailable() + itemToBeUpdated);
            } else {
                itemToBeUpdated = order.getNoOfItem() - existingOrder.getNoOfItem();
                item.setNoOfItemAvailable(item.getNoOfItemAvailable() - itemToBeUpdated);
            }
            itemRepository.save(item);
        }
        order = ordersRepository.save(order);
        return ResponseEntity.ok().body("{\"message\": \"Order Updated\"," +
                " \"orderDetails\": " + order + "}");
    }
}
