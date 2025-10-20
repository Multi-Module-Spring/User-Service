package com.wis.main.repository.user.impl;

import com.wis.main.model.user.User;
import com.wis.main.model.user.dto.action_model.UserGetActionModel;
import com.wis.main.model.user.dto.action_model.UserUpdateActionModel;
import com.wis.main.model.user.dto.response.UserResponseDto;
import com.wis.main.repository.user.UserRepository;
import com.wis.main.util.core_util.CoreRepository;
import com.wis.main.util.core_util.number.impl.IntegerUtil;
import com.wis.main.util.core_util.string.StringUtil;
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
                u.id as id,
                u.name AS name,
                u.email AS email,
                u.phone AS phone,
                u.age AS age,
                u.is_active AS is_active,
                u.updated_at AS updated_at,
                u.country AS country,
                to_jsonb(json_agg(
                    json_build_object(
                        'id', d.id,
                        'code', d.code,
                        'departmentName', d.department_name,
                        'role', r.role,
                        'parentCode', d.parent_code
                    )
                )) AS department_list
            FROM "user" u
            JOIN user_role r ON u.id = r.user_id
            JOIN department d ON r.department_code = d.code
            GROUP BY
              u.id,
              u.name,
              u.email,
              u.phone,
              u.age,
              u.is_active,
              u.updated_at,
              u.country
            ORDER BY u.name;
        """;

        return dbPool.executeQuery(sql, UserResponseDto.class, params);
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
