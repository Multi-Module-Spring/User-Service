package com.wis.practice_basic.model.user.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    private int id;
    private String name;
}
