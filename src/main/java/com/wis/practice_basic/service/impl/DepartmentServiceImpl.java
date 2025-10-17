package com.wis.practice_basic.service.impl;

import com.wis.common.configuration.Payload;
import com.wis.practice_basic.model.department.Department;
import com.wis.practice_basic.model.department.dto.request.DepartmentGetRequestDto;
import com.wis.practice_basic.service.DepartmentService;
import com.wis.practice_basic.service.action.department.GetDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final GetDepartmentService getDepartmentService;

    @Override
    public Department getDepartmentById(Payload payload,int id) {
        return getDepartmentService.execute(payload, DepartmentGetRequestDto
                .builder()
                .id(id)
                .build()
        );
    }

    @Override
    public List<Department> getAllDepartments(Payload payload) {
        return List.of();
    }

    @Override
    public Department addDepartment(Payload payload,Department department) {
        return null;
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
        return null;
    }
}
