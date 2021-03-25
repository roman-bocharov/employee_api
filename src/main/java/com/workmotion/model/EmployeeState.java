package com.workmotion.model;

public enum EmployeeState {
    ADDED("ADDED"), IN_CHECK("IN-CHECK"), APPROVED("APPROVED"), ACTIVE("ACTIVE");
    private final String state;

    EmployeeState(final String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
