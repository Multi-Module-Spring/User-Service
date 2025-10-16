package com.wis.practice_basic.service.action.user.impl;

import com.wis.i18n.Translate;
import com.wis.i18n.exception.TranslateException;
import com.wis.practice_basic.model.user.Role;
import com.wis.practice_basic.model.user.User;
import com.wis.common.configuration.Payload;
import com.wis.practice_basic.model.user.dto.action_model.UserGetActionModel;
import com.wis.practice_basic.model.user.dto.request.UserGetRequestDto;
import com.wis.practice_basic.model.user.dto.response.UserResponseDto;
import com.wis.common.exception.ServiceException;
import com.wis.practice_basic.repository.user.UserRepository;
import com.wis.practice_basic.service.action.user.GetUserService;
import com.wis.common.executation.CoreActionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetUserServiceImpl extends CoreActionService<UserGetRequestDto, UserGetActionModel, UserResponseDto> implements GetUserService {

    protected final UserRepository userRepository;

    @Override
    protected UserGetActionModel verify(Payload payload, UserGetRequestDto request, LocalDateTime now) {
        int id = integerUtil.nvl(request.getId());
        Role role = request.getRole();
        return UserGetActionModel.builder()
                .id(id)
                .role(role)
                .build();
    }

    @Override
    protected UserResponseDto innerExecute(Payload payload, UserGetActionModel actionModel, LocalDateTime now) {
        log.info("Getting user by id: {}", actionModel.getId());
        User user = userRepository.getUser(actionModel);
        if (user == null) {
            throw new TranslateException(HttpStatus.BAD_REQUEST, Translate.USER_NOT_FOUND, List.of(actionModel.getId()));
        }
        return mapper.mapTo(user, UserResponseDto.class);
    }
}
