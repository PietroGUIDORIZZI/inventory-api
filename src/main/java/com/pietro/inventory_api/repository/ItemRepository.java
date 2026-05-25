package com.pietro.inventory_api.repository;

import com.pietro.inventory_api.model.Category;
import com.pietro.inventory_api.model.Item;
import com.pietro.inventory_api.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByCategory(Category category);

    List<Item> findByRoom(Room room);

    List<Item> findByNameContainingIgnoreCase(String string);
}
