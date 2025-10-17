package com.wis.practice_basic.repository.department;

import com.wis.practice_basic.model.department.Department;
import com.wis.practice_basic.model.department.dto.action_model.DepartmentGetActionModel;


public interface DepartmentRepository {
    Department getDepartment(DepartmentGetActionModel departmentGetActionModel);
}
