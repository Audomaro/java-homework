package org.adoption.services;

import jakarta.persistence.EntityNotFoundException;
import org.adoption.domain.Pet;
import org.adoption.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet addPet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Pet getPetById(Integer petId) {
        return petRepository.findById(petId)
                .orElseThrow(() -> new EntityNotFoundException("Pet not found with id: " + petId));
    }

    @Override
    public Pet updatePet(Pet updatePet) {
        return petRepository.save(updatePet);
    }

    @Override
    public void deletePet(Integer petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new EntityNotFoundException("Pet not found with id: " + petId));

        petRepository.delete(pet);
    }
}
