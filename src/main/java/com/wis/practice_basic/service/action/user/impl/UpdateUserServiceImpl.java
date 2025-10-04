package com.wis.practice_basic.service.action.user.impl;

import com.wis.configuration.Payload;
import com.wis.practice_basic.model.user.dto.action_model.UserGetActionModel;
import com.wis.practice_basic.model.user.dto.action_model.UserUpdateActionModel;
import com.wis.practice_basic.model.user.dto.request.UserUpdateRequestDto;
import com.wis.practice_basic.repository.user.UserRepository;
import com.wis.practice_basic.service.action.user.UpdateUserService;
import com.wis.executation.CoreActionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class UpdateUserServiceImpl extends CoreActionService<UserUpdateRequestDto, UserUpdateActionModel, Boolean> implements UpdateUserService {

    protected final UserRepository userRepository;
    @Override
    protected UserUpdateActionModel verify(Payload payload, UserUpdateRequestDto request, LocalDateTime now) {
        String email = stringUtil.nvl(request.getEmail());
        String password = stringUtil.nvl(request.getPassword());
        String phone = stringUtil.nvl(request.getPhone());
        int age = integerUtil.nvl(request.getAge());
        UserGetActionModel userGetActionModel = UserGetActionModel.builder()
                .id(request.getId())
                .build();
        userRepository.getUser(userGetActionModel);

        return UserUpdateActionModel.builder()
                        .id(request.getId())
                        .name(request.getName())
                        .email(email)
                        .phone(phone)
                        .password(password)
                        .age(age)
                        .build();
    }

    @Override
    protected Boolean innerExecute(Payload payload, UserUpdateActionModel actionModel, LocalDateTime now) {
        return userRepository.updateUser(actionModel,now);
    }
}
