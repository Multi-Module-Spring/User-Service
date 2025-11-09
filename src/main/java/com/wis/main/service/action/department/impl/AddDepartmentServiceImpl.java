package com.wis.main.service.action.department.impl;

import com.wis.main.configuration.Payload;
import com.wis.main.executation.CoreActionService;
import com.wis.main.model.department.Department;
import com.wis.main.model.department.dto.action_model.AddDepartmentActionModel;
import com.wis.main.model.department.dto.request.AddDepartmentRequestDto;
import com.wis.main.model.department.dto.response.AddDepartmentResponseDto;
import com.wis.main.repository.department.DepartmentRepository;
import com.wis.main.service.action.department.AddDepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Component
public class AddDepartmentServiceImpl extends CoreActionService<AddDepartmentRequestDto, AddDepartmentActionModel, AddDepartmentResponseDto> implements AddDepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    protected AddDepartmentActionModel verify(Payload payload, AddDepartmentRequestDto addDepartmentRequestDto, LocalDateTime now) {
        String code = verifyNotNull(addDepartmentRequestDto::getCode);
        String parentCode = addDepartmentRequestDto.getParentCode();
        return AddDepartmentActionModel.builder()
                .code(code)
                .parentCode(parentCode)
                .build();
    }

    @Override
    protected AddDepartmentResponseDto innerExecute(Payload payload, AddDepartmentActionModel departmentAddActionModel, LocalDateTime now) {
        return departmentRepository.addDepartment(departmentAddActionModel);
    }
}
