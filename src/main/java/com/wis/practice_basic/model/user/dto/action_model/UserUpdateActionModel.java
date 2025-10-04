package com.wis.practice_basic.model.user.dto.action_model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class UserUpdateActionModel {
    private int id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private int age;
}
