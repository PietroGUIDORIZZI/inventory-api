package com.pietro.inventory_api.repository;

import com.pietro.inventory_api.model.Category;
import com.pietro.inventory_api.model.Item;
import com.pietro.inventory_api.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findByCategory(Category category, Pageable pageable);

    List<Item> findByRoom(Room room);

    Page<Item> findByNameContainingIgnoreCase(String string, Pageable pageable);

    Page<Item> findByDescriptionContainingIgnoreCase(String string, Pageable pageable);

    List<Item> findByQuantityLessThan(Integer quantity);

    List<Item> findByQuantityLessThanEqual(Integer quantity);

    boolean existsByNameIgnoreCase(String name);

}
