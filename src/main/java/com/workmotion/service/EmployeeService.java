package com.workmotion.service;

import com.workmotion.exceptions.EmployeeNotFoundException;
import com.workmotion.model.EmployeeState;
import com.workmotion.model.dto.CreateEmployeeRequestDto;
import com.workmotion.model.dto.EmployeeDto;
import com.workmotion.model.dto.UpdateEmployeeStateRequestDto;
import com.workmotion.model.entity.Employee;
import com.workmotion.model.mapper.EmployeeMapper;
import com.workmotion.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.Consumer;

/**
 * The service allows doing operations on employee such as creation/update
 */
@Transactional
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDto createEmployee(final CreateEmployeeRequestDto request) {
        var employeeToSave = EmployeeMapper.fromCreateEmployeeRequest(request);
        return EmployeeMapper.toDto(employeeRepository.save(employeeToSave));
    }

    public EmployeeDto updateEmployee(final Integer id, final UpdateEmployeeStateRequestDto request) {
        final var employee = employeeRepository.findById(id);

        employee.ifPresentOrElse(setState(request.getState()),
                () -> {
                    throw new EmployeeNotFoundException(String.format("Employee [id: %d] not found", id));
                });

        return EmployeeMapper.toDto(employee.get());
    }

    private Consumer<Employee> setState(final EmployeeState expectedState) {
        return employee -> {
            if (employee.getState().equals(expectedState)) {
                return;
            }

            employee.setState(expectedState);
        };
    }
}
