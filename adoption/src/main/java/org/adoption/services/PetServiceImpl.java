package org.adoption.services;

import org.adoption.domain.Pet;
import org.adoption.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet addPet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Optional<Pet> getPetById(Integer petId) {
        return petRepository.findById(petId);
    }

    @Override
    public List<Pet> findPetsByName(String name) {
        return petRepository.findByNameIgnoreCaseContaining(name);
    }

    @Override
    public List<Pet> findPetsWithAdopter() {
        return petRepository.findByAdopterIsNotNull();
    }

    @Override
    public List<Pet> findPetsWithoutAdopter() {
        return petRepository.findByAdopterIsNull();
    }

    @Override
    public Pet updatePet(Pet updatePet) {
        return petRepository.save(updatePet);
    }

    @Override
    public boolean deletePet(Integer petId) {
        petRepository.deleteById(petId);
        return true;
    }
}
