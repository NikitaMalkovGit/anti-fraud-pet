package com.pet.antifraud.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception indicating that a similar entity already exists.
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "A similar entity already exists!")
public class EntityAlreadyExistsException extends RuntimeException {
}
