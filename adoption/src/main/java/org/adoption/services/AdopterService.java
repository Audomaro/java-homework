package org.adoption.services;

import org.adoption.domain.Adopter;

import java.util.List;
import java.util.Optional;

public interface AdopterService {
    List<Adopter> findAllAdopters();

    Optional<Adopter> findAdopterById(int adopterId);

    List<Adopter> findAdopterByName(String name);

    List<Adopter> findAdopterWithoutPets();

    List<Adopter> findAdopterWithPets();

    Adopter addAdopterWithPets(Adopter newAdopter);

    Adopter updateAdopter(Adopter updatedAdopter);

    Boolean deleteAdopter(Integer adopterId);
}
