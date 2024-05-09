package org.adoption.domain;

import lombok.Getter;

import java.util.Random;

@Getter
public enum PetType {
    CAT(10),
    DOG(20),
    TURTLE(30);

    private static final Random RANDOM = new Random();

    public static PetType random() {
        return values()[RANDOM.nextInt(values().length)];
    }

    private final int value;

    PetType(int value) {
        this.value = value;
    }
}
