package com.pietro.inventory_api.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "items")
@JsonPropertyOrder({
        "id",
        "name",
        "description",
        "quantity",
        "room",
        "category"
})
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private Room room;

    @Enumerated(EnumType.STRING)
    private Category category;

    public Item() {
    }

    @PrePersist
    public void applyDefaults() {

        if(description == null || description.isBlank()){
            description = "No description";
        }

        if(quantity == null || quantity < 0){
            quantity = 1;
        }

        if(room == null){
            room = Room.NOT_ALLOCATED;
        }

        if(category == null){
            category = Category.NOT_CATEGORIZED;
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description == null || description.isBlank()){
            this.description = "No description";
        }
        this.description = description.trim();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if(quantity == null || quantity < 0){
            throw new IllegalArgumentException("Invalid quantity");
        }


        this.quantity = quantity;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}