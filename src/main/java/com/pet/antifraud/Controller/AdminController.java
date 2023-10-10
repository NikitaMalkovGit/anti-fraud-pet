package com.pet.antifraud.Controller;

import com.pet.antifraud.DTO.OperationDTO;
import com.pet.antifraud.DTO.RoleDTO;
import com.pet.antifraud.DTO.UserEntityDTO;
import com.pet.antifraud.Service.UserEntityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller class for managing user-related operations by an administrator.
 */
@RestController
@RequestMapping("/api/auth")
public class AdminController {

    private final UserEntityService userEntityService;

    public AdminController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    /**
     * Retrieves a list of DTO representations of all users.
     *
     * @return List of DTO representations of users.
     */
    @GetMapping("/list")
    public List<UserEntityDTO> showAllUsers() {
        return userEntityService.showAllUsers();
    }

    /**
     * Sets the role of a user based on the provided RoleDTO and returns the updated user.
     *
     * @param roleDTO The RoleDTO containing the username and new role.
     * @return Updated DTO representation of the user.
     */
    @PutMapping("/role")
    public UserEntityDTO setAccountRole(@RequestBody RoleDTO roleDTO) {
        return userEntityService.setAccountRole(roleDTO);
    }

    /**
     * Sets the account access (lock or unlock) for a user based on the provided OperationDTO and returns a status message.
     *
     * @param operation The OperationDTO containing the username and access operation.
     * @return A map containing the status message.
     */
    @PutMapping("/access")
    public Map<String, String> setAccountAccess(@RequestBody OperationDTO operation) {
        return userEntityService.setAccountAccess(operation);
    }

    /**
     * Deletes a user by the given username and returns a ResponseEntity with a status map.
     *
     * @param username The username of the user to be deleted.
     * @return ResponseEntity with a map containing the username and status message.
     */
    @DeleteMapping("/user/{username}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable String username) {
        return ResponseEntity.ok(userEntityService.deleteUser(username));
    }
}
