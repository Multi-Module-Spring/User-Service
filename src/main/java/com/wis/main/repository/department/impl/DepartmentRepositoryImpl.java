package com.wis.main.repository.department.impl;

import com.wis.main.model.department.dto.action_model.AddDepartmentActionModel;
import com.wis.main.model.department.dto.action_model.GetParentByCodeDepartmentActionModel;
import com.wis.main.model.department.dto.response.AddDepartmentResponseDto;
import com.wis.main.model.department.dto.response.GetDepartmentResponseDto;
import com.wis.main.model.department.dto.response.GetParentByCodeDepartmentResponseDto;
import com.wis.main.util.core_util.CoreRepository;
import com.wis.main.model.department.Department;
import com.wis.main.model.department.dto.action_model.GetDepartmentActionModel;
import com.wis.main.repository.department.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class DepartmentRepositoryImpl extends CoreRepository implements DepartmentRepository {
    @Override
    public GetDepartmentResponseDto getDepartment(GetDepartmentActionModel departmentGetActionModel) {
        StringBuilder sql = new StringBuilder(
               """
               SELECT id, department_name,
               code, parent_code FROM department
               WHERE 1=1
               """
        );

        //id
        if (departmentGetActionModel.getId() != 0) {
            params.add(departmentGetActionModel.getId());
            sql.append(" AND id = $").append(params.size());
        }

        //code
        if(stringUtil.isNotEmpty(departmentGetActionModel.getCode())){
            params.add(departmentGetActionModel.getCode());
            sql.append(" AND code = $").append(params.size());
        }

        return dbPool.executeQueryUnique(
                sql.toString(),
                GetDepartmentResponseDto.class,
                params
        );
    }

    @Override
    public List<Department> getDepartments(GetDepartmentActionModel departmentGetActionModel) {
        StringBuilder sql = new StringBuilder(
                """
                SELECT id, department_name,
                code, parent_code,created_at FROM department
                WHERE 1=1
                """
        );

        return dbPool.executeQuery(
                sql.toString(),
                Department.class,
                params
        );
    }

    @Override
    public AddDepartmentResponseDto addDepartment(AddDepartmentActionModel departmentAddActionModel, LocalDateTime localDateTime) {
        String sql = """
                INSERT INTO department (department_name, code, parent_code, created_at)
                VALUES ($1, $2, $3,$4)
                RETURNING id, department_name,
                code, parent_code, created_at
                """;
        params.add(departmentAddActionModel.getCode());
        params.add(departmentAddActionModel.getCode());
        params.add(departmentAddActionModel.getParentCode());
        params.add(localDateTime);

        return dbPool.executeQueryUnique(
                sql,
                AddDepartmentResponseDto.class,
                params
        );
    }

    @Override
    public GetParentByCodeDepartmentResponseDto getParentDepartmentByCode(GetParentByCodeDepartmentActionModel departmentGetParentByCodeActionModel) {
        String sql = """
                SELECT id, department_name, code, parent_code
                FROM department
                WHERE code =
                (
                    SELECT parent_code FROM department
                        WHERE code = $1
                )
                """;
        params.add(departmentGetParentByCodeActionModel.getCode());

        return dbPool.executeQueryUnique(
                sql,
                GetParentByCodeDepartmentResponseDto.class,
                params
        );
    }
}
