package org.adoption.services;

import org.adoption.domain.Pet;

import java.util.List;

public interface PetService {
    List<Pet> getAllPets();

    Pet addPet(Pet pet);

    Pet getPetById(Integer petId);

    Pet updatePet(Pet updatePet);

    void deletePet(Integer petId);
}
