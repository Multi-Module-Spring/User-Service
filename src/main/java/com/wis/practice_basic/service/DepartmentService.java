package com.wis.practice_basic.service;

import com.wis.common.configuration.Payload;
import com.wis.practice_basic.model.department.Department;

import java.util.List;

public interface DepartmentService {
    Department getDepartmentById(Payload payload,int id);
    List<Department> getAllDepartments(Payload payload);
    Department addDepartment(Payload payload,Department department);
    Department updateDepartment(Payload payload,Department department);
    Department deleteDepartment(Payload payload,int id);
    Department getDepartmentByCode(Payload payload,String code);
    Department getParentDepartmentByCode(Payload payload,String code);
}
