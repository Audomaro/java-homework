package org.adoption.repository;

import org.adoption.domain.Adopter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdopterRepository extends JpaRepository<Adopter, Integer> {
    @Query("SELECT a FROM Adopter a LEFT JOIN FETCH a.pets WHERE a.name like CONCAT('%', :name, '%')")
    List<Adopter> findAdopterByName(String name);

//    @Query("SELECT a FROM Adopter a LEFT JOIN FETCH a.pets p WHERE p IS NOT NULL")
    @Query("SELECT a FROM Adopter a WHERE a.pets IS NOT EMPTY")
    List<Adopter> findAdopterWithPets();

//    @Query("SELECT a FROM Adopter a LEFT JOIN FETCH a.pets p WHERE p IS NULL")
    @Query("SELECT a FROM Adopter a WHERE a.pets IS EMPTY")
    List<Adopter> findAdopterWithoutPets();
}
