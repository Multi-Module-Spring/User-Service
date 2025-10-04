package com.wis.practice_basic.service.action.user;

import com.wis.practice_basic.model.user.dto.request.UserGetRequestDto;
import com.wis.practice_basic.model.user.dto.response.UserResponseDto;
import com.wis.executation.ActionService;

public interface GetUserService extends ActionService<UserGetRequestDto, UserResponseDto> {
}
