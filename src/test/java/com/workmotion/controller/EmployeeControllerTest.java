package com.workmotion.controller;

import com.workmotion.exceptions.EmployeeNotFoundException;
import com.workmotion.model.EmployeeState;
import com.workmotion.model.dto.CreateEmployeeRequestDto;
import com.workmotion.model.dto.UpdateEmployeeStateRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EmployeeControllerTest extends BaseControllerTest {

    @Test
    public void testCreateEmployee() throws Exception {
        final var request = CreateEmployeeRequestDto.builder()
                .firstName("Peter")
                .lastName("Parker")
                .age(30).build();

        mockMvc.perform(post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.employee.firstName", is("Peter")))
                .andExpect(jsonPath("$.employee.lastName", is("Parker")))
                .andExpect(jsonPath("$.employee.age", is(30)))
                .andExpect(jsonPath("$.employee.state", is(EmployeeState.ACTIVE.getState())))
                .andExpect(jsonPath("$.employee.id", notNullValue()));
    }

    @Test
    @Sql(scripts = "/employees.sql")
    public void testUpdateEmployee() throws Exception {
        final var request = UpdateEmployeeStateRequestDto.builder()
                .state(EmployeeState.IN_CHECK)
                .build();

        mockMvc.perform(put("/employee/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employee.firstName", is("Peter")))
                .andExpect(jsonPath("$.employee.lastName", is("Parker")))
                .andExpect(jsonPath("$.employee.age", is(30)))
                .andExpect(jsonPath("$.employee.state", is(EmployeeState.IN_CHECK.toString())))
                .andExpect(jsonPath("$.employee.id", is(1)));
    }

    @Test
    public void testUpdateEmployeeWhenNoEntityFound() throws Exception {
        final var request = UpdateEmployeeStateRequestDto.builder()
                .state(EmployeeState.APPROVED)
                .build();

        mockMvc.perform(put("/employee/200")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(employeeNotFoundException());
    }

    private ResultMatcher employeeNotFoundException() {
        return result -> assertTrue(result.getResolvedException() instanceof EmployeeNotFoundException);
    }
}