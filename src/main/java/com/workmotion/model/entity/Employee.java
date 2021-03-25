package com.workmotion.model.entity;

import com.workmotion.model.EmployeeState;
import com.workmotion.model.EmployeeStateConverter;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @NotEmpty
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Min(1)
    @Max(150)
    @Column(name = "age")
    private Integer age;

    @NotNull
    @Convert(converter = EmployeeStateConverter.class)
    private EmployeeState state;
}
