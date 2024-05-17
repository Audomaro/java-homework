package org.adoption.dao;

import org.adoption.domain.Pet;

import java.util.List;

public interface PetDAO  {
    Pet insert(Pet newAdopter);

    Pet findByID(int id);

    boolean update(Pet updateAdopter);

    boolean delete(int id);

    List<Pet> findAll();
}