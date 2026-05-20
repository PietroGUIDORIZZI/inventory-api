package com.pietro.inventory_api.dto;

import com.pietro.inventory_api.model.Category;
import com.pietro.inventory_api.model.Room;

public class UpdateItemRequest {

    private String name;
    private String description;
    private Integer quantity;
    private Room room;
    private Category category;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Room getRoom() {
        return room;
    }

    public Category getCategory() {
        return category;
    }
}
