package com.wis.main.service.action.department;

import com.wis.main.executation.ActionService;
import com.wis.main.model.department.Department;
import com.wis.main.model.department.dto.request.GetDepartmentRequestDto;
import com.wis.main.model.department.dto.response.GetDepartmentsResponseDto;

import java.util.List;

public interface GetsDepartmentService extends ActionService<GetDepartmentRequestDto, List<GetDepartmentsResponseDto>> {
}
