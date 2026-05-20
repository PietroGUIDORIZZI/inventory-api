package com.pietro.inventory_api.controller;


import com.pietro.inventory_api.dto.ItemResponse;
import com.pietro.inventory_api.dto.UpdateItemRequest;
import com.pietro.inventory_api.model.Item;
import com.pietro.inventory_api.service.ItemService;
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
    public Item addItem(@RequestBody Item item){
        return service.addItem(item);
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
            @RequestBody UpdateItemRequest request
            ){
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }


}
