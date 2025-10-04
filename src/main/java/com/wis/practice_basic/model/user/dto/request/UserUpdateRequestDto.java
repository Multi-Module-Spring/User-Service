package com.wis.practice_basic.model.user.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDto {
    private int id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private int age;
}
