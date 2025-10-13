package com.wis.practice_basic.service.action.user;

import com.wis.practice_basic.model.user.dto.request.UserGetRequestDto;
import com.wis.practice_basic.model.user.dto.response.UserResponseDto;
import com.wis.common.executation.ActionService;

import java.util.List;

public interface GetUsersByRoleService extends ActionService<UserGetRequestDto, List<UserResponseDto>> {
}
