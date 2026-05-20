package com.pietro.inventory_api.service;

import com.pietro.inventory_api.dto.CreateItemRequest;
import com.pietro.inventory_api.dto.ItemResponse;
import com.pietro.inventory_api.dto.UpdateItemRequest;
import com.pietro.inventory_api.exception.ItemNotFoundException;
import com.pietro.inventory_api.model.Category;
import com.pietro.inventory_api.model.Item;
import com.pietro.inventory_api.model.Room;
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




    public ItemResponse findById(Long id) {

        Item item = repository.findById(id)
                .orElseThrow(()->
                        new ItemNotFoundException(id)

                );
        return toResponse(item);
    }

    public List<ItemResponse> findAll() {

        return repository.findAll()

                .stream()
                .map(item -> toResponse(item))
                .toList();
    }

    public ItemResponse update(Long id, UpdateItemRequest request) {
        Item item = repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));

        item.setName(request.getName());

        item.setDescription(request.getDescription());

        item.setQuantity(request.getQuantity());

        item.setRoom(request.getRoom());

        item.setCategory(request.getCategory());

        Item updatedItem = repository.save(item);

        return toResponse(updatedItem);


    }

    public void delete(Long id) {
        Item item = repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));

        repository.delete(item);

    }

    public Item create(CreateItemRequest request){

        Item item = new Item();

        item.setName(request.getName());

        item.setDescription(
                request.getDescription() == null ||
                        request.getDescription().isBlank() ? "No description"
                        : request.getDescription()
        );

        item.setQuantity(
                request.getQuantity() == null
                        ? 1
                        : request.getQuantity()
        );

        item.setRoom(
                request.getRoom() == null
                        ? Room.NOT_ALLOCATED
                        : request.getRoom()
        );

        item.setCategory(
                request.getCategory() == null
                ? Category.NOT_CATEGORIZED
                        : request.getCategory()
        );

        return repository.save(item);
    }
}