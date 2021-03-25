package com.workmotion.controller;

import com.workmotion.model.dto.CreateEmployeeRequestDto;
import com.workmotion.model.dto.EmployeeResponse;
import com.workmotion.model.dto.UpdateEmployeeStateRequestDto;
import com.workmotion.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@Validated
@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody CreateEmployeeRequestDto request) {
        final var employeeDto = employeeService.createEmployee(request);
        final var employeeResponse = EmployeeResponse.builder()
                .employee(employeeDto)
                .build();

        final var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employeeDto.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(employeeResponse);
    }

    @PutMapping(value = "/employee/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeResponse changeState(@Valid @RequestBody UpdateEmployeeStateRequestDto request, @Valid @PathVariable Integer id) {
        final var employeeDto = employeeService.updateEmployee(id, request);

        return EmployeeResponse.builder()
                .employee(employeeDto)
                .build();
    }
}
