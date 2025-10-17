package com.wis.practice_basic.model.department.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class DepartmentGetRequestDto {
    private int id;
    private String code;
}
