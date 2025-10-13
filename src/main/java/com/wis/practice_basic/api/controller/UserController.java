package com.wis.practice_basic.api.controller;

import com.wis.common.util.core_util.CoreAPI;
import com.wis.practice_basic.model.user.Role;
import com.wis.common.configuration.Payload;
import com.wis.practice_basic.model.user.dto.request.UserGetRequestDto;
import com.wis.practice_basic.model.user.dto.response.UserResponseDto;
import com.wis.practice_basic.model.user.dto.request.UserUpdateRequestDto;
import com.wis.practice_basic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController extends CoreAPI {

    private final UserService userService;

    @GetMapping("")
    public List<UserResponseDto> getUsers() {
        Payload payload = payload(false);

        return userService.getUsers(payload);
    }

    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Integer id) {
        Payload payload = payload(false);
        return userService.getUser(
                payload,
                UserGetRequestDto.builder()
                        .id(id)
                        .build()
        );
    }

    @GetMapping("/{id}/{role}")
    public UserResponseDto getUserWithRole(@PathVariable Integer id, @PathVariable String role) {
        Payload payload = payload(false);
        return userService.getUser(
                payload,
                UserGetRequestDto.builder()
                        .id(id)
                        .role(Role.parse(role))
                        .build()
        );
    }

    @GetMapping("by-role/{role}")
    public List<UserResponseDto> getUserByRole(@PathVariable String role) {
        Payload payload = payload(false);
        return userService.getUsersByRole(
                payload,
                UserGetRequestDto.builder()
                        .role(Role.parse(role))
                        .build()
        );
    }

    @PutMapping("")
    public boolean updateUser(@RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        Payload payload = payload(false);
        return userService.updateUser(payload, userUpdateRequestDto);
    }
}
