package com.wis.main.repository.department;

import com.wis.main.model.department.Department;
import com.wis.main.model.department.dto.action_model.DepartmentAddActionModel;
import com.wis.main.model.department.dto.action_model.DepartmentGetActionModel;
import com.wis.main.model.department.dto.action_model.DepartmentGetParentByCodeActionModel;

import java.util.List;


public interface DepartmentRepository {
    Department getDepartment(DepartmentGetActionModel departmentGetActionModel);

    List<Department> getDepartments(DepartmentGetActionModel departmentGetActionModel);

    Department addDepartment(DepartmentAddActionModel departmentAddActionModel);

    Department getParentDepartmentByCode(DepartmentGetParentByCodeActionModel departmentGetParentByCodeActionModel);
}
