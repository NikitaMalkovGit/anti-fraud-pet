package com.pet.antifraud.Service;


import com.pet.antifraud.DTO.OperationDTO;
import com.pet.antifraud.DTO.RoleDTO;
import com.pet.antifraud.DTO.UserEntityDTO;
import com.pet.antifraud.Enum.UserRoles;
import com.pet.antifraud.Exception.BadRequestException;
import com.pet.antifraud.Exception.EntityAlreadyExistsException;
import com.pet.antifraud.Exception.NotFoundException;
import com.pet.antifraud.Model.UserEntity;
import com.pet.antifraud.Repository.UserEntityRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntityService(UserEntityRepository userEntityRepository, PasswordEncoder passwordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user and returns a DTO representation.
     *
     * @param user The UserEntity representing the new user.
     * @return UserEntityDTO representing the registered user.
     * @throws EntityAlreadyExistsException if a user with the same username already exists.
     */
    public UserEntityDTO registerUser(UserEntity user) {
        if (userEntityRepository.findUserEntityByUsernameIgnoreCase(user.getUsername()).isPresent()) {
            throw new EntityAlreadyExistsException();
        }
        setUserRole(user);
        setUserPassword(user);
        userEntityRepository.save(user);
        UserEntity savedUser = userEntityRepository.findUserEntityByUsernameIgnoreCase(user.getUsername()).get();
        return new UserEntityDTO(savedUser);
    }

    /**
     * Sets the initial role of the user.
     * If no users exist, the role is set to ADMINISTRATOR, otherwise to MERCHANT.
     *
     * @param user The UserEntity for which the role needs to be set.
     */
    public void setUserRole(UserEntity user) {
        if (userEntityRepository.findAll().size() == 0) {
            user.setRole(UserRoles.ADMINISTRATOR);
            user.setAccountNonLocked(true);
        } else {
            user.setRole(UserRoles.MERCHANT);
        }
    }

    /**
     * Sets the encoded password for the user.
     *
     * @param user The UserEntity for which the password needs to be set.
     */
    public void setUserPassword(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    /**
     * Deletes a user by username.
     *
     * @param username The username of the user to be deleted.
     * @return A map containing username and status information after deletion.
     * @throws NotFoundException if the specified user is not found.
     */
    public Map<String, String> deleteUser(String username) {
        Optional<UserEntity> user = userEntityRepository.findUserEntityByUsernameIgnoreCase(username);
        if (user.isEmpty()) {
            throw new NotFoundException();
        }
        userEntityRepository.delete(user.get());
        return Map.of("username", username, "status", "Deleted successfully!");
    }public UserEntityDTO setAccountRole(RoleDTO role) {
        UserEntity user = userEntityRepository.findUserEntityByUsernameIgnoreCase(role.getUsername())
                .orElseThrow(NotFoundException::new);
        UserRoles providedRole = getProvidedRole(role.getRole());
        if (user.getRole() == providedRole) {
            throw new EntityAlreadyExistsException();
        }
        user.setRole(providedRole);
        userEntityRepository.save(user);
        return new UserEntityDTO(user);
    }

    /**
     * Retrieves a valid UserRoles enum value based on the provided role string.
     *
     * @param role Role string to be converted to UserRoles.
     * @return Valid UserRoles enum value.
     * @throws NotFoundException if the provided role is not valid.
     */
    public UserRoles getProvidedRole(String role) throws NotFoundException {
        UserRoles providedRole;
        try {
            providedRole = UserRoles.valueOf(role);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException();
        }
        if (providedRole == UserRoles.MERCHANT || providedRole == UserRoles.SUPPORT) {
            return providedRole;
        }
        throw new NotFoundException();
    }

    /**
     * Sets account access status (lock/unlock) for a user and returns a status map.
     *
     * @param operation OperationDTO containing the username and operation (LOCK/UNLOCK).
     * @return A map containing the status of the operation.
     * @throws NotFoundException if the specified user is not found.
     * @throws BadRequestException if the operation is not valid.
     */
    public Map<String, String> setAccountAccess(OperationDTO operation) {
        UserEntity user = userEntityRepository.findUserEntityByUsernameIgnoreCase(operation.getUsername())
                .orElseThrow(NotFoundException::new);
        if (user.getRole() == UserRoles.ADMINISTRATOR) {
            throw new BadRequestException();
        }
        userEntityRepository.save(lockOrUnlockUser(operation.getOperation(), user));
        return Map.of("status", String.format(
                "User %s %sed!", operation.getUsername(), operation.getOperation().toLowerCase(Locale.ROOT)));
    }

    /**
     * Locks or unlocks a user's account based on the provided operation.
     *
     * @param operation Operation to be performed (LOCK/UNLOCK).
     * @param user UserEntity for which the operation needs to be performed.
     * @return Updated UserEntity after the operation.
     * @throws BadRequestException if the provided operation is not valid.
     */
    public UserEntity lockOrUnlockUser(String operation, UserEntity user) {
        if (operation.equals("LOCK")) {
            user.setAccountNonLocked(false);
        } else if (operation.equals("UNLOCK")) {
            user.setAccountNonLocked(true);
        } else {
            throw new BadRequestException();
        }
        return user;
    }

    /**
     * Retrieves a list of all users as UserEntityDTO objects.
     *
     * @return List of UserEntityDTO representing all users.
     */
    public List<UserEntityDTO> showAllUsers() {
        return userEntityRepository.findAll().stream().map(UserEntityDTO::new).collect(Collectors.toList());
    }
}