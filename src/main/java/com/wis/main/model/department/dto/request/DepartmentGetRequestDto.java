package com.wis.main.model.department.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class DepartmentGetRequestDto {
    private int id;
    private String code;
    private String name;
}
