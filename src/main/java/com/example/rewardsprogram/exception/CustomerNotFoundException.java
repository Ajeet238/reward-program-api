package com.example.rewardsprogram.exception;

/**
 * Exception thrown when the requested customer does not exist.
 */
public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long customerId) {
        super("Customer with id " + customerId + " not found");
    }
}
