package com.ead.course.exceptions;

/**
 * The Class NotFoundException
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 11/12/2024
 */
public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }
}
