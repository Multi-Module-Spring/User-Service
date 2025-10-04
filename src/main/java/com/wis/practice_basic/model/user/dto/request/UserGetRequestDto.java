package com.wis.practice_basic.model.user.dto.request;

import com.wis.practice_basic.model.user.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class UserGetRequestDto {
    private int id;
    private Role role;
}
