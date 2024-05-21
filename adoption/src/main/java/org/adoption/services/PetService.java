package org.adoption.services;

import org.adoption.domain.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {
    List<Pet> findAllPets();

    Pet addPet(Pet pet);

    Optional<Pet> getPetById(Integer petId);

    List<Pet> findPetsByName(String name);

    List<Pet> findPetsWithAdopter();

    List<Pet> findPetsWithoutAdopter();

    Pet updatePet(Pet updatePet);

    boolean deletePet(Integer petId);
}
