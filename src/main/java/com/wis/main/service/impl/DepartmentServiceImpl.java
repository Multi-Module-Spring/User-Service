package com.wis.main.service.impl;

import com.wis.main.configuration.Payload;
import com.wis.main.model.department.Department;
import com.wis.main.model.department.dto.request.AddDepartmentRequestDto;
import com.wis.main.model.department.dto.request.GetParentByCodeDepartmentRequestDto;
import com.wis.main.model.department.dto.request.GetDepartmentRequestDto;
import com.wis.main.service.DepartmentService;
import com.wis.main.service.action.department.AddDepartmentService;
import com.wis.main.service.action.department.GetDepartmentService;
import com.wis.main.service.action.department.GetParentDepartmentService;
import com.wis.main.service.action.department.GetsDepartmentService;
import com.wis.main.util.core_util.CoreBean;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl extends CoreBean implements DepartmentService {
    private final GetDepartmentService getDepartmentService;
    private final GetsDepartmentService getsDepartmentService;
    private final AddDepartmentService addDepartmentService;
    private final GetParentDepartmentService getParentDepartmentService;

    @Override
    public Department getDepartmentById(Payload payload,int id) {
        return mapper.mapTo(
                getDepartmentService.execute(payload, GetDepartmentRequestDto
                .builder()
                .id(id)
                .build()
                ), Department.class);
    }

    @Override
    public List<Department> getAllDepartments(Payload payload,String name) {
        return mapper.mapToList(
                getsDepartmentService.execute(payload,
                GetDepartmentRequestDto.builder()
                        .name(name)
                        .build()
                )
                , Department.class);
    }

    @Override
    public Department addDepartment(Payload payload, AddDepartmentRequestDto department) {
        return mapper.mapTo(addDepartmentService.execute(payload, department), Department.class);
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
        return mapper.mapTo(
                getDepartmentService.execute(payload, GetDepartmentRequestDto.
                builder()
                .code(code)
                .build()
        ), Department.class);
    }

    @Override
    public Department getParentDepartmentByCode(Payload payload,String code) {
        GetParentByCodeDepartmentRequestDto departmentGetParentByCodeRequestDto =
                GetParentByCodeDepartmentRequestDto.builder()
                .code(code)
                .build();
        return mapper.mapTo(
                getParentDepartmentService.execute(payload,departmentGetParentByCodeRequestDto)
                ,Department.class);
    }
}
