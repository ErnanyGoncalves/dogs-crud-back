package com.api.dog.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE("male"),
    FEMALE("female");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Gender fromValue(String value) {
        for (Gender gender : Gender.values()) {
            if (gender.value.equalsIgnoreCase(value)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
