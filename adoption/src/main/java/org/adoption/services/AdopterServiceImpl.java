package org.adoption.services;

import org.adoption.domain.Adopter;
import org.adoption.repository.AdopterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AdopterServiceImpl implements AdopterService {

    private final AdopterRepository adopterRepository;

    public AdopterServiceImpl(AdopterRepository adopterRepository) {
        this.adopterRepository = adopterRepository;
    }

    @Override
    public List<Adopter> findAllAdopters() {
        return this.adopterRepository.findAll();
    }

    @Override
    public Optional<Adopter> findAdopterById(int adopterId) {
        return adopterRepository.findById(adopterId);
    }

    @Override
    public List<Adopter> findAdopterByName(String name) {
        return adopterRepository.findAdopterByName(name);
    }

    @Override
    public List<Adopter> findAdopterWithoutPets() {
        return adopterRepository.findAdopterWithoutPets();
    }

    @Override
    public List<Adopter> findAdopterWithPets() {
        return this.adopterRepository.findAdopterWithPets();
    }

    @Override
    public Adopter addAdopterWithPets(Adopter newAdopter) {
        newAdopter.getPets().forEach( pet -> pet.setAdopter(newAdopter));
        return adopterRepository.save(newAdopter);
    }

    @Override
    public Adopter updateAdopter(Adopter updatedAdopter) {
        return adopterRepository.save(updatedAdopter);
    }

    @Override
    public Boolean deleteAdopter(Integer adopterId) {
        try {
            adopterRepository.deleteById(adopterId);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
