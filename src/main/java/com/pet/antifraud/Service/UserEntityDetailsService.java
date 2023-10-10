package com.pet.antifraud.Service;


import com.pet.antifraud.Exception.NotFoundException;
import com.pet.antifraud.Model.UserEntity;
import com.pet.antifraud.Model.UserEntityDetails;
import com.pet.antifraud.Repository.UserEntityRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEntityDetailsService implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;

    public UserEntityDetailsService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    /**
     * Load user details by username for authentication.
     *
     * @param username The username of the user to load details for.
     * @return UserDetails representing the loaded user.
     * @throws UsernameNotFoundException if the user with the given username is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find user entity by username, and create UserEntityDetails from it.
        Optional<UserEntity> userEntity = userEntityRepository.findUserEntityByUsernameIgnoreCase(username);
        return userEntity.map(UserEntityDetails::new)
                .orElseThrow(NotFoundException::new);
    }
}
