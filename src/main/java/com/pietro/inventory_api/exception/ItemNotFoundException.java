package com.pietro.inventory_api.exception;

public class ItemNotFoundException extends Exception{

    public ItemNotFoundException(String message){
        super(message);
    }
}
