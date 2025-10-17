package com.wis.main.api.controller;

import com.wis.main.configuration.Payload;
import com.wis.main.util.core_util.CoreAPI;
import com.wis.main.model.department.Department;
import com.wis.main.service.DepartmentService;
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
