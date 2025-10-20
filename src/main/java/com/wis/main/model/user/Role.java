package com.wis.main.model.user;

import com.wis.i18n.Translate;
import com.wis.i18n.TranslateCommon;
import com.wis.i18n.exception.TranslateException;
import com.wis.main.exception.ServiceException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Comparator;
import java.util.List;

@Getter
public enum Role {
    USER,
    VISITOR,
    VIP,
    MANAGER,
    ADMIN;

    public static Role parse(String value) {
        if (value == null || value.isBlank()) {
            throw new TranslateException(HttpStatus.UNAUTHORIZED, TranslateCommon.MISSING_AUTHORITY);
        }

        try {
            return Role.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TranslateException(HttpStatus.UNAUTHORIZED, TranslateCommon.MISSING_AUTHORITY);
        }
    }

    public static Role findMaxRole(List<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            throw new TranslateException(HttpStatus.BAD_REQUEST, Translate.EMPTY_ROLE_LIST);
        }

        return roles.stream()
                .max(Comparator.comparingInt(Role::getPriority))
                .orElse(Role.USER);
    }
    private int getPriority() {
        return switch (this) {
            case VISITOR -> 0;
            case USER -> 1;
            case VIP -> 2;
            case MANAGER -> 3;
            case ADMIN -> 4;
        };
    }
}
