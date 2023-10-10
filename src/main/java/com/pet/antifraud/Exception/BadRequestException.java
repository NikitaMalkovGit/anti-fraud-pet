package com.pet.antifraud.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception indicating a bad request.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid request")
public class BadRequestException extends RuntimeException {
}
