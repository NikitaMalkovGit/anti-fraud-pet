package com.pet.antifraud.Controller;

import com.pet.antifraud.DTO.UserEntityDTO;
import com.pet.antifraud.Model.UserEntity;
import com.pet.antifraud.Service.UserEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller class for user authentication and registration.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserEntityService userEntityService;

    public AuthController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    /**
     * Registers a new user based on the provided UserEntity and returns the registered user's details.
     *
     * @param userEntity The UserEntity containing user details.
     * @return ResponseEntity containing the registered user's details.
     */
    @PostMapping("/user")
    public ResponseEntity<UserEntityDTO> registerUser(@RequestBody @Valid UserEntity userEntity) {
        return new ResponseEntity<>(userEntityService.registerUser(userEntity), HttpStatus.CREATED);
    }
}
