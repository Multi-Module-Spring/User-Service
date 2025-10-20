package com.wis.main.service.action.user.impl;

import com.wis.main.configuration.Payload;
import com.wis.main.model.department.dto.response.DepartmentWithRoleResponseDto;
import com.wis.main.model.user.Role;
import com.wis.main.model.user.dto.action_model.UserGetActionModel;
import com.wis.main.model.user.dto.request.UserGetRequestDto;
import com.wis.main.model.user.dto.response.UserResponseDto;
import com.wis.main.repository.user.UserRepository;
import com.wis.main.service.action.user.GetUsersService;
import com.wis.main.executation.CoreActionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        List<UserResponseDto> users = userRepository.getUsers();
        users = users.stream().peek(user -> {
            user.setRoleName(
                    Role.findMaxRole(
                            user.getDepartmentList().stream()
                                    .map(DepartmentWithRoleResponseDto::getRole)
                                    .toList()
                    ).name()
            );
        }).collect(Collectors.toList());
        return users;
    }

}
