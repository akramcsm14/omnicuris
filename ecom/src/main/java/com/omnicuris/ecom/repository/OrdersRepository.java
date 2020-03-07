package com.omnicuris.ecom.repository;

import com.omnicuris.ecom.dto.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findByEmail(String email);

    Orders findByOrderId(int orderId);
}
