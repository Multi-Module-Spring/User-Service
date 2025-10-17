package com.wis.main.service.action.department.impl;

import com.wis.i18n.Translate;
import com.wis.i18n.exception.TranslateException;
import com.wis.main.configuration.Payload;
import com.wis.main.executation.CoreActionService;
import com.wis.main.model.department.Department;
import com.wis.main.model.department.dto.action_model.DepartmentGetActionModel;
import com.wis.main.model.department.dto.request.DepartmentGetRequestDto;
import com.wis.main.repository.department.DepartmentRepository;
import com.wis.main.service.action.department.GetsDepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetsDepartmentServiceImpl
        extends CoreActionService<DepartmentGetRequestDto, DepartmentGetActionModel, List<Department>>
implements GetsDepartmentService {

    protected final DepartmentRepository departmentRepository;

    @Override
    protected DepartmentGetActionModel verify(Payload payload, DepartmentGetRequestDto departmentGetRequestDto, LocalDateTime now) {
       String name = stringUtil.nvl(departmentGetRequestDto.getName());
        return DepartmentGetActionModel.builder()
                .name(name)
                .build();
    }

    @Override
    protected List<Department> innerExecute(Payload payload, DepartmentGetActionModel departmentGetActionModel, LocalDateTime now) {
        String name = stringUtil.nvl(departmentGetActionModel.getName());
        List<Department> departments = departmentRepository.getDepartments(departmentGetActionModel);
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
