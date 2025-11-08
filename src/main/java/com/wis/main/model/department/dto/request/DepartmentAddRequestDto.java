package com.wis.main.model.department.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class DepartmentAddRequestDto {
    private String code;
    private String parentCode;
}
