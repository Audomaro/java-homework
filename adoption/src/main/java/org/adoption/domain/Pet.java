package org.adoption.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonSerialize
public class Pet {
    private int petId;
    private String name;
    private PetType type;
    private BreedType breed;

    public Pet() {
        this.setName(new Faker().funnyName().name());
        this.setType(PetType.random());
        this.setBreed(BreedType.random());
    }

    public Pet(PetType type) {
        this.setName(new Faker().funnyName().name());
        this.setType(type);
        this.setBreed(BreedType.random());
    }
}
