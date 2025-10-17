package com.wis.main.service.action.user;

import com.wis.main.model.user.dto.request.UserGetRequestDto;
import com.wis.main.model.user.dto.response.UserResponseDto;
import com.wis.main.executation.ActionService;

public interface GetUserService extends ActionService<UserGetRequestDto, UserResponseDto> {
}
