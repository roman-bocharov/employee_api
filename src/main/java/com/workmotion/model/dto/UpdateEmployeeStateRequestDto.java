package com.workmotion.model.dto;

import com.workmotion.model.EmployeeState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateEmployeeStateRequestDto {
    @NotNull
    private EmployeeState state;
}
