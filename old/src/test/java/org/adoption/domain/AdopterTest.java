package org.adoption.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AdopterTest {

    @Test
    void shouldBeValidAdopter() {
        int id = 1;
        String name = "John Doe";
        String phoneNumber = "123-456-7890";
        LocalDate adoptionDate = LocalDate.now();
        Pet pet = new Pet(1, "Buddy", Pet.PetType.DOG, Pet.BreedType.Unknowk);

        Adopter adopter = new Adopter(id, name, phoneNumber, adoptionDate, pet);

        assertEquals(id, adopter.getId());
        assertEquals(name, adopter.getName());
        assertEquals(phoneNumber, adopter.getPhoneNumber());
        assertEquals(adoptionDate, adopter.getAdoptionDate());
        assertEquals(pet, adopter.getPet());
    }

    @Test
    void shouldBeValidId() {
        String name = "John Doe";
        String phoneNumber = "123-456-7890";
        LocalDate adoptionDate = LocalDate.now();
        Pet pet = new Pet(Pet.PetType.DOG);

        Adopter adopter = new Adopter(name, phoneNumber, adoptionDate, pet);

        assertNotSame(0, adopter.getId());
    }

    @Test
    void shouldBeValidData() {

        Adopter adopter = new Adopter(new Pet(Pet.PetType.CAT));

        assertNotSame(0, adopter.getId());
        assertNotSame("", adopter.getName());
        assertNotSame("", adopter.getPhoneNumber());
    }
}