package com.wis.main.controller;

import com.wis.main.configuration.Payload;
import com.wis.main.model.department.dto.request.DepartmentAddRequestDto;
import com.wis.main.util.core_util.CoreAPI;
import com.wis.main.model.department.Department;
import com.wis.main.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/search")
    public List<Department> getDepartment(@RequestParam String name) {
        Payload payload = payload(false);
        return departmentService.getAllDepartments(payload,name);
    }

    @GetMapping("/code/{code}")
    public Department getDepartmentByCode(@PathVariable String code) {
        Payload payload = payload(false);
        return departmentService.getDepartmentByCode(
                payload,
                code
        );
    }

    @GetMapping("/code/{code}/parent")
    public Department getParentDepartmentByCode(@PathVariable String code) {
        Payload payload = payload(false);
        return departmentService.getParentDepartmentByCode(
                payload,
                code
        );
    }

    @PostMapping("")
    public Department addDepartment(@RequestBody DepartmentAddRequestDto departmentAddRequestDto) {
        Payload payload = payload(false);
        return departmentService.addDepartment(
                payload,
                departmentAddRequestDto
        );
    }
}
