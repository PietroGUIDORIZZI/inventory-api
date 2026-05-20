package com.pietro.inventory_api.service;

import com.pietro.inventory_api.model.Item;
import com.pietro.inventory_api.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository repository;

    public ItemService(ItemRepository repository){
        this.repository = repository;
    }

    public List<Item> listItems(){
        return repository.findAll();
    }

    public Item addItem(Item item){
        return repository.save(item);
    }
}
