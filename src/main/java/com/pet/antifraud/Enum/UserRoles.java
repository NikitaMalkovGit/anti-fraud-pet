package com.pet.antifraud.Enum;

/**
 * Enum representing different user roles in the system.
 */
public enum UserRoles {

    ADMINISTRATOR("ROLE_ADMINISTRATOR"),
    MERCHANT("ROLE_MERCHANT"),
    SUPPORT("ROLE_SUPPORT");

    private final String role;

    UserRoles(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return this.role;
    }
}
