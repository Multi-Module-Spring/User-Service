package com.wis.practice_basic.repository.user.impl;

import com.wis.practice_basic.model.user.Role;
import com.wis.practice_basic.model.user.UserRole;
import com.wis.practice_basic.model.user.dto.response.UserResponseDto;
import com.wis.util.core_util.CoreRepository;
import com.wis.util.core_util.number.impl.IntegerUtil;
import com.wis.util.core_util.string.StringUtil;
import com.wis.util.core_util.database.SQLBuilder;
import com.wis.practice_basic.model.user.User;
import com.wis.practice_basic.model.user.dto.action_model.UserGetActionModel;
import com.wis.practice_basic.model.user.dto.action_model.UserUpdateActionModel;
import com.wis.practice_basic.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl extends CoreRepository implements UserRepository {
    @Override
    public List<UserResponseDto> getUsers() {
        SQLBuilder sqlBuilder = SQLBuilder.build()
                .select(userTable,"u")
                .select(userRoleTable,"r")
                .from(userTable)
                .join(userRoleTable).on("u.id = r.user_id")
                .orderBy(userRoleTable,UserRole.Fields.role);

        return dbPool.executeQuery(
                sqlBuilder.getSql(),
                UserResponseDto.class,
                sqlBuilder.getParams()
        );
    }

    @Override
    public List<User> getUsersByRole(UserGetActionModel userGetActionModel) {
       SQLBuilder sqlBuilder = SQLBuilder.build()
               .select(userTable,"u")
               .from(userTable)
               .join(userRoleTable).on("u.id = user_role.user_id")
               .where(userRoleTable,UserRole.Fields.role,userGetActionModel.getRole().name());

        return dbPool.executeQuery(
               sqlBuilder.getSql(),
                userTable,
               sqlBuilder.getParams()
        );
    }

    @Override
    public User getUser(UserGetActionModel actionModel) {
        SQLBuilder sqlBuilder = SQLBuilder.build()
                .select(userTable,"u")
                .select(userRoleTable,"r")
                .from(userTable)
                .join(userRoleTable).on("u.id = r.user_id")
                .where(userTable,
                        User.Fields.id, actionModel.getId()
                );
        if(actionModel.getRole() != null) {
            sqlBuilder.and(userRoleTable,UserRole.Fields.role, actionModel.getRole().name());
        }
        return dbPool.executeQueryUnique(
                sqlBuilder.getSql(),
                userTable,
                sqlBuilder.getParams());
    }

    @Override
    public Boolean updateUser(UserUpdateActionModel actionModel, LocalDateTime now) {
        if(!actionModel.getName().equals(StringUtil.BLANK)) {
            values().put(User.Fields.name, actionModel.getName());
        }
        if(!actionModel.getPassword().equals(StringUtil.BLANK)) {
            values().put(User.Fields.password, actionModel.getPassword());
        }

        if(!actionModel.getPhone().equals(StringUtil.BLANK)) {
            values().put(User.Fields.phone, actionModel.getPhone());
        }
        if(!actionModel.getEmail().equals(StringUtil.BLANK)) {
            values().put(User.Fields.email, actionModel.getEmail());
        }
        if(actionModel.getAge() != IntegerUtil.ZERO) {
            values().put(User.Fields.age, actionModel.getAge());
        }

        values().put(User.Fields.updatedAt, now);

        SQLBuilder sqlBuilder = SQLBuilder.build()
                .update(userTable,values())
                .where(User.Fields.id, actionModel.getId());

        return dbPool.executeUpdate(
                sqlBuilder.getSql(),
                userTable,
                sqlBuilder.getParams()) >=1;

    }
}
