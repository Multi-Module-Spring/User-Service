package com.wis.main.service.action.department.impl;

import com.wis.i18n.Translate;
import com.wis.i18n.exception.TranslateException;
import com.wis.main.configuration.Payload;
import com.wis.main.executation.CoreActionService;
import com.wis.main.model.department.dto.action_model.GetParentByCodeDepartmentActionModel;
import com.wis.main.model.department.dto.request.GetParentByCodeDepartmentRequestDto;
import com.wis.main.model.department.dto.response.GetParentByCodeDepartmentResponseDto;
import com.wis.main.repository.department.DepartmentRepository;
import com.wis.main.service.action.department.GetParentDepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Component
public class GetParentDepartmentServiceImpl
        extends CoreActionService<
        GetParentByCodeDepartmentRequestDto
        , GetParentByCodeDepartmentActionModel
        , GetParentByCodeDepartmentResponseDto
        > implements GetParentDepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    protected GetParentByCodeDepartmentActionModel verify(Payload payload, GetParentByCodeDepartmentRequestDto departmentGetParentByCodeRequestDto, LocalDateTime now) {
        String code = verifyNotNull(departmentGetParentByCodeRequestDto::getCode);
        return GetParentByCodeDepartmentActionModel.builder()
                .code(code)
                .build();

    }

    @Override
    protected GetParentByCodeDepartmentResponseDto innerExecute(Payload payload, GetParentByCodeDepartmentActionModel departmentGetParentByCodeActionModel, LocalDateTime now) {
        GetParentByCodeDepartmentResponseDto parentDepartment = departmentRepository
                .getParentDepartmentByCode(
                        departmentGetParentByCodeActionModel
                );
        if (parentDepartment == null) {
            throw new TranslateException(HttpStatus.NOT_FOUND, Translate.DEPARTMENT_NOT_FOUND_CODE,
                    departmentGetParentByCodeActionModel.getCode()
            );
        }
        return parentDepartment;
    }
}
