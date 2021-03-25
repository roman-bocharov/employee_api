package com.workmotion.model.dto;

import com.workmotion.model.EmployeeState;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private EmployeeState state;
    private Integer id;
}
