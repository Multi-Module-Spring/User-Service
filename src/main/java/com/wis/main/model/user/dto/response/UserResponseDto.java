package com.wis.main.model.user.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.wis.main.annotation.I18n;
import com.wis.main.model.department.Department;
import com.wis.main.model.department.dto.response.DepartmentWithRoleResponseDto;
import com.wis.main.model.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserResponseDto {
    private int id;
    private String name;
    private String email;
    private String phone;
    private int age;
    @I18n
    private String roleName = Role.USER.name();
    @I18n(args = {"roleName","name"})
    private String isActive;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime updatedAt;
    @I18n
    private String country;
    List<DepartmentWithRoleResponseDto> departmentList;
}
