package org.adoption.domain;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Getter
public enum PetType {
    CAT(10),
    DOG(20),
    TURTLE(30);

    private final int value;
    private static final Random RANDOM = new Random();
    private static final Map<Integer, PetType> BY_VALUE = new HashMap<>();

    static {
        for (PetType type : values()) {
            BY_VALUE.put(type.value, type);
        }
    }

    PetType(int value) {
        this.value = value;
    }

    public static PetType random() {
        return values()[RANDOM.nextInt(values().length)];
    }

    public static PetType getByValue(int value) {
        return BY_VALUE.get(value);
    }
}
