package com.wis.main.service.impl;

import com.wis.main.configuration.Payload;
import com.wis.main.model.department.Department;
import com.wis.main.model.department.dto.request.DepartmentGetRequestDto;
import com.wis.main.service.DepartmentService;
import com.wis.main.service.action.department.GetDepartmentService;
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
