package com.wis.main.service.impl;

import com.wis.main.configuration.Payload;
import com.wis.main.model.department.Department;
import com.wis.main.model.department.dto.request.DepartmentAddRequestDto;
import com.wis.main.model.department.dto.request.DepartmentGetParentByCodeRequestDto;
import com.wis.main.model.department.dto.request.DepartmentGetRequestDto;
import com.wis.main.service.DepartmentService;
import com.wis.main.service.action.department.AddDepartmentService;
import com.wis.main.service.action.department.GetDepartmentService;
import com.wis.main.service.action.department.GetParentDepartmentService;
import com.wis.main.service.action.department.GetsDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final GetDepartmentService getDepartmentService;
    private final GetsDepartmentService getsDepartmentService;
    private final AddDepartmentService addDepartmentService;
    private final GetParentDepartmentService getParentDepartmentService;

    @Override
    public Department getDepartmentById(Payload payload,int id) {
        return getDepartmentService.execute(payload, DepartmentGetRequestDto
                .builder()
                .id(id)
                .build()
        );
    }

    @Override
    public List<Department> getAllDepartments(Payload payload,String name) {
        return getsDepartmentService.execute(payload,
                DepartmentGetRequestDto.builder()
                        .name(name)
                        .build()
        );
    }

    @Override
    public Department addDepartment(Payload payload, DepartmentAddRequestDto department) {
        return addDepartmentService.execute(payload, department);
    }

    @Override
    public Department updateDepartment(Payload payload,Department department) {
        return null;
    }

    @Override
    public Department deleteDepartment(Payload payload,int id) {
        return null;
    }

    @Override
    public Department getDepartmentByCode(Payload payload,String code) {
        return getDepartmentService.execute(payload, DepartmentGetRequestDto.
                builder()
                .code(code)
                .build()
        );
    }

    @Override
    public Department getParentDepartmentByCode(Payload payload,String code) {
        DepartmentGetParentByCodeRequestDto departmentGetParentByCodeRequestDto =
                DepartmentGetParentByCodeRequestDto.builder()
                .code(code)
                .build();
        return getParentDepartmentService.execute(payload,departmentGetParentByCodeRequestDto);
    }
}
