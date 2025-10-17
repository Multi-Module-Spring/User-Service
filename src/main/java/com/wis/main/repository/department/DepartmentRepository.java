package com.wis.main.repository.department;

import com.wis.main.model.department.Department;
import com.wis.main.model.department.dto.action_model.DepartmentGetActionModel;


public interface DepartmentRepository {
    Department getDepartment(DepartmentGetActionModel departmentGetActionModel);
}
