package com.pet.antifraud.DTO;

import com.pet.antifraud.Enum.UserRoles;
import com.pet.antifraud.Model.UserEntity;
import lombok.*;

@Data
@NoArgsConstructor
public class UserEntityDTO {

    private Long id;
    private String name;
    private String username;
    private UserRoles role;

    /**
     * Constructor to create a UserEntityDTO from a UserEntity instance.
     *
     * @param user The UserEntity instance to create the DTO from.
     */
    public UserEntityDTO(UserEntity user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.role = user.getRole();
    }
}
