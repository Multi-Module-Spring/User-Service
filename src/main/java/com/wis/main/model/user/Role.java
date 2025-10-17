package com.wis.main.model.user;

import com.wis.main.exception.ServiceException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Role {
    USER,
    VISITOR,
    VIP,
    MANAGER,
    ADMIN;

    public static Role parse(String value) {
        if (value == null || value.isBlank()) {
            throw ServiceException.of(HttpStatus.UNAUTHORIZED,"MISSING_AUTHORITY");
        }

        try {
            return Role.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw ServiceException.of(HttpStatus.UNAUTHORIZED,"MISSING_AUTHORITY");
        }
    }
}
