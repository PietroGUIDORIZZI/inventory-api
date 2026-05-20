package com.pietro.inventory_api.repository;

import com.pietro.inventory_api.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
