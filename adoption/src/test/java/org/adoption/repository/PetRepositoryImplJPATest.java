package org.adoption.repository;

import org.adoption.domain.Adopter;
import org.adoption.domain.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class PetRepositoryImplJPATest {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setup() {
        entityManager.getEntityManager().createNativeQuery("DELETE FROM t_adopter").executeUpdate();
        entityManager.getEntityManager().createNativeQuery("DELETE FROM t_pet").executeUpdate();
        entityManager.getEntityManager().createNativeQuery("ALTER TABLE t_adopter ALTER COLUMN adopter_id RESTART WITH 1").executeUpdate();
        entityManager.getEntityManager().createNativeQuery("ALTER TABLE t_pet ALTER COLUMN pet_id RESTART WITH 1").executeUpdate();

        Adopter adopter1 = new Adopter();
        adopter1.setName("Isiah Deckow");

        Pet pet1 = new Pet();
        pet1.setName("prueba 1");
        pet1.setAdopter(adopter1);
        adopter1.getPets().add(pet1);

        Pet pet2 = new Pet();
        pet2.setName("prueba 2");

        entityManager.persist(pet1);
        entityManager.persist(pet2);
        entityManager.persist(adopter1);
        entityManager.persist(new Pet());
        entityManager.persist(new Pet());
        entityManager.persist(new Pet());
        entityManager.persist(new Pet());
        entityManager.persist(new Pet());
        entityManager.persist(new Pet());

        entityManager.flush();
    }

    @Test
    void findAllPets() {
        List<Pet> result = petRepository.findAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void addPet() {
        Pet newPet = new Pet();
        newPet.setName("Patita II");
        newPet.setDateAdoption(LocalDate.of(2024,5,20));
        petRepository.save(newPet);

        Optional<Pet> result = petRepository.findById(newPet.getId());

        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(newPet.getName(), result.get().getName());
    }


    @Test
    void getPetById() {
        List<Pet> pets = petRepository.findAll();
        Optional<Pet> pet = petRepository.findById(1);
        assertNotNull(pet);
        assertTrue(pet.isPresent());
        assertEquals("prueba 1", pet.get().getName());
    }


    @Test
    void findPetsByName() {
        List<Pet> pets = petRepository.findByNameIgnoreCaseContaining("prueba");
        assertNotNull(pets);
        assertFalse(pets.isEmpty());
        assertEquals(2, pets.size());
    }

    @Test
    void findPetsWithAdopter() {
        List<Pet> pets = petRepository.findByAdopterIsNotNull();
        assertNotNull(pets);
        assertFalse(pets.isEmpty());
    }

    @Test
    void findPetsWithoutAdopter() {
        List<Pet> pets = petRepository.findByAdopterIsNull();
        assertNotNull(pets);
        assertFalse(pets.isEmpty());
    }


    @Test
    void updatePet() {
        Pet pet = new Pet();
        pet.setId(5);
        pet.setName("Chow");
        pet.setDateAdoption(LocalDate.of(2024,5,20));
        pet = petRepository.save(pet);

        Optional<Pet> result = petRepository.findById(pet.getId());

        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(pet.getName(), result.get().getName());
    }

    @Test
    void deletePet() {
        petRepository.deleteById(7);
        Optional<Pet> result = petRepository.findById(7);
        assertFalse(result.isPresent());
    }
}