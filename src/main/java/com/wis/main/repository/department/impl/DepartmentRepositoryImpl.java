package com.wis.main.repository.department.impl;

import com.wis.main.util.core_util.CoreRepository;
import com.wis.main.model.department.Department;
import com.wis.main.model.department.dto.action_model.DepartmentGetActionModel;
import com.wis.main.repository.department.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class DepartmentRepositoryImpl extends CoreRepository implements DepartmentRepository {
    @Override
    public Department getDepartment(DepartmentGetActionModel departmentGetActionModel) {
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
                Department.class,
                params
        );
    }

    @Override
    public List<Department> getDepartments(DepartmentGetActionModel departmentGetActionModel) {
        StringBuilder sql = new StringBuilder(
                """
                SELECT id, department_name,
                code, parent_code FROM department
                WHERE 1=1
                """
        );

        return dbPool.executeQuery(
                sql.toString(),
                Department.class,
                params
        );
    }
}
