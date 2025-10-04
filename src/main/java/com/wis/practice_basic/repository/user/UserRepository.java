package com.wis.practice_basic.repository.user;

import com.wis.practice_basic.model.user.User;
import com.wis.practice_basic.model.user.dto.action_model.UserGetActionModel;
import com.wis.practice_basic.model.user.dto.action_model.UserUpdateActionModel;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository {
    Class<User> userTable = User.class; //Table

    List<User> getUsers();

    User getUser(UserGetActionModel actionModel);

    Boolean updateUser(UserUpdateActionModel actionModel, LocalDateTime now);

    List<User> getUsersByRole(UserGetActionModel userGetActionModel);
}
