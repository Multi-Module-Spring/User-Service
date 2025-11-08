package com.wis.main.service.action.department;

import com.wis.main.executation.ActionService;
import com.wis.main.model.department.Department;
import com.wis.main.model.department.dto.request.DepartmentGetParentByCodeRequestDto;

public interface GetParentDepartmentService extends ActionService<DepartmentGetParentByCodeRequestDto, Department> {
}
