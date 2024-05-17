package org.adoption.domain;

import lombok.Getter;

import java.util.Random;

@Getter
public enum PetType {
    CAT,
    DOG,
    TURTLE;

    private static final Random RANDOM = new Random();

    public static PetType random() {
        return values()[RANDOM.nextInt(0, values().length)];
    }
}
