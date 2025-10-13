package com.wis.practice_basic.service.impl;

import com.wis.common.configuration.Payload;
import com.wis.practice_basic.model.user.dto.request.UserGetRequestDto;
import com.wis.practice_basic.model.user.dto.response.UserResponseDto;
import com.wis.practice_basic.model.user.dto.request.UserUpdateRequestDto;
import com.wis.practice_basic.service.action.user.GetUserService;
import com.wis.practice_basic.service.action.user.GetUsersByRoleService;
import com.wis.practice_basic.service.action.user.GetUsersService;
import com.wis.practice_basic.service.action.user.UpdateUserService;
import com.wis.practice_basic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final GetUserService getUserService;
    private final GetUsersService getUsersService;
    private final UpdateUserService updateUsersService;
    private final GetUsersByRoleService getUsersByRoleService;

    @Override
    public UserResponseDto getUser(Payload payload,UserGetRequestDto requestDto) {
        return getUserService.execute(payload,requestDto);
    }

    @Override
    public List<UserResponseDto> getUsers(Payload payload) {
        UserGetRequestDto requestDto = UserGetRequestDto.builder().build();
        return getUsersService.execute(payload,requestDto);
    }

    @Override
    public boolean updateUser(Payload payload, UserUpdateRequestDto userUpdateRequestDto) {
        return updateUsersService.execute(payload,userUpdateRequestDto);
    }

    @Override
    public List<UserResponseDto> getUsersByRole(Payload payload, UserGetRequestDto userUpdateRequestDto) {
        return getUsersByRoleService.execute(payload,userUpdateRequestDto);
    }
}
