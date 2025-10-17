package com.wis.practice_basic.api.controller;

import com.wis.common.configuration.Payload;
import com.wis.common.util.core_util.CoreAPI;
import com.wis.practice_basic.model.department.Department;
import com.wis.practice_basic.model.user.dto.request.UserGetRequestDto;
import com.wis.practice_basic.model.user.dto.response.UserResponseDto;
import com.wis.practice_basic.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("department")
@RequiredArgsConstructor
public class DepartmentController extends CoreAPI {
    private final DepartmentService departmentService;

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable Integer id) {
        Payload payload = payload(false);
        return departmentService.getDepartmentById(
                payload,
                id
        );
    }

    @GetMapping("/code/{code}")
    public Department getDepartmentByCode(@PathVariable String code) {
        Payload payload = payload(false);
        return departmentService.getDepartmentByCode(
                payload,
                code
        );
    }
}
