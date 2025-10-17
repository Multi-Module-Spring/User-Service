package com.wis.main.repository.department;

import com.wis.main.model.department.Department;
import com.wis.main.model.department.dto.action_model.DepartmentGetActionModel;

import java.util.List;


public interface DepartmentRepository {
    Department getDepartment(DepartmentGetActionModel departmentGetActionModel);

    List<Department> getDepartments(DepartmentGetActionModel departmentGetActionModel);
}
