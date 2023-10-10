package com.pet.antifraud.Repository;

import com.pet.antifraud.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Find a user entity by username (case-insensitive).
     *
     * @param username The username of the user.
     * @return Optional containing the user entity if found, or empty if not found.
     */
    Optional<UserEntity> findUserEntityByUsernameIgnoreCase(String username);

    /**
     * Retrieve a list of all user entities.
     *
     * @return A list of all user entities.
     */
    List<UserEntity> findAll();
}
