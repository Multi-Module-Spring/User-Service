package com.wis.main.repository.user;

import com.wis.main.model.user.User;
import com.wis.main.model.user.UserRole;
import com.wis.main.model.user.dto.action_model.UserGetActionModel;
import com.wis.main.model.user.dto.action_model.UserUpdateActionModel;
import com.wis.main.model.user.dto.response.UserResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository {
    Class<User> userTable = User.class; //Table
    Class<UserRole> userRoleTable = UserRole.class; //Table

    List<UserResponseDto> getUsers();

    User getUser(UserGetActionModel actionModel);

    Boolean updateUser(UserUpdateActionModel actionModel, LocalDateTime now);

    List<User> getUsersByRole(UserGetActionModel userGetActionModel);
}
