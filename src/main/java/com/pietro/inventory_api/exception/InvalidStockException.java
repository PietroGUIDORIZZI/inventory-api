package com.pietro.inventory_api.exception;

public class InvalidStockException extends RuntimeException{
    public InvalidStockException(){
        super("Invalid Stock quantity. It cannot be greater than 1000." );
    }
}
