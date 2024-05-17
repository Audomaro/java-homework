package org.adoption.domain;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Getter
public enum BreedType {
    Unknown(0),
    Buldog(10),
    Chihuahua(20),
    Dachshund(30),
    Poodle(40),
    Siamese(50),
    British_Shorthair(60),
    Sphynx(70),
    Ragdoll(80),
    Persian(90)
    ;

    private final int value;
    private static final Random RANDOM = new Random();
    private static final Map<Integer, BreedType> BY_VALUE = new HashMap<>();

    static {
        for (BreedType type : values()) {
            BY_VALUE.put(type.value, type);
        }
    }

    BreedType(int value) {
        this.value = value;
    }

    public static BreedType random() {
        return values()[RANDOM.nextInt(values().length)];
    }

    public static BreedType getByValue(int value) {
        return BY_VALUE.get(value);
    }
}
