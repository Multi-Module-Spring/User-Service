package com.wis.main.service.action.department.impl;

import com.wis.i18n.Translate;
import com.wis.i18n.exception.TranslateException;
import com.wis.main.configuration.Payload;
import com.wis.main.executation.CoreActionService;
import com.wis.main.model.department.Department;
import com.wis.main.model.department.dto.action_model.GetDepartmentActionModel;
import com.wis.main.model.department.dto.request.GetDepartmentRequestDto;
import com.wis.main.model.department.dto.response.GetDepartmentsResponseDto;
import com.wis.main.repository.department.DepartmentRepository;
import com.wis.main.service.action.department.GetsDepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class GetsDepartmentServiceImpl
        extends CoreActionService<GetDepartmentRequestDto, GetDepartmentActionModel, List<GetDepartmentsResponseDto>>
implements GetsDepartmentService {

    protected final DepartmentRepository departmentRepository;

    @Override
    protected GetDepartmentActionModel verify(Payload payload, GetDepartmentRequestDto departmentGetRequestDto, LocalDateTime now) {
       String name = stringUtil.nvl(departmentGetRequestDto.getName());
        return GetDepartmentActionModel.builder()
                .name(name)
                .build();
    }

    @Override
    protected List<GetDepartmentsResponseDto> innerExecute(Payload payload, GetDepartmentActionModel departmentGetActionModel, LocalDateTime now) {
        String name = stringUtil.nvl(departmentGetActionModel.getName());
        List<GetDepartmentsResponseDto> departments = departmentRepository.getDepartments(departmentGetActionModel);
        if(departments.isEmpty()) {
            throw new TranslateException(HttpStatus.NOT_FOUND, Translate.DEPARTMENT_NOT_FOUND);
        }
        if(!name.isEmpty()) {
            departments = departments.stream()
                    .filter(depart -> messageUtil.getI18n(depart.getDepartmentName())
                            .contains(name)
                    )
                    .toList();
            if(departments.isEmpty()) {
                throw new TranslateException(HttpStatus.NOT_FOUND, Translate.DEPARTMENTS_NOT_FOUND,name);
            }
        }

        return departments;
    }
}
