package com.wis.main.controller;

import com.wis.main.util.core_util.CoreAPI;
import com.wis.main.model.user.Role;
import com.wis.main.configuration.Payload;
import com.wis.main.model.user.dto.request.UserGetRequestDto;
import com.wis.main.model.user.dto.response.UserResponseDto;
import com.wis.main.model.user.dto.request.UserUpdateRequestDto;
import com.wis.main.service.UserService;
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
