package org.adoption.services;

import jakarta.persistence.EntityNotFoundException;
import org.adoption.domain.Pet;
import org.adoption.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Pet addPet(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet getPetById(Integer petId) {
        return petRepository.findById(petId)
                .orElseThrow(() -> new EntityNotFoundException("Pet not found with id: " + petId));
    }

    public Pet updatePet(Pet updatePet) {
        return petRepository.save(updatePet);
    }

    public void deletePet(Integer petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new EntityNotFoundException("Pet not found with id: " + petId));

        petRepository.delete(pet);
    }
}
