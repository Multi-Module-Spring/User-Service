package com.wis.main.service.action.department.impl;

import com.wis.i18n.Translate;
import com.wis.i18n.exception.TranslateException;
import com.wis.main.configuration.Payload;
import com.wis.main.executation.CoreActionService;
import com.wis.main.model.department.Department;
import com.wis.main.model.department.dto.action_model.DepartmentGetParentByCodeActionModel;
import com.wis.main.model.department.dto.request.DepartmentGetParentByCodeRequestDto;
import com.wis.main.repository.department.DepartmentRepository;
import com.wis.main.service.action.department.GetParentDepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetParentDepartmentServiceImpl
        extends CoreActionService<
        DepartmentGetParentByCodeRequestDto
        , DepartmentGetParentByCodeActionModel
        , Department
        > implements GetParentDepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    protected DepartmentGetParentByCodeActionModel verify(Payload payload, DepartmentGetParentByCodeRequestDto departmentGetParentByCodeRequestDto, LocalDateTime now) {
        String code = verifyNotNull(departmentGetParentByCodeRequestDto::getCode);
        return DepartmentGetParentByCodeActionModel.builder()
                .code(code)
                .build();

    }

    @Override
    protected Department innerExecute(Payload payload, DepartmentGetParentByCodeActionModel departmentGetParentByCodeActionModel, LocalDateTime now) {
        Department parentDepartment = departmentRepository
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
