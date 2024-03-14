package org.adoption.domain;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PetTest {

    @Test
    public void shouldBeValidPet() {
        int petId = 1;
        String name = "Buddy";
        Pet.PetType type = Pet.PetType.DOG;
        Pet.BreedType breed = Pet.BreedType.Unknowk;

        Pet pet = new Pet(petId, name, type, breed);

        assertEquals(petId, pet.getPetId());
        assertEquals(name, pet.getName());
        assertEquals(type, pet.getType());
        assertEquals(breed, pet.getBreed());
    }

    @Test
    public void breedTypeShouldBeUnknown() {
        Pet.BreedType expected = Pet.BreedType.Unknowk;

        Pet pet = new Pet(1, "Buddy", Pet.PetType.DOG);

        assertEquals(expected, pet.getBreed());
    }

    @Test
    public void petTypeShouldBeDog() {
        Pet.PetType expected = Pet.PetType.DOG;

        Pet current = new Pet(Pet.PetType.DOG);

        assertSame(expected, current.getType());
    }

    @Test
    public void petTypeShouldNotBeDog() {
        Pet.PetType unexpected = Pet.PetType.DOG;

        Pet current = new Pet(Pet.PetType.CAT);

        assertNotSame(unexpected,current.getType());
    }


}