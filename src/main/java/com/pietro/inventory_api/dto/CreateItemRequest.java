package com.pietro.inventory_api.dto;

import com.pietro.inventory_api.model.Category;
import com.pietro.inventory_api.model.Room;
import jakarta.validation.constraints.NotBlank;

public class CreateItemRequest {

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    private Integer quantity;

    private Room room;

    private Category category;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

    public void setRoom(Room room){
        this.room = room;
    }

    public void setCategory(Category category){
        this.category = category;
    }
}
