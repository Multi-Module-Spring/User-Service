package com.wis.main.service.action.department.impl.getDepartmentService.factory;

import com.wis.main.service.action.department.impl.getDepartmentService.GetDepartmentService;
import com.wis.main.util.core_util.factory.abstracter.AbstractFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetDepartmentFactory extends AbstractFactory<Integer, GetDepartmentService> {

    protected final List<GetDepartmentService> getDepartmentServices;

    @Override
    protected List<GetDepartmentService> getInstances() {
        return getDepartmentServices;
    }

}
