package com.pietro.inventory_api.service;

import com.pietro.inventory_api.dto.CreateItemRequest;
import com.pietro.inventory_api.dto.ItemResponse;
import com.pietro.inventory_api.model.Item;
import com.pietro.inventory_api.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> listItems() {
        return repository.findAll();
    }

    public Item addItem(Item item) {
        return repository.save(item);
    }

    public ItemResponse save(CreateItemRequest request) {

        Item item = new Item();

        item.setName(request.getName());
        item.setDescription(request.getDescription());
        item.setQuantity(request.getQuantity());
        item.setRoom(request.getRoom());
        item.setCategory(request.getCategory());

        Item savedItem = repository.save(item);

        return toResponse(savedItem);
    }

    private ItemResponse toResponse(Item item) {

        ItemResponse response = new ItemResponse();

        response.setId(item.getId());
        response.setName(item.getName());
        response.setDescription(item.getDescription());
        response.setQuantity(item.getQuantity());
        response.setRoom(item.getRoom());
        response.setCategory(item.getCategory());

        return response;
    }
}