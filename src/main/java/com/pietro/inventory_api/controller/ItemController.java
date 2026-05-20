package com.pietro.inventory_api.controller;


import com.pietro.inventory_api.model.Item;
import com.pietro.inventory_api.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service){
        this.service = service;
    }

    @GetMapping
    public List<Item> listItems() {
        return service.listItems();
    }

    @PostMapping
    public Item addItem(@RequestBody Item item){
        return service.addItem(item);
    }
}
