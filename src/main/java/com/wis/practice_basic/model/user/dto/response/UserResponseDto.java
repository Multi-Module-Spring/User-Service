package com.wis.practice_basic.model.user.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.wis.annotation.I18n;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

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
    private String role;
    @I18n(args = {"role","name"})
    private String isActive;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime updatedAt;
}
