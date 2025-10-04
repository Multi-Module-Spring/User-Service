package com.wis.practice_basic.service.action.user.impl;

import com.wis.configuration.Payload;
import com.wis.practice_basic.model.user.Role;
import com.wis.practice_basic.model.user.User;
import com.wis.practice_basic.model.user.dto.action_model.UserGetActionModel;
import com.wis.practice_basic.model.user.dto.request.UserGetRequestDto;
import com.wis.practice_basic.model.user.dto.response.UserResponseDto;
import com.wis.practice_basic.repository.user.UserRepository;
import com.wis.practice_basic.service.action.user.GetUsersByRoleService;
import com.wis.executation.CoreActionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetUsersByRoleServiceImpl extends CoreActionService<UserGetRequestDto, UserGetActionModel, List<UserResponseDto>> implements GetUsersByRoleService {
    protected final UserRepository userRepository;

    @Override
    protected UserGetActionModel verify(Payload payload, UserGetRequestDto request, LocalDateTime now) {
        Role role = request.getRole();
        return UserGetActionModel.builder()
                .role(role)
                .build();
    }

    @Override
    protected List<UserResponseDto> innerExecute(Payload payload, UserGetActionModel userGetActionModel, LocalDateTime now) {
        List<User> beforeMap = userRepository.getUsersByRole(userGetActionModel);

        return mapper.mapList(beforeMap, UserResponseDto.class);
    }


}
