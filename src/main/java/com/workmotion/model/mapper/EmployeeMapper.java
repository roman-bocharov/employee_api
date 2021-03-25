package com.workmotion.model.mapper;

import com.workmotion.model.EmployeeState;
import com.workmotion.model.dto.CreateEmployeeRequestDto;
import com.workmotion.model.dto.EmployeeDto;
import com.workmotion.model.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto toDto(final Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .age(employee.getAge())
                .state(employee.getState())
                .build();
    }

    public static Employee fromCreateEmployeeRequest(final CreateEmployeeRequestDto request) {
        return Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .age(request.getAge())
                .state(EmployeeState.ACTIVE)
                .build();
    }

    private EmployeeMapper() {
        // Intentionally left empty as it's Factory
    }
}
