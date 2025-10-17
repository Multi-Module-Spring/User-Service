package com.wis.main.service.action.user;

import com.wis.main.model.user.dto.request.UserGetRequestDto;
import com.wis.main.model.user.dto.response.UserResponseDto;
import com.wis.main.executation.ActionService;

import java.util.List;

public interface GetUsersService extends ActionService<UserGetRequestDto, List<UserResponseDto>> {
}
