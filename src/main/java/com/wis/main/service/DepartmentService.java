package com.wis.main.service;

import com.wis.main.configuration.Payload;
import com.wis.main.model.department.Department;

import java.util.List;

public interface DepartmentService {
    Department getDepartmentById(Payload payload,int id);
    List<Department> getAllDepartments(Payload payload,String name);
    Department addDepartment(Payload payload,Department department);
    Department updateDepartment(Payload payload,Department department);
    Department deleteDepartment(Payload payload,int id);
    Department getDepartmentByCode(Payload payload,String code);
    Department getParentDepartmentByCode(Payload payload,String code);
}
