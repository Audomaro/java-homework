package org.adoption.domain;

import lombok.Getter;

import java.util.Random;

@Getter
public enum BreedType {
    Unknown,
    Buldog,
    Chihuahua,
    Dachshund,
    Poodle,
    Siamese,
    British_Shorthair,
    Sphynx,
    Ragdoll,
    Persian
    ;

    private static final Random RANDOM = new Random();

    public static BreedType random() {
        return values()[RANDOM.nextInt(0, values().length)];
    }
}
