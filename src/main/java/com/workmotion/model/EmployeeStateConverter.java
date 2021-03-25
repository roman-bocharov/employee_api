package com.workmotion.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class EmployeeStateConverter implements AttributeConverter<EmployeeState, String> {

    @Override
    public String convertToDatabaseColumn(EmployeeState employeeState) {
        if (employeeState == null) {
            return null;
        }
        return employeeState.getState();
    }

    @Override
    public EmployeeState convertToEntityAttribute(String state) {
        if (state == null) {
            return null;
        }

        return Stream.of(EmployeeState.values())
                .filter(s -> s.getState().equals(state))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
