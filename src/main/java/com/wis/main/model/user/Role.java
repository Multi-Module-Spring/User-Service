package com.wis.main.model.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wis.i18n.Translate;
import com.wis.i18n.TranslateCommon;
import com.wis.i18n.exception.TranslateException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public enum Role {
    VISITOR(1),
    USER(2),
    VIP(3),
    MANAGER(4),
    ADMIN(5);


    private int value;

    @JsonValue
    public Integer getValue() {
        return this.value;
    }

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

    @JsonCreator
    public static Role fromValue(Object value) {
        if (value == null) {
            throw new TranslateException(HttpStatus.UNAUTHORIZED, TranslateCommon.MISSING_AUTHORITY);
        }

        try {
            int intValue;
            if (value instanceof Number num) {
                intValue = num.intValue();
            } else {
                intValue = Integer.parseInt(value.toString());
            }

            for (Role role : Role.values()) {
                if (role.value == intValue) {
                    return role;
                }
            }
        } catch (Exception e) {
            throw new TranslateException(HttpStatus.UNAUTHORIZED, TranslateCommon.MISSING_AUTHORITY);
        }

        throw new TranslateException(HttpStatus.UNAUTHORIZED, TranslateCommon.MISSING_AUTHORITY);
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
