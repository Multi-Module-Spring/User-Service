package com.wis.practice_basic.service.action.department.impl;

import com.wis.common.configuration.Payload;
import com.wis.common.executation.CoreActionService;
import com.wis.i18n.Translate;
import com.wis.i18n.exception.TranslateException;
import com.wis.practice_basic.model.department.Department;
import com.wis.practice_basic.model.department.dto.action_model.DepartmentGetActionModel;
import com.wis.practice_basic.model.department.dto.request.DepartmentGetRequestDto;
import com.wis.practice_basic.repository.department.DepartmentRepository;
import com.wis.practice_basic.service.action.department.GetDepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetDepartmentServiceImpl
        extends CoreActionService<DepartmentGetRequestDto, DepartmentGetActionModel, Department>
implements GetDepartmentService {

    protected final DepartmentRepository departmentRepository;

    @Override
    protected DepartmentGetActionModel verify(Payload payload, DepartmentGetRequestDto departmentGetRequestDto, LocalDateTime now) {
        int id = integerUtil.nvl(departmentGetRequestDto.getId());
        String code = stringUtil.nvl(departmentGetRequestDto.getCode());

        if(id == 0 && stringUtil.isEmpty(code)) {
            throw new TranslateException(HttpStatus.NOT_FOUND, Translate.DEPARTMENT_NOT_FOUND);
        }
        return DepartmentGetActionModel.builder()
                .id(id)
                .code(code)
                .build();
    }

    @Override
    protected Department innerExecute(Payload payload, DepartmentGetActionModel departmentGetActionModel, LocalDateTime now) {
        Department department = departmentRepository.getDepartment(departmentGetActionModel);
        if(department == null) {
            throw new TranslateException(HttpStatus.NOT_FOUND, Translate.DEPARTMENT_NOT_FOUND);
        }
        return department;
    }
}
