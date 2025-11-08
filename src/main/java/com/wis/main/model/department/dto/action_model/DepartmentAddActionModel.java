package com.wis.main.model.department.dto.action_model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class DepartmentAddActionModel {
    private String code;
    private String parentCode;
}
