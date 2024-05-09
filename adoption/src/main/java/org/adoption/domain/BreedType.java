package org.adoption.domain;

import lombok.Getter;

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


    private static final Random RANDOM = new Random();

    public static BreedType random() {
        return values()[RANDOM.nextInt(values().length)];
    }

    private final int value;

    BreedType(int value) {
        this.value = value;
    }
}
