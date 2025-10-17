package com.wis.main.model.user.dto.action_model;

import com.wis.main.model.user.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class UserGetActionModel {
    private int id;
    private Role role;
}
