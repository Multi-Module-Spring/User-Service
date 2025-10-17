package com.wis.practice_basic.repository.user.impl;

import com.wis.practice_basic.model.user.User;
import com.wis.practice_basic.model.user.dto.action_model.UserGetActionModel;
import com.wis.practice_basic.model.user.dto.action_model.UserUpdateActionModel;
import com.wis.practice_basic.model.user.dto.response.UserResponseDto;
import com.wis.practice_basic.repository.user.UserRepository;
import com.wis.common.util.core_util.CoreRepository;
import com.wis.common.util.core_util.number.impl.IntegerUtil;
import com.wis.common.util.core_util.string.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl extends CoreRepository implements UserRepository {

    @Override
    public List<UserResponseDto> getUsers() {
        String sql = """
            SELECT
                u.id AS id,
                u.name AS name,
                u.email AS email,
                u.phone AS phone,
                u.age AS age,
                r.role AS role
            FROM "user" u
            JOIN user_role r ON u.id = r.user_id
            ORDER BY r.role
        """;

        return dbPool.executeQuery(sql, UserResponseDto.class, Collections.emptyMap());
    }

    @Override
    public List<User> getUsersByRole(UserGetActionModel model) {
        String sql = """
            SELECT
                u.*
            FROM "user" u
            JOIN user_role r ON u.id = r.user_id
            WHERE r.role = $1
        """;
        params.add(model.getRole().name());

        return dbPool.executeQuery(sql, User.class, params.size());
    }

    @Override
    public User getUser(UserGetActionModel model) {
        StringBuilder sql = new StringBuilder("""
            SELECT
                u.*,
                r.role AS role
            FROM "user" u
            JOIN user_role r ON u.id = r.user_id
            WHERE u.id = $1
        """);
        params.add(model.getId());

        if (model.getRole() != null) {
            params.add(model.getRole().name());
            sql.append(" AND r.role = $").append(params.size());
        }

        return dbPool.executeQueryUnique(sql.toString(), User.class, params);
    }

    @Override
    public Boolean updateUser(UserUpdateActionModel model, LocalDateTime now) {
        StringBuilder sql = new StringBuilder("UPDATE \"user\" SET ");
        List<String> updates = new ArrayList<>();

        if (!StringUtil.BLANK.equals(model.getName())) {
            params.add(model.getName());
            updates.add(
                    "name = $" + params.size()
            );

        }

        if (!StringUtil.BLANK.equals(model.getPassword())) {
            params.add(model.getPassword());
            updates.add(
                    "password = $" + params.size()
            );
        }

        if (!StringUtil.BLANK.equals(model.getPhone())) {
            params.add(model.getPhone());
            updates.add(
                    "phone = $" + params.size()
            );
        }

        if (!StringUtil.BLANK.equals(model.getEmail())) {
            params.add(model.getEmail());
            updates.add(
                    "email = $" + params.size()
                    );
        }

        if (model.getAge() != IntegerUtil.ZERO) {
            params.add(model.getAge());
            updates.add(
                    "age = $" + params.size()
            );
        }

        params.add(now);
        updates.add(
                "updated_at = $" + params.size()
        );
        sql.append(String.join(", ", updates));
        params.add(model.getId());
        sql.append(
                " WHERE id = $"
        ).append(params.size());

        return dbPool.executeUpdate(sql.toString(),User.class, params) >= 1;
    }
}
