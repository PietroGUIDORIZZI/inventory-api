package com.pietro.inventory_api.controller;


import com.pietro.inventory_api.dto.CreateItemRequest;
import com.pietro.inventory_api.dto.ItemResponse;
import com.pietro.inventory_api.dto.UpdateItemRequest;
import com.pietro.inventory_api.model.Category;
import com.pietro.inventory_api.model.Room;
import com.pietro.inventory_api.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Tag(
        name = "Items",
        description = "Invetory management endpoints"
)
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service){
        this.service = service;
    }

    //Criar
    @Operation(summary = "Create",
            description = "Creates a new inventory item")
    @PostMapping
    public ResponseEntity<ItemResponse> create(@Valid @RequestBody CreateItemRequest request){

        ItemResponse response = service.create(request);

        return ResponseEntity.status(201)
                .body(response);
    }

    //listar
    @Operation(summary = "List all items",
            description = "Returns a paginated list of all inventory items")
    @GetMapping
    public ResponseEntity<Page<ItemResponse>> findAll(
            Pageable pageable
    ){

        Page<ItemResponse> response =
                service.findAll(pageable);

        return ResponseEntity.ok(response);
    }

    //busca por id
    @Operation(summary = "find by id",
            description = "Returns a single item by its id")
    @GetMapping("/{id}")
    public ItemResponse findById(
            @PathVariable Long id
    ){
        return service.findById(id);
    }

    //buscar por nome
    @Operation(summary = "search by name",
            description = "Returns all items that contains the word")
    @GetMapping("/search")
    public ResponseEntity<Page<ItemResponse>> findByNameContainingIgnoreCase(
            @RequestParam String name,
            Pageable pageable
    ){
        Page<ItemResponse> response = service.findByNameContainingIgnoreCase(name, pageable);

        return ResponseEntity.ok(response);
    }

    //buscar por descrição
    @Operation(summary = "search by description")
    @GetMapping("/search/description")
    public ResponseEntity<Page<ItemResponse>>
    findByDescriptionContainingIgnoreCase(

            @RequestParam String description,
            Pageable pageable
    ){

        Page<ItemResponse> response =
                service.findByDescriptionContainingIgnoreCase(
                        description,
                        pageable
                );
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "list items with quantity less than x",
    description = "returns all items with quantity less that x")
    @GetMapping("/low-stock")
    public ResponseEntity<List<ItemResponse>> findByQuantityLessThan(
            @RequestParam Integer quantity
    ){

        List<ItemResponse> response = service.findByQuantityLessThan(quantity);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "list running out items",
    description = "list running out items(2 or less)")
    @GetMapping("/running-out")
    public ResponseEntity<List<ItemResponse>> findRunningOutItems(

    ){
        List<ItemResponse> response = service.findRunningOutItems(2);
        return ResponseEntity.ok(response);
    }

    //editar
    @Operation(summary = "Updates an item")
    @PutMapping("/{id}")
    public ItemResponse update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateItemRequest request
            ){
        return service.update(id, request);
    }

    //deletar
    @Operation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    //buscar por categoria
    @GetMapping("/category/{category}")
    public ResponseEntity<Page<ItemResponse>> findByCategory(
            @PathVariable Category category,
            Pageable pageable
    ){
        Page<ItemResponse> response =
                service.findByCategory(
                        category,
                        pageable
                );
        return ResponseEntity.ok(response);
    }

    //buscar por cômodo
    @GetMapping("/room/{room}")
    public List<ItemResponse> findByRoom(
            @PathVariable Room room
    ){
        return service.findByRoom(room);
    }



}
