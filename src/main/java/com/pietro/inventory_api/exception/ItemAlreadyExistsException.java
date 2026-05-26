package com.pietro.inventory_api.exception;

public class ItemAlreadyExistsException extends RuntimeException {
    public ItemAlreadyExistsException(String name) {
        super("Item named " + name + " already exists.");
    }
}
