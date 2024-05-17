package org.adoption.services;

import jakarta.persistence.EntityNotFoundException;
import org.adoption.domain.Adopter;
import org.adoption.repository.AdopterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdopterService {

    private final AdopterRepository adopterRepository;

    public AdopterService(AdopterRepository adopterRepository) {
        this.adopterRepository = adopterRepository;
    }

    public List<Adopter> findAllAdopters() {
        return this.adopterRepository.findAll();
    }

    public Adopter findAdopterById(int adopterId) {
        return adopterRepository.findById(adopterId)
                .orElseThrow(() -> new EntityNotFoundException("Adopter not found with id: " + adopterId));
    }

    public List<Adopter> findAdopterByName(String name) {
        return adopterRepository.findAdopterByName(name);
    }

    public List<Adopter> findAdopterWithoutPets() {
        return adopterRepository.findAdopterWithoutPets();
    }

    public List<Adopter> findAdopterWithPets() {
        return this.adopterRepository.findAdopterWithPets();
    }

    public Adopter addAdopterWithPets(Adopter newAdopter) {
        newAdopter.getPets().forEach( pet -> pet.setAdopter(newAdopter));
        return adopterRepository.save(newAdopter);
    }

    public Adopter updateAdopter(Integer adopterId, Adopter updatedAdopter) {
        Adopter adopter = adopterRepository.findById(adopterId)
                .orElseThrow(() -> new EntityNotFoundException("Adopter not found with id: " + adopterId));

            adopter.setName(updatedAdopter.getName());
            adopter.setPhoneNumber(updatedAdopter.getPhoneNumber());

            return adopterRepository.save(adopter);
    }

    public void deleteAdopter(Integer adopterId) {
        Adopter adopter = adopterRepository.findById(adopterId)
                .orElseThrow(() -> new EntityNotFoundException("Adopter not found with id: " + adopterId));

        adopterRepository.delete(adopter);
    }

}
