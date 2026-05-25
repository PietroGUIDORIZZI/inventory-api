package com.pietro.inventory_api.controller;


import com.pietro.inventory_api.dto.CreateItemRequest;
import com.pietro.inventory_api.dto.ItemResponse;
import com.pietro.inventory_api.dto.UpdateItemRequest;
import com.pietro.inventory_api.model.Category;
import com.pietro.inventory_api.model.Item;
import com.pietro.inventory_api.model.Room;
import com.pietro.inventory_api.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service){
        this.service = service;
    }

    @PostMapping
    public ItemResponse create(@Valid @RequestBody CreateItemRequest request){
        return service.create(request);
    }
    @GetMapping("/search")
    public List<ItemResponse> findByNameContainingIgnoreCase(
            @RequestParam String name
    ){
        return service.findByNameContainingIgnoreCase(name);
    }

    @GetMapping("/{id}")
    public ItemResponse findById(
            @PathVariable Long id
    ){
        return service.findById(id);
    }

    @GetMapping
    public List<ItemResponse> findAll(){
        return service.findAll();
    }

    @PutMapping("/{id}")
    public ItemResponse update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateItemRequest request
            ){
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public List<ItemResponse> findByCategory(
            @PathVariable Category category
    ){
        return service.findByCategory(category);
    }

    @GetMapping("/room/{room}")
    public List<ItemResponse> findByRoom(
            @PathVariable Room room
    ){
        return service.findByRoom(room);
    }



}
