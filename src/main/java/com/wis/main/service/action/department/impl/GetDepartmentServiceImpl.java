package com.wis.main.service.action.department.impl;

import com.wis.main.configuration.Payload;
import com.wis.main.executation.CoreActionService;
import com.wis.i18n.Translate;
import com.wis.i18n.exception.TranslateException;
import com.wis.main.model.department.Department;
import com.wis.main.model.department.dto.action_model.GetDepartmentActionModel;
import com.wis.main.model.department.dto.request.GetDepartmentRequestDto;
import com.wis.main.model.department.dto.response.GetDepartmentResponseDto;
import com.wis.main.repository.department.DepartmentRepository;
import com.wis.main.service.action.department.GetDepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Component
public class GetDepartmentServiceImpl
        extends CoreActionService<GetDepartmentRequestDto, GetDepartmentActionModel, GetDepartmentResponseDto>
implements GetDepartmentService {

    protected final DepartmentRepository departmentRepository;

    @Override
    protected GetDepartmentActionModel verify(Payload payload, GetDepartmentRequestDto departmentGetRequestDto, LocalDateTime now) {
        int id = integerUtil.nvl(departmentGetRequestDto.getId());
        String code = stringUtil.nvl(departmentGetRequestDto.getCode());

        if(id == 0 && stringUtil.isEmpty(code)) {
            throw new TranslateException(HttpStatus.NOT_FOUND, Translate.DEPARTMENT_NOT_FOUND);
        }
        return GetDepartmentActionModel.builder()
                .id(id)
                .code(code)
                .build();
    }

    @Override
    protected GetDepartmentResponseDto innerExecute(Payload payload, GetDepartmentActionModel departmentGetActionModel, LocalDateTime now) {
        GetDepartmentResponseDto department = departmentRepository.getDepartment(departmentGetActionModel);
        if(department == null) {
            throw new TranslateException(HttpStatus.NOT_FOUND, Translate.DEPARTMENT_NOT_FOUND);
        }
        return department;
    }
}
