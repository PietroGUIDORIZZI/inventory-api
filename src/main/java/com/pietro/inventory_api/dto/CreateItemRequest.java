package com.pietro.inventory_api.dto;

import com.pietro.inventory_api.model.Category;
import com.pietro.inventory_api.model.Room;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class CreateItemRequest {

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    private Room room;

    private Category category;

    public CreateItemRequest(){

    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getDescription(){
        return description;
    }

    public Room getRoom() {
        return room;
    }

    public Category getCategory() {
        return category;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
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
