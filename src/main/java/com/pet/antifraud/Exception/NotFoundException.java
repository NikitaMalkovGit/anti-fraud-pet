package com.pet.antifraud.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception indicating that the requested entity could not be found.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find requested Entity")
public class NotFoundException extends RuntimeException {
}
