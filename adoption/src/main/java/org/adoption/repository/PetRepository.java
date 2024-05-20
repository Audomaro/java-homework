package org.adoption.repository;

import org.adoption.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> findByAdopterIsNull();

    List<Pet> findByAdopterIsNotNull();

    List<Pet> findByNameIgnoreCaseContaining(String name);
}
