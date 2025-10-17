package com.wis.practice_basic.service.action.department;

import com.wis.common.executation.ActionService;
import com.wis.practice_basic.model.department.Department;
import com.wis.practice_basic.model.department.dto.request.DepartmentGetRequestDto;

public interface GetDepartmentService extends ActionService<DepartmentGetRequestDto, Department> {
}
