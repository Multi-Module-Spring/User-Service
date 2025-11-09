package com.wis.main.repository.department;

import com.wis.main.model.department.Department;
import com.wis.main.model.department.dto.action_model.AddDepartmentActionModel;
import com.wis.main.model.department.dto.action_model.GetDepartmentActionModel;
import com.wis.main.model.department.dto.action_model.GetParentByCodeDepartmentActionModel;
import com.wis.main.model.department.dto.response.AddDepartmentResponseDto;
import com.wis.main.model.department.dto.response.GetDepartmentResponseDto;
import com.wis.main.model.department.dto.response.GetDepartmentsResponseDto;
import com.wis.main.model.department.dto.response.GetParentByCodeDepartmentResponseDto;

import java.time.LocalDateTime;
import java.util.List;


public interface DepartmentRepository {
    GetDepartmentResponseDto getDepartment(GetDepartmentActionModel departmentGetActionModel);

    List<GetDepartmentsResponseDto> getDepartments(GetDepartmentActionModel departmentGetActionModel);

    AddDepartmentResponseDto addDepartment(AddDepartmentActionModel departmentAddActionModel, LocalDateTime localDateTime);

    GetParentByCodeDepartmentResponseDto getParentDepartmentByCode(GetParentByCodeDepartmentActionModel departmentGetParentByCodeActionModel);
}
