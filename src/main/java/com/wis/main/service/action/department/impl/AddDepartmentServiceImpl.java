package com.wis.main.service.action.department.impl;

import com.wis.main.configuration.Payload;
import com.wis.main.executation.CoreActionService;
import com.wis.main.model.department.Department;
import com.wis.main.model.department.dto.action_model.DepartmentAddActionModel;
import com.wis.main.model.department.dto.request.DepartmentAddRequestDto;
import com.wis.main.repository.department.DepartmentRepository;
import com.wis.main.service.action.department.AddDepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class AddDepartmentServiceImpl extends CoreActionService<DepartmentAddRequestDto, DepartmentAddActionModel, Department> implements AddDepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    protected DepartmentAddActionModel verify(Payload payload, DepartmentAddRequestDto departmentAddRequestDto, LocalDateTime now) {
        String code = verifyNotNull(departmentAddRequestDto::getCode);
        String parentCode = departmentAddRequestDto.getParentCode();
        return DepartmentAddActionModel.builder()
                .code(code)
                .parentCode(parentCode)
                .build();
    }

    @Override
    protected Department innerExecute(Payload payload, DepartmentAddActionModel departmentAddActionModel, LocalDateTime now) {
        return departmentRepository.addDepartment(departmentAddActionModel);
    }
}
