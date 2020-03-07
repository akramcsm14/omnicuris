package com.omnicuris.ecom.repository;

import com.omnicuris.ecom.dto.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    Item findById(int id);
}
