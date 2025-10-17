package com.wis.main.service;

import com.wis.main.configuration.Payload;
import com.wis.main.model.user.dto.request.UserGetRequestDto;
import com.wis.main.model.user.dto.response.UserResponseDto;
import com.wis.main.model.user.dto.request.UserUpdateRequestDto;

import java.util.List;

public interface UserService {
    UserResponseDto getUser(Payload payload,UserGetRequestDto requestDto);

    List<UserResponseDto> getUsers(Payload payload);

    boolean updateUser(Payload payload, UserUpdateRequestDto userUpdateRequestDto);

    List<UserResponseDto> getUsersByRole(Payload payload, UserGetRequestDto userUpdateRequestDto);
}
