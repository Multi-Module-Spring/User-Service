package com.wis.practice_basic.service.action.user.impl;

import com.wis.common.configuration.Payload;
import com.wis.practice_basic.model.user.dto.action_model.UserGetActionModel;
import com.wis.practice_basic.model.user.dto.request.UserGetRequestDto;
import com.wis.practice_basic.model.user.dto.response.UserResponseDto;
import com.wis.practice_basic.repository.user.UserRepository;
import com.wis.practice_basic.service.action.user.GetUsersService;
import com.wis.common.executation.CoreActionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetUsersServiceImpl extends CoreActionService<UserGetRequestDto, UserGetActionModel, List<UserResponseDto>> implements GetUsersService {
    protected final UserRepository userRepository;

    @Override
    protected UserGetActionModel verify(Payload payload, UserGetRequestDto request, LocalDateTime now) {
        return UserGetActionModel.builder().build();
    }


    @Override
    protected List<UserResponseDto> innerExecute(Payload payload, UserGetActionModel actionModel, LocalDateTime now) {
        return userRepository.getUsers();
    }

}
