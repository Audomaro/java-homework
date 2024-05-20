package org.adoption.services;

import org.adoption.domain.Adopter;

import java.util.List;

public interface AdopterService {
    List<Adopter> findAllAdopters();

    Adopter findAdopterById(int adopterId);

    List<Adopter> findAdopterByName(String name);

    List<Adopter> findAdopterWithoutPets();

    List<Adopter> findAdopterWithPets();

    Adopter addAdopterWithPets(Adopter newAdopter);

    Adopter updateAdopter(Adopter updatedAdopter);

    Boolean deleteAdopter(Integer adopterId);
}
