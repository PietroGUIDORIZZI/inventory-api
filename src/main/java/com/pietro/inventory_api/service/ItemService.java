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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }


    private ItemResponse toResponse(Item item) {

        ItemResponse response = new ItemResponse();

        response.setId(item.getId());

        response.setName(item.getName());

        response.setDescription(item.getDescription());

        response.setQuantity(item.getQuantity());

        response.setRoom(item.getRoom());

        response.setCategory(item.getCategory());

        response.setCreatedAt(item.getCreatedAt());

        response.setUpdatedAt(item.getUpdatedAt());

        return response;
    }

    public Page<ItemResponse> findAll(Pageable pageable) {

        return repository.findAll(pageable)
                .map(this::toResponse);

    }

    public ItemResponse findById(Long id) {

        Item item = repository.findById(id)
                .orElseThrow(()->
                        new ItemNotFoundException(id)

                );
        return toResponse(item);
    }

    public ItemResponse create(CreateItemRequest request){

        Item item = new Item();

        item.setName(request.getName());

        item.setDescription(resolveDescription(request.getDescription()));

        item.setQuantity(resolveQuantity(request.getQuantity()));

        item.setRoom(resolveRoom(request.getRoom()));

        item.setCategory(resolveCategory(request.getCategory()));

        Item savedItem = repository.save(item);

        return toResponse(savedItem);
    }

    public ItemResponse update(Long id, UpdateItemRequest request) {
        Item item = repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));

        item.setName(request.getName());

        item.setDescription(resolveDescription(request.getDescription()));

        item.setQuantity(resolveQuantity(request.getQuantity()));

        item.setRoom(resolveRoom(request.getRoom()));

        item.setCategory(resolveCategory(request.getCategory()));

        Item updatedItem = repository.save(item);

        return toResponse(updatedItem);


    }

    public void delete(Long id) {
        Item item = repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));

        repository.delete(item);

    }



    private String resolveDescription(String description) {
        return description == null || description.isBlank()
                ? "No description"
                : description;
    }

    private Integer resolveQuantity(Integer quantity) {
        return quantity == null ? 1 : quantity;
    }

    private Room resolveRoom(Room room) {
        return room == null
                ? Room.NOT_ALLOCATED
                : room;
    };

    private Category resolveCategory(Category category){
        return  category == null
                ? Category.NOT_CATEGORIZED
                : category;
    }

    public Page<ItemResponse> findByCategory(Category category, Pageable pageable){
        return repository.findByCategory(category, pageable)
                .map(this::toResponse);
    }

    public List<ItemResponse> findByRoom(Room room){
        return repository.findByRoom(room)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public Page<ItemResponse> findByNameContainingIgnoreCase(String name, Pageable pageable){
        return repository.findByNameContainingIgnoreCase(name, pageable)
                .map(this::toResponse);

    }

    public Page<ItemResponse> findByDescriptionContainingIgnoreCase(String description, Pageable pageable){
        return repository.findByDescriptionContainingIgnoreCase(description, pageable)
                .map(this::toResponse);
    }

    public List<ItemResponse> findByQuantityLessThan(Integer quantity){
        return repository.findByQuantityLessThan(quantity)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ItemResponse> findRunningOutItems(Integer quantity){
        return repository.findByQuantityLessThanEqual(3)
                .stream()
                .map(this::toResponse)
                .toList();
    }

}