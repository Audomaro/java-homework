package org.adoption.services;

import org.adoption.domain.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {
    List<Pet> getAllPets();

    Pet addPet(Pet pet);

    Optional<Pet> getPetById(Integer petId);

    List<Pet> getPetByName(String name);

    List<Pet> getPetsWithAdopter();

    List<Pet> getPetsWithoutAdopter();

    Pet updatePet(Pet updatePet);

    boolean deletePet(Integer petId);
}
